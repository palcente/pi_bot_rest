package com.labuda.matt.rest.model.macscan;

import java.sql.Timestamp;

/**
 * Created by mateu on 04/04/2018.
 */
public class MacscanUserTS {
    private Timestamp ts;
    private String owner;

    public MacscanUserTS(Timestamp ts, String owner) {
        this.ts = ts;
        this.owner = owner;
    }

    public Timestamp getTs() {
        return ts;
    }

    public void setTs(Timestamp ts) {
        this.ts = ts;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
