package id.co.diansetiyadi.notificationservice;

import com.google.gson.Gson;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableKafka
@EnableWebFlux
@EnableMongoAuditing
@EnableMongoRepositories
@OpenAPIDefinition
@ComponentScan(basePackages = {"id.co.diansetiyadi.notificationservice", "pl.piomin.logging"})
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@Bean
	public Gson gson() {
		return new Gson();
	}
}
