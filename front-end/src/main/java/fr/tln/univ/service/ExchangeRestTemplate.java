package fr.tln.univ.service;

import fr.tln.univ.exception.SendingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class ExchangeRestTemplate implements CallRestWebService{
    private final RestTemplate restTemplate;

    public ExchangeRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static String createUrlFromParams(String url, MultiValueMap<String, String> params) {
        return UriComponentsBuilder.fromHttpUrl(url).queryParams(params).build().toUriString();
    }

    @Override
    public Object call(String url
            , HttpMethod method
            , HttpEntity<Object> entity) throws IOException {
        // call rest with exchange
        ResponseEntity<Object> objResponse = restTemplate.exchange(url, method, entity, Object.class);
        //TODO objResponse == null ???
        if (objResponse.getStatusCode() == HttpStatus.valueOf(200)) {
            return objResponse.getBody();
        }
        log.error("server exception with code:" + objResponse.getStatusCode() + ", message " + objResponse.getBody());
        throw new SendingException("server exception with code:" + objResponse.getStatusCode() + ", message " + objResponse.getBody());
    }

    public HttpHeaders createHeaderFromMap(MediaType mediaType, Map<String, String> map) {
        // Header
        HttpHeaders headers = new HttpHeaders();
        //Set MediaType
        if (mediaType != null) {
            headers.setAccept(Collections.singletonList(mediaType));
        }

        // Set other headers
        if (map != null && map.size() != 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        return headers;
    }

    //--------------------------------------------------------------------------------------------------------
    //  Call Post Method
    //--------------------------------------------------------------------------------------------------------
    public Object callPostWithHeader(String url, Object reqObj, HttpHeaders headers) throws SendingException, IOException {
        log.debug("Starting callPostWithHeader method ");
        // Combine Body and Header
        HttpEntity<Object> request = new HttpEntity<>(reqObj, headers);
        return call(url, HttpMethod.POST, request);
    }

    public Object callRestPostWithHeaders(String url, Object reqObj, Map<String, String> map) throws SendingException, IOException {
        return callPostWithHeader(url, reqObj, createHeaderFromMap(MediaType.APPLICATION_JSON, map));
    }

    public Object callPostWithoutHeader(String url, Object reqObj) throws SendingException, IOException {
        log.debug("Starting callPostWithoutHeader method");
        // Set body
        HttpEntity<Object> request = new HttpEntity<>(reqObj);
        return call(url, HttpMethod.POST, request);
    }

    //--------------------------------------------------------------------------------------------------------
    //  Call Get Method
    //--------------------------------------------------------------------------------------------------------
    public Object callGet(HttpHeaders headers, MultiValueMap<String, String> params, String url) throws SendingException, IOException {
        return call(createUrlFromParams(url, params), HttpMethod.GET,
                new HttpEntity<Object>(headers));
    }

    public Object callRestGetWithAuthorization(MultiValueMap<String, String> params, String url, HashMap<String, String> map) throws SendingException, IOException {
        return call(createUrlFromParams(url, params), HttpMethod.GET,
                new HttpEntity<Object>(createHeaderFromMap(null, map)));
    }
}
