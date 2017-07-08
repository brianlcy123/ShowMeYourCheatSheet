package com.example.demo;

import java.io.Serializable;
import java.util.stream.Stream;
import com.example.demo.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.repository.ReservationRepository;

@SpringBootApplication
public class DemoApplication implements Serializable {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}