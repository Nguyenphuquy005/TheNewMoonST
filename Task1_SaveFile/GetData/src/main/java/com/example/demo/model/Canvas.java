package com.example.demo.model;

import java.awt.*;
import java.util.List;

public class Canvas {
    private long id;
    public List domain;

    public Canvas() {
    }

    public Canvas(long id, List domain) {
        this.id = id;
        this.domain = domain;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List getDomain() {
        return domain;
    }

    public void setDomain(List domain) {
        this.domain = domain;
    }
}
