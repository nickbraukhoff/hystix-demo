package com.sandbox.service.impl;

import com.google.common.collect.Lists;
import com.sandbox.dao.PersonDao;
import com.sandbox.dto.Person;
import com.sandbox.service.PersonService;
import com.sandbox.service.command.GetAllPeopleCommand;
import com.sandbox.service.command.GetByNameCommand;
import com.sandbox.service.command.GetByPersonIdCommand;
import com.sandbox.view.PersonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tkmay02
 * @since 1/25/15
 */
@Component
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonDao personDao;

    @Override
    public List<PersonView> getPeople() {

        final List<Person> persons = new GetAllPeopleCommand(personDao, 300).execute();
        return mapListOfPeople(persons);
    }

    @Override
    public PersonView getPersonByName(final String name) {

        final List<Person> persons = new GetByNameCommand(personDao, name, 300).execute();

        return mapSinglePersonView(persons.get(0));
    }

    @Override
    public List<PersonView> getGender(final String gender) {
        final List<Person> persons = personDao.getGender(gender);

        return mapListOfPeople(persons);
    }

    @Override
    public PersonView getPersonByID(final int id) {
        final List<Person> persons = new GetByPersonIdCommand(personDao, id, 300).execute();

        return mapSinglePersonView(persons.get(0));
    }

    private PersonView mapSinglePersonView(final Person person){
        final PersonView personView = new PersonView();
        personView.setId(person.getId());
        personView.setName(person.getName());
        personView.setGender(person.getGender());
        personView.setProfession(person.getProfession());
        personView.setBirthplace(person.getBirthplace());
        personView.setHome(person.getHome());
        personView.setHref(person.getHref());

        return personView;
    }

    private List<PersonView> mapListOfPeople(final List<Person> persons){
        final List<PersonView> personViews = Lists.newArrayList();
        for(Person person : persons){
            personViews.add(mapSinglePersonView(person));
        }
        return personViews;
    }

}
