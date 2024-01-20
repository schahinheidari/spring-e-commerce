package fr.tln.univ.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestConfig {
    /// @Value("${info.http.readTimeOut}")
    /// private static int readTimeout;
    /// @Value("${info.http.connectTimeOut}")
    /// private static int connectTimeout;


    @Bean
    public RestTemplate restTemplate() {
        // Customize the RestTemplate instance here
        return new RestTemplate();
    }
}
