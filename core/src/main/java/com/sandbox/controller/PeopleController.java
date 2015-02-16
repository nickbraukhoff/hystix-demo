package com.sandbox.controller;

import com.sandbox.service.PersonService;
import com.sandbox.view.PersonView;
import com.sandbox.view.ShipView;
import com.sandbox.ws.StarWarsService;
import com.swapi.models.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Nick Braukhoff
 * @since 1/25/15
 */
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    StarWarsService starWarsService;

    @Autowired
    PersonService personService;


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<PersonView> getPeople() {
        return personService.getPeople();
    }

    @RequestMapping(value="/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PersonView getShipType(@RequestParam(value = "name", required = false) final String name) {
        return personService.getPersonByName(name);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PersonView getPersonById(@PathVariable final String id) {
        return personService.getPersonByID(Integer.valueOf(id));
    }

    @RequestMapping(value = "/swapi/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public People getPilot(@PathVariable final String id) {
        return starWarsService.getPerson(id);
    }
}
