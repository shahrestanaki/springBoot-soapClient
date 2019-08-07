package com.config;

import com.localhost.springsoap.gen.GetAcntwgRequest;
import com.localhost.springsoap.gen.GetAcntwgResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@Configuration
@PropertySource({"classpath:application.properties"})
@ComponentScan(basePackages = {"com.config", "com.controller"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner lookup(SOAPConnector soapConnector) {
        return args -> {
            String name = "";
            if (args.length > 0) {
                name = args[0];
            }
            if (!name.isEmpty()) {
                GetAcntwgRequest request = new GetAcntwgRequest();
                request.setAcntno(name);
                GetAcntwgResponse response = (GetAcntwgResponse) soapConnector.callWebService("http://localhost:8043/ws", request);
                System.out.println("Got Response As below ========= : " + response.getResult());
            }
        };
    }
}