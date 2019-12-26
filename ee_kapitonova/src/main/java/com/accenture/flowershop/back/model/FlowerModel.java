package com.accenture.flowershop.back.model;

import com.accenture.flowershop.back.entity.Flower;

import java.util.ArrayList;
import java.util.List;

public class FlowerModel {
    private List<Flower> flowerList;

    public FlowerModel() {
        this.flowerList = new ArrayList<Flower>();
        this.flowerList.add(new Flower("p01", "tulip", "tulip.jpg", 20));
        this.flowerList.add(new Flower("p02", "rose", "rose.jpeg", 21));
        this.flowerList.add(new Flower("p03", "chamomile", "chamomile.jpg", 22));
    }

    public List<Flower> findAll() {
        return this.flowerList;
    }

    public Flower find(String id) {
        for (Flower product : this.flowerList) {
            if (product.getId().equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null;
    }
}
