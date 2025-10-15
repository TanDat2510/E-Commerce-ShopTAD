package com.tad;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TadShopBackendApplication {

	public static void main(String[] args) {
        System.out.println(">>> ENV TEST: " + System.getenv("${SPRING_DATASOURCE_URL}"));
        SpringApplication.run(TadShopBackendApplication.class, args);
	}

    @PostConstruct
    public void Test(){
        System.out.println("Toi la dat");
    }
}
