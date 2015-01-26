package com.sandbox.service.impl;

import com.netflix.hystrix.*;
import com.sandbox.dao.ShipDao;
import com.sandbox.view.ShipView;

import java.util.List;

/**
 * @author tkmay02
 * @since 1/25/15
 */
public class GetShipsCommand extends HystrixCommand<List<ShipView>> {

    private ShipDao shipDao;

    public GetShipsCommand(final ShipDao shipDao, final int timeOut){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("OfferVendorGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetShipsCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("OfferVendorPool"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationThreadTimeoutInMilliseconds(timeOut)));
    }



    @Override
    protected List<ShipView> run() throws Exception {
        return null;
    }
}
