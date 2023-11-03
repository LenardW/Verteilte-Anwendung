package de.leuphana.shop;

import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication
@EnableJpaRepositories(basePackages = { "de.leuphana.shop.connector" })
@EntityScan(basePackages = { "de.leuphana.shop.component.structure" })
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableFeignClients
public class ShopApplication {

	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ShopApplication.class, args);
	}

}


