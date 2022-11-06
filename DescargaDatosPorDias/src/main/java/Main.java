import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args){
        String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhLnNhbmNoZXpwZS4yMDE5QGFsdW1ub3MudXJqYy5lcyIsImp0aSI6ImRhYTliMDk2LWI5OTgtNGY5Yi05NDdhLWQxMDY5Yjc5NzJkZSIsImlzcyI6IkFFTUVUIiwiaWF0IjoxNjU2NTEwNDUwLCJ1c2VySWQiOiJkYWE5YjA5Ni1iOTk4LTRmOWItOTQ3YS1kMTA2OWI3OTcyZGUiLCJyb2xlIjoiIn0.jC6TwKGKfFn-MM5OUDYKZV48qxnDIBIg_NYfGs4i3TU";
        String baseUrl = "https://opendata.aemet.es/centrodedescargas/productosAEMET?";
        System.setProperty("webdriver.chrome.driver", "D:\\TFGAlvaroSanchez\\DescargaDatosPorDias\\chromedriver_win32\\chromedriver.exe");
        int anoInicio = 2007;
        int anoFin = 2022;


        WebDriver driver = new ChromeDriver();
        driver.get(baseUrl);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Select desplegable1 = new Select(driver.findElement(By.id("clim1")));
        List<WebElement> ld1 = desplegable1.getOptions();

        driver.close();

        for (int anoInt = anoInicio; anoInt <= anoFin; anoInt++) {
            String ano = String.valueOf(anoInt);
            for (int provincia = 1; provincia <= ld1.size()-1; provincia++) {
                driver = new ChromeDriver();
                driver.get(baseUrl);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                desplegable1 = new Select(driver.findElement(By.id("clim1")));
                desplegable1.selectByIndex(provincia);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Select desplegable2 = new Select(driver.findElement(By.id("clim11")));
                List<WebElement> ld2 = desplegable2.getOptions();
                driver.close();
                for (int estacionEnProvincia = 1; estacionEnProvincia <= ld2.size()-1; estacionEnProvincia++) {
                    driver = new ChromeDriver();
                    driver.get(baseUrl);
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //Guardamos el ID de la ventana principal
                    String originalWindow = driver.getWindowHandle();

                    driver.findElement(By.id("apikey")).sendKeys(apiKey);
                    desplegable1 = new Select(driver.findElement(By.id("clim1")));
                    desplegable1.selectByIndex(provincia);

                    try {
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    desplegable2 = new Select(driver.findElement(By.id("clim11")));
                    desplegable2.selectByIndex(estacionEnProvincia);

                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String estacionMeterologica = driver.findElement(By.xpath("/html//tr[17]/td[3]/div/button/span")).getText();
                    estacionMeterologica = estacionMeterologica.replaceAll("/", " ");

                    WebElement fecha1 = driver.findElement(By.id("fechaclim1"));
                    WebElement fecha2 = driver.findElement(By.id("fechaclim2"));
                    ((JavascriptExecutor)driver).executeScript("arguments[0].removeAttribute('disabled');", fecha1);
                    ((JavascriptExecutor)driver).executeScript("arguments[0].removeAttribute('disabled');", fecha2);
                    fecha1.sendKeys(ano+"-01-01");
                    fecha2.sendKeys(ano+"-12-31");

                    driver.findElement(By.id("bclim1")).click();

                    //Bucle para cambiar de ventana
                    for (String windowHandle : driver.getWindowHandles()) {
                        if (!originalWindow.contentEquals(windowHandle)) {
                            driver.switchTo().window(windowHandle);
                            break;
                        }
                    }

                    String texto = driver.findElement(By.xpath("//pre[contains(@style,'word-wrap')]")).getText();
                    if(texto.contains("\"descripcion\" : \"exito\"")) {
                        String urlDatos = texto.split("\"")[9];

                        driver.get(urlDatos);
                        texto = driver.findElement(By.xpath("//pre[contains(@style,'word-wrap')]")).getText();

                        //Imprimimos la informacion en un fichero externo
                        PrintWriter printWriter = null;
                        String ubicacionGuardar = "D:\\TFGAlvaroSanchez\\data\\day\\";
                        String nombreFichero = ubicacionGuardar.concat(estacionMeterologica).concat(" (").concat(ano).concat(")").concat(".json");

                        try {
                            printWriter = new PrintWriter(nombreFichero);
                        } catch (FileNotFoundException e) {
                            System.out.println("Unable to locate the fileName: " + e.getMessage());
                        }
                        Objects.requireNonNull(printWriter).println(texto);
                        printWriter.close();
                    }

                    //Para cerrar todas las ventanas que se abren
                    for (String handle : driver.getWindowHandles()) {
                        driver.switchTo().window(handle);
                        driver.close();
                    }
                }
            }
        }
        System.exit(0);
    }
}