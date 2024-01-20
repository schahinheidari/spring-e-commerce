package fr.tln.univ.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.io.IOException;

public interface CallRestWebService {
    Object call(String url
            , HttpMethod method
            , HttpEntity<Object> entity) throws IOException;
}
