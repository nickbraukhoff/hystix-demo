package com.sandbox.controller;

import com.sandbox.service.BattleService;
import com.sandbox.view.ShipView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tkmay02
 * @since 1/24/15
 */
@RestController
@RequestMapping("/deathstar")
public class DeathStar {
    public static final String JSON_CONTENT_TYPE = "application/json";

    @Autowired
    BattleService battleService;

    @RequestMapping(value = "/{shipType}", method = RequestMethod.GET, produces = {JSON_CONTENT_TYPE})
    @ResponseBody
    public ShipView generateOffer(@PathVariable final String shipType) {
        return battleService.getShip(shipType);
    }

}
