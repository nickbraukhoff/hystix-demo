package com.sandbox.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandbox.exception.ServiceException;
import com.swapi.models.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
* @author tkmay02
* @since 1/15/15
*/
@Component
public class StarWarsServiceImpl implements StarWarsService {

    public static final String HTTP_SWAPI_CO_API = "http://swapi.co/api";
    public static final String PEOPLE_API = "/people";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;


    public People getPerson(final String id) {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        final String url = buildUrl(PEOPLE_API,id);

        final HttpEntity<String> request = new HttpEntity<String>(headers);
        final ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        final String responseBody = response.getBody();
        try {
            if (RestUtil.isError(response.getStatusCode())) {
//                throw new ServiceException(response.getStatusCode().value(), "This is not the page you are looking for.");
                throw new RuntimeException(responseBody);

            } else {
                return objectMapper.readValue(responseBody, People.class);
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildUrl(final String domain, final String id){
        final StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(HTTP_SWAPI_CO_API);
        stringBuffer.append(domain);
        stringBuffer.append("/");
        stringBuffer.append(id);
        return stringBuffer.toString();
    }
}

