package com.sandbox.dto;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author tkmay02
 * @since 1/24/15
 */
public class ShipEntityMapper implements RowMapper<Ship> {
    private static final ShipEntityMapper SHIP_ENTITY_MAPPER = new ShipEntityMapper();
    public static final String TYPE = "TYPE";
    public static final String NAME = "NAME";

    public static ShipEntityMapper getInstance() {
        return SHIP_ENTITY_MAPPER;
    }


    @Override
    public Ship mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
        final Ship ship = new Ship();
        ship.setType(resultSet.getString(TYPE));
        ship.setName(resultSet.getString(NAME));

        return ship;
    }
}
