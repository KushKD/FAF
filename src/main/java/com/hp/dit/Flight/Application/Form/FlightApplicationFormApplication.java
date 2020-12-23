package com.hp.dit.Flight.Application.Form;

import com.hp.dit.Flight.Application.Form.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class FlightApplicationFormApplication {

	public static void main(String[] args) throws UnsupportedEncodingException { SpringApplication.run(FlightApplicationFormApplication.class, args);



	}

}
