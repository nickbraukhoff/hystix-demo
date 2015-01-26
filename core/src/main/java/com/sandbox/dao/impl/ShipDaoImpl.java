package com.sandbox.dao.impl;

import com.google.common.collect.Maps;
import com.sandbox.dao.ShipDao;
import com.sandbox.dto.Ship;
import com.sandbox.dto.ShipEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author tkmay02
 * @since 1/24/15
 */
@Component
public class ShipDaoImpl implements ShipDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${ships.select}")
    String shipsSelect;

    @Value("${ships.type.select}")
    String shipTypeSelect;



    @Override
    public List<Ship> getShipType(final String type) {

        final Map<String, Object> argMap = Maps.newHashMap();
        argMap.put("type", type);
        final List<Ship> ships = namedParameterJdbcTemplate.query(shipTypeSelect, argMap, ShipEntityMapper.getInstance());

        return ships;
    }

    @Override
    public List<Ship> getShips() {

        final List<Ship> ships = namedParameterJdbcTemplate.query(shipsSelect, ShipEntityMapper.getInstance());

        return ships;
    }
}
