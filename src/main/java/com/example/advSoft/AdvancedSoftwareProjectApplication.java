package com.example.advSoft;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLException;

@SpringBootApplication
@RestController
public class AdvancedSoftwareProjectApplication {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		SpringApplication.run(AdvancedSoftwareProjectApplication.class, args);
	}
}
