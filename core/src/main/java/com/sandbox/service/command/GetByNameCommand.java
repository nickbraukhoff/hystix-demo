package com.sandbox.service.command;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.hystrix.*;
import com.sandbox.dao.PersonDao;
import com.sandbox.dto.Person;

import java.util.List;

/**
 * @author Nick Braukhoff
 * @since 1/27/15
 */
public class GetByNameCommand extends HystrixCommand<List<Person>> {
    private static final DynamicPropertyFactory PROPERTY_FACTORY = DynamicPropertyFactory.getInstance();

    private final PersonDao personDao;
    private final String name;


    public GetByNameCommand(final PersonDao personDao, final String name, final int timeOut) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("PeopleGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetByNameCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("PeoplePool"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationThreadTimeoutInMilliseconds(timeOut)));
        this.personDao = personDao;
        this.name = name;

    }

    @Override
    protected List<Person> run() throws Exception {
        final int val = (int) ((Math.random() * PROPERTY_FACTORY.getIntProperty("range.val", 4).get()) + 1);

        if(val != 4) {
            return personDao.getPersonByName(name);
        } else {
            throw new RuntimeException("Value equals 4");
        }
    }
}
