package com.hp.dit.Flight.Application.Form;

import com.hp.dit.Flight.Application.Form.captcha.CaptchaGenServlet;
import com.hp.dit.Flight.Application.Form.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class FlightApplicationFormApplication {

	public static void main(String[] args) throws UnsupportedEncodingException { SpringApplication.run(FlightApplicationFormApplication.class, args);



	}


		@Bean
	ServletRegistrationBean captchaServletRegistration () {
		ServletRegistrationBean srb = new ServletRegistrationBean();
		srb.setName("CaptchaServlet");
		srb.setServlet(new CaptchaGenServlet());
		srb.addUrlMappings("/captcha.jpg");
		return srb;
	}

}
