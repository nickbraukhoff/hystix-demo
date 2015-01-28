package com.sandbox.controller;

import com.sandbox.service.ShipService;
import com.sandbox.view.ShipView;
import com.sandbox.ws.StarWarsService;
import com.swapi.models.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tkmay02
 * @since 1/24/15
 */
@RestController
@RequestMapping("/ships")
public class ShipController {
    public static final String JSON_CONTENT_TYPE = "application/json";

    @Autowired
    ShipService shipService;

    @RequestMapping(method = RequestMethod.GET, produces = {JSON_CONTENT_TYPE})
    @ResponseBody
    public List<ShipView> getShips() {
        return shipService.getShips();
    }

}
