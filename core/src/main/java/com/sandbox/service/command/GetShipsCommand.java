package com.sandbox.service.command;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.hystrix.*;
import com.sandbox.dao.ShipDao;
import com.sandbox.dto.Ship;
import com.sandbox.view.ShipView;

import java.util.List;

/**
 * @author Nick Braukhoff
 * @since 1/25/15
 */
public class GetShipsCommand extends HystrixCommand<List<Ship>> {
    private static final DynamicPropertyFactory PROPERTY_FACTORY = DynamicPropertyFactory.getInstance();

    private ShipDao shipDao;

    public GetShipsCommand(final ShipDao shipDao, final int timeOut){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ShipsGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetShipsCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ShipsPool"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationThreadTimeoutInMilliseconds(timeOut)));
        this.shipDao = shipDao;
    }



    @Override
    protected List<Ship> run() throws Exception {
        final int val = (int) ((Math.random() * PROPERTY_FACTORY.getIntProperty("range.val", 4).get()) + 1);

        if (val != 4) {
            return shipDao.getShips();
        } else {
            throw new RuntimeException("Value equals 4");
        }
    }
}
