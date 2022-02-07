package com.fa993.details;

import com.fa993.utils.View;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.Objects;

public class ItemBuyDetails {

    private String id;
    private String name;
    private Double cost;
    private int quantity;

    public ItemBuyDetails() {
    }

    public ItemBuyDetails(String id, String name, Double cost, int quantity) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemBuyDetails that = (ItemBuyDetails) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
