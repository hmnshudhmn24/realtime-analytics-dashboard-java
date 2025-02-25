import org.apache.kafka.clients.producer.*;
import java.util.Properties;
import java.util.Random;

public class KafkaDataProducer {
    private static final String TOPIC = "real-time-data";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        Random random = new Random();

        while (true) {
            int value = random.nextInt(100);
            producer.send(new ProducerRecord<>(TOPIC, "key", String.valueOf(value)));
            System.out.println("Sent: " + value);
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
