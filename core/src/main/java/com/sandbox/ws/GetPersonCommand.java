package com.sandbox.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.*;
import com.sandbox.dao.PersonDao;
import com.sandbox.dto.Person;
import com.sandbox.exception.ServiceException;
import com.swapi.models.People;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

/**
 * @author tkmay02
 * @since 1/26/15
 */
public class GetPersonCommand extends HystrixCommand<People> {

    private final RestTemplate restTemplate;
    private final PersonDao personDao;
    private final int id;
    private final String url;
    private final ObjectMapper objectMapper;

    public GetPersonCommand(final RestTemplate restTemplate, final PersonDao personDao, final int id,
                            final String url, final ObjectMapper objectMapper, final int timeOut){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("PersonSWAPIGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetPersonCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("PersonSWAPIPool"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationThreadTimeoutInMilliseconds(timeOut)));
        this.restTemplate = restTemplate;
        this.url = url;
        this.personDao = personDao;
        this.id = id;
        this.objectMapper = objectMapper;

    }


    @Override
    protected People run() throws Exception {
        final ResponseEntity<String> responseEntity =  restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        final String responseBody = responseEntity.getBody();
        try {
            if (RestUtil.isError(responseEntity.getStatusCode())) {
                throw new ServiceException(responseEntity.getStatusCode().value(), "This is not the page you are looking for.");
            } else {
                return objectMapper.readValue(responseBody, People.class);
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected People getFallback(){
        final List<Person> person = personDao.getPersonByID(id);
        return mapPersonToPeople(person.get(0));

    }

    private People mapPersonToPeople(final Person person){
        final People people = new People();
        people.name = person.getName();
        people.gender = person.getGender();

        return people;
    }
}
