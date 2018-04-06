package com.labuda.matt.rest.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Created by mateu on 04/04/2018.
 */

public class DataPoint<T> {
    private Timestamp timestamp;
    private T value;

    public DataPoint(Timestamp timestamp, T value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public T getValue() {
        return value;
    }
}
