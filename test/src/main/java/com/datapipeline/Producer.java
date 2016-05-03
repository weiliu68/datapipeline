package com.datapipeline;

import com.google.common.io.Resources;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Properties;

public class Producer {
    private static SecureRandom random = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static String encodeBase62(long num) {
        return alphabetEncode(num, 62);
    }

    private static String alphabetEncode(long num, int base) {
        num = Math.abs(num);
        StringBuilder sb = new StringBuilder();
        for (; num > 0; num /= base) {
            sb.append(ALPHABET.charAt((int) (num % base)));
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Must have topic argument");
        }
        String topic = args[1];
        KafkaProducer<String, String> producer;
        try (InputStream props = Resources.getResource("producer.props").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            producer = new KafkaProducer<>(properties);
        }

        try {
            for (int i = 0; i < 1000; i++) {
                producer.send(new ProducerRecord<String, String>(topic, String.format("{user: \"%s\", password: \"%s\"}", encodeBase62(random.nextLong()), encodeBase62(random.nextLong()))));
                if (i % 10 == 0) {
                    producer.flush();
                    System.out.println("Sent msg number " + i);
                }
            }
        } catch (Throwable throwable) {
            System.out.printf("%s", throwable.getStackTrace());
        } finally {
            producer.close();
        }
    }
}