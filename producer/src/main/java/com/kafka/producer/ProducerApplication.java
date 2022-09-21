package com.kafka.producer;

import org.json.JSONException;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kafka.producer.configs.ProducerConfiguration;

@SpringBootApplication
public class ProducerApplication {


	public static void main(String[] args) throws JSONException {
//		SpringApplication.run(ProducerApplication.class, args);
		ProducerConfiguration producer = new ProducerConfiguration();
		
		
//		@Value("${socket.base.port}")
//		private String baeldungPresentation;
		
		
    	producer.Client1("sockets", 12345);
		
	}
}
