package fr.tln.univ.config;


import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RestCaller {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "http://localhost:9090/";

    public <T> Object call(String url, HttpMethod method, T body, Class<T> requestType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> requestEntity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Object> responseEntity = restTemplate.exchange(
                    (BASE_URL + url),
                    method,
                    requestEntity,
                    Object.class
            );
            return responseEntity.getBody();
        } catch (Exception e) {
            return null;
        }
    }

}
