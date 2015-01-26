package com.sandbox.dto;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author tkmay02
 * @since 1/25/15
 */
public class PersonEntityMapper  implements RowMapper<Person> {

    private static final PersonEntityMapper PERSON_ENTITY_MAPPER = new PersonEntityMapper();
    public static final String TYPE = "TYPE";
    public static final String NAME = "NAME";
    public static final String PROFESSION = "PROFESSION";
    public static final String GENDER = "GENDER";
    public static final String BIRTHPLACE = "BIRTHPLACE";
    public static final String HOME = "HOME";
    public static final String ID = "ID";
    public static final String HREF = "HREF";

    public static PersonEntityMapper getInstance() {
        return PERSON_ENTITY_MAPPER;
    }

    @Override
    public Person mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
        final Person person = new Person();
        person.setId(resultSet.getInt(ID));
        person.setName(resultSet.getString(NAME));
        person.setProfession(resultSet.getString(PROFESSION));
        person.setGender(resultSet.getString(GENDER));
        person.setBirthplace(resultSet.getString(BIRTHPLACE));
        person.setHome(resultSet.getString(HOME));
        person.setHref(resultSet.getString(HREF));

        return person;
    }
}
