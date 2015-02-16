package com.sandbox.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.config.DynamicPropertyFactory;
import com.sandbox.dao.PersonDao;
import com.swapi.models.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
* @author Nick Braukhoff
* @since 1/15/15
*/
@Component
public class StarWarsServiceImpl implements StarWarsService {
    private static final DynamicPropertyFactory PROPERTY_FACTORY = DynamicPropertyFactory.getInstance();
    public static final String HTTP_SWAPI_CO_API = "http://swapi.co/api";
    public static final String PEOPLE_API = "/people";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    PersonDao personDao;

    @Autowired
    ObjectMapper objectMapper;


    public People getPerson(final String id) {
        final int swapiTimeout = PROPERTY_FACTORY.getIntProperty("person.swapi.timeout", 300).get();

        final HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        final String url = buildUrl(PEOPLE_API, id);

        return new GetPersonCommand(restTemplate, personDao, Integer.valueOf(id), url, objectMapper, swapiTimeout).execute();

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

