package com.sandbox.service;

import com.sandbox.view.PersonView;

import java.util.List;

/**
 * @author tkmay02
 * @since 1/25/15
 */
public interface PersonService {
    List<PersonView> getPeople();

    PersonView getPersonByName(final String name);

    List<PersonView> getGender(final String gender);

    PersonView getPersonByID(final int id);

}
