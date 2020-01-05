import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class TestMain {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "majj02:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        System.out.println("-----------------------------------------------1");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        System.out.println("-----------------------------------------------2");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        System.out.println("-----------------------------------------------3");
        Producer<String, String> producer = new KafkaProducer<>(props);
        System.out.println("-----------------------------------------------3");
        try {
            producer.send(new ProducerRecord<String, String>("first", "名字", "李四"));
        }catch (Exception e){
            System.out.println(e);
        }

        producer.close();
    }
}
