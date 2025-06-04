package br.com.digio.vinho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class VinhoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VinhoApplication.class, args);
	}

}
