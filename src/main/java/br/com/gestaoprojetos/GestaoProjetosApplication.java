package br.com.gestaoprojetos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.gestaoprojetos")
public class GestaoProjetosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoProjetosApplication.class, args);
	}

}
