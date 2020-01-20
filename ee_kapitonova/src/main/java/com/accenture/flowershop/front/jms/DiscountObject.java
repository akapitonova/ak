package com.accenture.flowershop.front.jms;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DiscountObject {

    long id;
    int discount;

    public DiscountObject() {
    }

    public DiscountObject(long id, int discount) {
        this.id = id;
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
