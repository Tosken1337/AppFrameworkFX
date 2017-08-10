package com.github.tosken1337.afwfx;

/**
 * Created by Sebastian Greif on 07.08.2017.
 * Copyright di support 2017
 */
public enum ApplicationEvent {
    Exit("Exit");

    ApplicationEvent(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    private String id;
}
