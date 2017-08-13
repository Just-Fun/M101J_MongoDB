package com.M233_spark_and_mongoDB;

import com.mongodb.spark.api.java.MongoSpark;
import com.mongodb.spark.rdd.api.java.JavaMongoRDD;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.bson.Document;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * @author sergii.zagryvyi on 13.08.2017
 */
public class SpaceWalk implements Serializable {

    static Pattern pattern = Pattern.compile("\\w+\\s\\w+");

    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setMaster("local")
                .setAppName("MongoSparkConnectorTour")
                .set("spark.app.id", "MongoSparkConnectorTour")
                .set("spark.mongodb.input.uri", "mongodb://127.0.0.1/video.movies")
                .set("spark.mongodb.output.uri", "mongodb://127.0.0.1/video2.movies2");

        JavaSparkContext jsc = new JavaSparkContext(conf);
        JavaMongoRDD<Document> rdd = MongoSpark.load(jsc);

        System.out.println(rdd.count());
        System.out.println(rdd.first());

//        for(doc <- rdd.take(10)) println(doc)


     /*   JavaPairRDD<String, Integer> log = rdd.flatMapToPair(
                new PairFlatMapFunction<Document, String, Integer>() {
                    public Iterator<Tuple2<String, Integer>> call(Document document) throws Exception {
                        int minutes = 0;
                        String time = (String) document.get("Duration");
                        if (!time.isEmpty()) {
                            String[] timeComponents = time.split(":");
                            minutes = new Integer(timeComponents[0]) * 60;
                            minutes += new Integer(timeComponents[1]);
                        }

                        String crewString = (String) document.get("Crew");
                        Matcher matcher = pattern.matcher(crewString);
                        List<String> crew = new ArrayList<>();
                        while (matcher.find()) {
                            crew.add(matcher.group());
                        }
                        final int finaleMinutes = minutes;
                        return crew.stream()
                                .map(new Function<String, Tuple2<String, Integer>>() {
                                    @Override
                                    public Tuple2<String, Integer> apply(String c) {
                                        return new Tuple2<>(c, finaleMinutes);

                                    }
                                })
                                .flatMap(s -> s)
                                .collect(Collectors.toList());

                    }
                });
        JavaPairRDD<String, Integer> totalHours = log.reduceByKey(
                new Function2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                }
        );

        FlatMapFunction map = new FlatMapFunction<Tuple2<String, Integer>>,*/

    }

}
