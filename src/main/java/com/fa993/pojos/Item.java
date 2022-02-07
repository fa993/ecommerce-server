package com.fa993.pojos;

import com.fa993.utils.Utility;
import com.fa993.utils.View;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "item_id")
    @JsonView(View.UserView.class)
    private String id;

    @Column(name = "name")
    @JsonView(View.UserView.class)
    private String name;

    @Column(name = "cost")
    @JsonView(View.UserView.class)
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "user_id")
    @JsonView(View.UserView.class)
    private ECommerceUser seller;

    @Transient
    @JsonView(View.UserView.class)
    private int quantity;

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

    public ECommerceUser getSeller() {
        return seller;
    }

    public void setSeller(ECommerceUser seller) {
        this.seller = seller;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @PrePersist
    private void resolveId() {
        if(this.id == null) {
            this.id = Utility.getId();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getId().equals(item.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
