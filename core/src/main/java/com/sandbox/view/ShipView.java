package com.sandbox.view;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * @author tkmay02
 * @since 1/24/15
 */
@JsonAutoDetect
public class ShipView {
    private String id;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
