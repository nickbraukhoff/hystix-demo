package com.sandbox.dao;

import com.sandbox.dto.Ship;

import java.util.List;

/**
 * @author Nick Braukhoff
 * @since 1/24/15
 */
public interface ShipDao {

    List<Ship> getShips();
    List<Ship> getShipType(final String type);
}
