import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{functions => func, _}
import org.apache.log4j.{Level, Logger}

import com.typesafe.config.ConfigFactory

object Main {

  def main(args: Array[String]) = {

    val config = ConfigFactory.load("config.conf")

    implicit val spark = SparkSession.builder().getOrCreate()
    import spark.implicits._
    Logger.getRootLogger.setLevel(Level.ERROR)

    val data = spark.read.parquet(config.getString("inputDataPath"))
    val stations = spark.read.option("delimiter", ";").csv(config.getString("stationsData")).toDF("provincia", "indicativo", "ubicacion")
    val window = Window.partitionBy($"indicativo", $"año").orderBy($"fecha")

    val results = data
      .filter(!func.isnull($"tmax") && $"tmax" >= 40)
      .withColumn("año", func.year($"fecha"))
      .withColumn("n_fila", func.row_number().over(window))
      .withColumn("id", func.expr("date_sub(fecha, n_fila)"))
      .groupBy($"indicativo", $"año", $"id")
      .agg(func.count($"id").alias("dias"), func.avg($"tmax"), func.max($"tmax"), func.min($"tmax"))
      .filter($"dias" > 3)
      .join(stations, "indicativo")
      .select($"ubicacion", $"provincia", $"año", $"dias", func.round($"avg(tmax)", 2).alias("avg(tmax)"),
        $"max(tmax)", $"min(tmax)")

    val resultsSave = results
      .groupBy($"provincia", $"año")
      .agg(func.count($"provincia"), func.avg($"dias"), func.avg($"avg(tmax)"), func.max($"max(tmax)"), func.min($"min(tmax)"))
      .select($"provincia", $"año", $"count(provincia)".alias("nº de olas de calor"),
        $"avg(dias)".alias("duracion media"), $"avg(avg(tmax))".alias("avg(tmax)"),
        $"max(max(tmax))".alias("max(tmax)"), $"min(min(tmax))".alias("min(tmax)"))

    resultsSave
      .withColumnRenamed("nº de olas de calor", "nOlasCalor")
      .withColumnRenamed("duracion media", "duracionMedia")
      .withColumnRenamed("avg(tmax)", "avgTmax")
      .withColumnRenamed("max(tmax)", "maxTmax")
      .withColumnRenamed("min(tmax)", "minTmax")
      .write.format("parquet").partitionBy("año").mode("overwrite").save(config.getString("outputDataPath"))

    spark.stop()

    println(Seq.fill(100)("#").mkString)
    println("--> La consulta ha finalizado con exito")
    println("--> Se guardaron los datos del resultado en: " + config.getString("outputDataPath"))
    println(Seq.fill(100)("#").mkString)
  }
}