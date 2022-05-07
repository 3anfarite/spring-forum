package org.example.again;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
public class AgainApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgainApplication.class, args);
	}
	@Bean
	public MultipartConfigElement multipartConfigElement() {
	  MultipartConfigFactory factory = new MultipartConfigFactory();
	  factory.setMaxFileSize(DataSize.ofBytes(9024000)); 
	  factory.setMaxRequestSize(DataSize.ofBytes(9024000));
	  return factory.createMultipartConfig();
	}

}
