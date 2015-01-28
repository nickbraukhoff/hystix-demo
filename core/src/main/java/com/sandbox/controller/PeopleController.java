package com.sandbox.controller;

import com.sandbox.service.PersonService;
import com.sandbox.view.PersonView;
import com.sandbox.view.ShipView;
import com.sandbox.ws.StarWarsService;
import com.swapi.models.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tkmay02
 * @since 1/25/15
 */
@RestController
@RequestMapping("/people")
public class PeopleController {

    public static final String JSON_CONTENT_TYPE = "application/json";

    @Autowired
    StarWarsService starWarsService;

    @Autowired
    PersonService personService;


    @RequestMapping(method = RequestMethod.GET, produces = {JSON_CONTENT_TYPE})
    @ResponseBody
    public List<PersonView> getPeople() {
        return personService.getPeople();
    }

    @RequestMapping(method = RequestMethod.POST, produces = {JSON_CONTENT_TYPE})
    @ResponseBody
    public PersonView getShipType(@PathVariable final String name) {
        return personService.getPersonByName(name);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = {JSON_CONTENT_TYPE})
    @ResponseBody
    public PersonView getPersonById(@PathVariable final String id) {
        return personService.getPersonByID(Integer.valueOf(id));
    }

    @RequestMapping(value = "/swapi/{id}", method = RequestMethod.GET, produces = {JSON_CONTENT_TYPE})
    @ResponseBody
    public People getPilot(@PathVariable final String id) {
        return starWarsService.getPerson(id);
    }
}
