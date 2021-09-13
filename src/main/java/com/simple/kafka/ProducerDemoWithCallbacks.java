package com.simple.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoWithCallbacks {
    public static void main(String s[]) throws Exception
    {
       // Logger logger = LoggerFactory.getLogger(ProducerDemoWithCallbacks.class);


        //create Producer Properties
        String bootstrapSevers = "127.0.0.1:9092";
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapSevers);

        //create Producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        //send Data
        ProducerRecord<String, String> record;
        record = new ProducerRecord<>("first_topic","Hello Kafka from Java");
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    //executes record successfully sent
                if(e == null)
                {
                    //logger.info("received new metadata " + recordMetadata.topic() +"\n" +
                      //      recordMetadata.offset() +" \n"+recordMetadata.partition()+"\n"+recordMetadata.timestamp());
                    System.out.println("received new metadata " + recordMetadata.topic() +"\n" +
                            recordMetadata.offset() +" \n"+recordMetadata.partition()+"\n"+recordMetadata.timestamp());
                }
                else {
                    //logger.error("Error while producing" + e);
                }
            }
        });
        //flush data
        producer.flush();
        //flush data and close producer
        producer.close();
    }
}
