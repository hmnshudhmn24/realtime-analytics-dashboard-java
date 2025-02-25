import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.*;
import org.apache.spark.streaming.kafka010.*;

import java.util.*;

public class SparkKafkaConsumer {
    public static void main(String[] args) throws InterruptedException {
        SparkConf conf = new SparkConf().setAppName("RealTimeAnalytics").setMaster("local[*]");
        JavaStreamingContext streamingContext = new JavaStreamingContext(conf, Durations.seconds(5));

        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        kafkaParams.put(ConsumerConfig.GROUP_ID_CONFIG, "spark-group");
        kafkaParams.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaParams.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        Collection<String> topics = Collections.singletonList("real-time-data");

        JavaInputDStream<ConsumerRecord<String, String>> stream =
                KafkaUtils.createDirectStream(
                        streamingContext,
                        LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.Subscribe(topics, kafkaParams)
                );

        stream.map(record -> Integer.parseInt(record.value()))
                .foreachRDD(rdd -> {
                    List<Integer> data = rdd.collect();
                    System.out.println("Received Data: " + data);
                });

        streamingContext.start();
        streamingContext.awaitTermination();
    }
}
