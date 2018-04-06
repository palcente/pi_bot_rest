package com.labuda.matt.rest.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by mateu on 04/04/2018.
 */
@Component
@Scope("prototype")
public class Device {
    private String owner;
    private String address;
    private String type;

    public Device(String owner, String address, String type) {
        this.owner = owner;
        this.address = address;
        this.type = type;
    }

    public String getOwner() {
        return owner;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }
}
