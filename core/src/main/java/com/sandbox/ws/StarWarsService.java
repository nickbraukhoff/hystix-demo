package com.sandbox.ws;

import com.swapi.models.People;

/**
 * @author Nick Braukhoff
 * @since 1/25/15
 */
public interface StarWarsService {

    People getPerson(final String id);
}
