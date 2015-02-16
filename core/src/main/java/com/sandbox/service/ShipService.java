package com.sandbox.service;

import com.sandbox.view.ShipView;
import com.swapi.models.People;

import java.util.List;

/**
 * @author Nick Braukhoff
 * @since 1/24/15
 */
public interface ShipService {

    List<ShipView> getShips();

    List<ShipView> getShipType(final String shipType);

    People getPilot(final String id);
}
