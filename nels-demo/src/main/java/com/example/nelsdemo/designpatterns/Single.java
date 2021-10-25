package com.example.nelsdemo.designpatterns;

import java.io.ObjectStreamException;
import java.io.Serializable;

public final class Single implements Cloneable, Serializable {
    private static final Single SINGLE = new Single();
    private String name = "hello";

    //disable cloning
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("CloneNotSupported");
    }

    //disable serialization
    private Object readResolve() throws ObjectStreamException {
        throw new RuntimeException("SerializationNotSupported");
    }

    private Single() {
        // disable refection
        if (null != SINGLE) {
            throw new RuntimeException("instance already exists");
        }
    }

    public static Single getInstance() {
        return SINGLE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
