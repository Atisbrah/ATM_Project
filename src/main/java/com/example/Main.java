package com.example;

import com.example.ATMMachine.MachineInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(MachineInterface machineInterface) {
		return args -> {
			while (machineInterface.isWorking()) {
				machineInterface.run();
			}
		};
	}
}
