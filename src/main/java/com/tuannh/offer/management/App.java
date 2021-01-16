package com.tuannh.offer.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;

@SpringBootApplication(
        exclude = KafkaAutoConfiguration.class,
        scanBasePackages = {"com.tuannh.offer.management"}
)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}
