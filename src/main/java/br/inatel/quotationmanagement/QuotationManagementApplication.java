package br.inatel.quotationmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class QuotationManagementApplication {

	@GetMapping("/message")
	public String getMessage() {
		return "Hello World";
	}
	public static void main(String[] args) {
		SpringApplication.run(QuotationManagementApplication.class, args);
	}

}
