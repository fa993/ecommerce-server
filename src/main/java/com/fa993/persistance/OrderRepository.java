package com.fa993.persistance;

import com.fa993.details.ItemSellDetails;
import com.fa993.details.OrderDetails;
import com.fa993.pojos.ECommerceUser;
import com.fa993.pojos.Item;
import com.fa993.pojos.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    @Query(
            value = "SELECT i.item_id as itemId, i.name as name, i.cost as cost, sum(o.quantity) as quantity from item i, sales_order o, user u  where i.item_id = o.item_id AND i.seller_id = u.user_id AND u.email = :email group by i.item_id, i.name, i.cost",
            nativeQuery = true
    )
    public List<ItemSellDetails> findItemDetailsWithOrders(@Param(value = "email") String userId, Pageable pageable);

    @Query(
            value = "SELECT so.order_id as orderId, so.completed as completed, so.quantity as quantity, so.buyer_id as buyerId, so.item_id as itemId, i.name as itemName, i.cost as itemCost FROM sales_order so, item i WHERE i.item_id =so.item_id AND i.seller_id = :seller",
            nativeQuery = true
    )
    public List<OrderDetails> findOrdersForSeller(@Param(value = "seller") String userId, Pageable pageable);

    public List<Order> findAllBy(Pageable pageable);
}
