package com.sandbox.service.command;

import com.netflix.hystrix.*;
import com.sandbox.dao.PersonDao;
import com.sandbox.dto.Person;

import java.util.List;

/**
 * @author tkmay02
 * @since 1/27/15
 */
public class GetByNameCommand extends HystrixCommand<List<Person>> {

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
        return personDao.getPersonByName(name);
    }
}
