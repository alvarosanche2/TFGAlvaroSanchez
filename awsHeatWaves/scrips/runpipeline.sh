#!/bin/bash

CLUSTER_ID=$1
MAIN_JAR=s3://$2/jars/awsHeatWaves-assembly-0.1.0-SNAPSHOT.jar
PROFILE=default

aws emr add-steps \
  --cluster-id $CLUSTER_ID \
  --steps Type=Spark,Name="My program",ActionOnFailure=CONTINUE,Args=[--class,Main,$MAIN_JAR] \
  --profile $PROFILE

