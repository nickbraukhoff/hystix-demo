package com.sandbox.service.impl;

import com.google.common.collect.Lists;
import com.sandbox.dao.ShipDao;
import com.sandbox.dto.Ship;
import com.sandbox.service.ShipService;
import com.sandbox.service.command.GetShipsCommand;
import com.sandbox.view.ShipView;
import com.swapi.models.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nick Braukhoff
 * @since 1/24/15
 */
@Component
public class ShipServiceImpl implements ShipService {

    @Autowired
    ShipDao shipDao;

    @Override
    public List<ShipView> getShips() {
        final List<Ship> ships = new GetShipsCommand(shipDao, 300).execute();

        return assembleShipView(ships);
    }

    @Override
    public List<ShipView> getShipType(final String shipType) {
        final List<Ship> ships = shipDao.getShipType(shipType);
        final List<ShipView> shipViews = assembleShipView(ships);

        return shipViews;
    }

    @Override
    public People getPilot(final String id) {
        return null;
    }

    private List<ShipView> assembleShipView(final List<Ship> ships){
        final List<ShipView> shipViews = Lists.newArrayList();
        for(Ship ship : ships){
            final ShipView shipView = new ShipView();
            shipView.setType(ship.getType());
            shipView.setName(ship.getName());
            shipViews.add(shipView);
        }

        return shipViews;
    }
}
