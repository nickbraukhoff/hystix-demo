package com.sandbox.service.impl;

import com.sandbox.service.BattleService;
import com.sandbox.view.ShipView;
import org.springframework.stereotype.Component;

/**
 * @author tkmay02
 * @since 1/24/15
 */
@Component
public class BattleServiceImpl implements BattleService {

    @Override
    public ShipView getShip(String shipType) {
        final ShipView shipView = new ShipView();
        shipView.setId("TKMAY02");
        shipView.setType("Tie Fighter");

        return shipView;
    }
}
