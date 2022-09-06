package com.toast.grid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class ToastGridApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToastGridApplication.class, args);
	}

}
