package com.fa993.pojos;

import javax.persistence.*;
import java.util.Objects;
import com.fa993.utils.Utility;
import com.fa993.utils.View;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "sales_order")
public class Order {

    @Id
    @Column(name = "order_id")
    @JsonView(View.UserView.class)
    private String orderId;

    @Column(name = "completed")
    @JsonView(View.UserView.class)
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    @JsonView(View.UserView.class)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "user_id")
    @JsonView(View.UserView.class)
    private ECommerceUser buyer;

    @Column(name = "quantity")
    @JsonView(View.UserView.class)
    private Integer quantity;

    public Order() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ECommerceUser getBuyer() {
        return buyer;
    }

    public void setBuyer(ECommerceUser buyer) {
        this.buyer = buyer;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @PrePersist
    public void resolveId() {
        if(this.orderId == null) {
            this.orderId = Utility.getId();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getOrderId().equals(order.getOrderId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId());
    }

}
