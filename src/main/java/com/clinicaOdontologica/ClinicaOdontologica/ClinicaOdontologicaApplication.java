package com.clinicaOdontologica.ClinicaOdontologica;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicaOdontologicaApplication {

	//esto carga todo el apache. Entonces:
	//Creo las tablas --> el primer .run ejecuta todo lo creado de momento.



	public static void main(String[] args) {

		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
	}

}
