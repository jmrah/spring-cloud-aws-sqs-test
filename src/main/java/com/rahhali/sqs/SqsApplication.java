package com.rahhali.sqs;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SqsApplication {
    @Autowired
    public static void main(String[] args) {
        SpringApplication.run(SqsApplication.class, args);
    }

    @SqsListener("deleteMe")
    public void listen(String message) {}
}
