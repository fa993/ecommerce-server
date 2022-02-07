package com.fa993.controllers;

import com.fa993.details.ItemSellDetails;
import com.fa993.details.OrderDetails;
import com.fa993.persistance.ECommerceUserRepository;
import com.fa993.persistance.ItemRepository;
import com.fa993.persistance.OrderRepository;
import com.fa993.pojos.ECommerceUser;
import com.fa993.pojos.Item;
import com.fa993.pojos.Order;
import com.fa993.utils.Utility;
import com.fa993.utils.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/sell")
public class SellController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ECommerceUserRepository eCommerceUserRepository;

    private static final int PAGE_LENGTH = Utility.getPageLength();

    @GetMapping("/all")
    public List<ItemSellDetails> getItemsSold(Principal principal, @RequestParam(name = "page") int page) {
        page-=1;
        return orderRepository.findItemDetailsWithOrders(principal.getName(), PageRequest.of(page, PAGE_LENGTH));
    }

    @PostMapping("/listitem")
    public void listItem(Authentication auth, @RequestBody Item i) throws Exception {
        ECommerceUser u = (ECommerceUser) auth.getPrincipal();
        i.setSeller(u);
        itemRepository.save(i);
    }

    @PostMapping("/delistitem")
    public void delistItem(@RequestBody Item i) {
        itemRepository.delete(i);
    }

    @GetMapping("allorders")
    @JsonView(View.UserView.class)
    public List<Order> getAllOrders(@RequestParam int page) {
        return orderRepository.findAllBy(PageRequest.of(page - 1, PAGE_LENGTH));
    }

    @GetMapping("myorders")
    public List<OrderDetails> getOrders(Authentication auth, @RequestParam int page) {
        ECommerceUser u = (ECommerceUser) auth.getPrincipal();
        return orderRepository.findOrdersForSeller(u.getId(), PageRequest.of(page - 1, PAGE_LENGTH));
    }


    @PostMapping("/deliver")
    public void deliver(@RequestBody List<String> orderIds) {
        orderRepository.findAllById(orderIds).forEach(t->{
            t.setCompleted(true);
            //TODO now make websocket signal
            NotificationEndpoint.sendToUser(t.getBuyer().getId(), t);
            NotificationEndpoint.sendToUser(t.getItem().getSeller().getId(), t);
            orderRepository.save(t);
        });
    }

}
