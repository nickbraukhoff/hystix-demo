package com.sandbox.dao.impl;

import com.google.common.collect.Maps;
import com.sandbox.dao.PersonDao;
import com.sandbox.dto.Person;
import com.sandbox.dto.PersonEntityMapper;
import com.sandbox.service.command.GetAllPeopleCommand;
import com.sandbox.service.command.GetByNameCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Nick Braukhoff
 * @since 1/25/15
 */
@Component
public class PersonDaoImpl implements PersonDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${people.select}")
    String peopleSelect;

    @Value("${person.name.select}")
    String personNameSelect;

    @Value("${person.id.select}")
    String personIdSelect;


    @Override
    public List<Person> getPeople() {

        final List<Person> people = namedParameterJdbcTemplate.query(peopleSelect, PersonEntityMapper.getInstance());

        return people;

    }

    @Override
    public List<Person> getPersonByName(final String name) {
        final Map<String, Object> argMap = Maps.newHashMap();
        argMap.put("name", name);
        final List<Person> people = namedParameterJdbcTemplate.query(personNameSelect, argMap, PersonEntityMapper.getInstance());

        return people;
    }

    @Override
    public List<Person> getGender(String gender) {
        return null;
    }

    @Override
    public List<Person> getPersonByID(final int id) {
        final Map<String, Object> argMap = Maps.newHashMap();
        argMap.put("id", id);
        final List<Person> people = namedParameterJdbcTemplate.query(personIdSelect, argMap, PersonEntityMapper.getInstance());

        return people;
    }
}
