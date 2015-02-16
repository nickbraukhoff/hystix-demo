package com.sandbox.dao;

import com.sandbox.dto.Person;

import java.util.List;

/**
 * @author Nick Braukhoff
 * @since 1/25/15
 */
public interface PersonDao {

    List<Person> getPeople();

    List<Person> getPersonByName(final String name);

    List<Person> getGender(final String gender);

    List<Person> getPersonByID(final int id);



}
