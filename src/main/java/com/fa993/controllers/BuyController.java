package com.fa993.controllers;

import com.fa993.details.ItemBuyDetails;
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
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/buy")
public class BuyController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ECommerceUserRepository eCommerceUserRepository;

    private static final int PAGE_LENGTH = Utility.getPageLength();

    private final Map<Object, List<ItemBuyDetails>> carts = new HashMap<>();

    @GetMapping("/cart")
    public List<ItemBuyDetails> getItemsInCart(Authentication auth, @RequestParam(name = "page") int page) {
        page -= 1;
        int offset = page * PAGE_LENGTH;
        int limit = PAGE_LENGTH;
        carts.putIfAbsent(auth.getName(), new ArrayList<>());
        List<ItemBuyDetails> ls = carts.get(auth.getName());
        return ls.subList(offset, Math.min(offset + limit, ls.size()));
    }


    @PostMapping("/addtocart")
    public void addToCart(Authentication auth, @RequestBody ItemBuyDetails i) {
        carts.putIfAbsent(auth.getName(), new ArrayList<>());
        boolean found = false;
        for(ItemBuyDetails r : carts.get(auth.getName())) {
            if(r.equals(i)) {
                r.setQuantity(r.getQuantity() + i.getQuantity());
                found = true;
                if(r.getQuantity() == 0) {
                    removeFromCart(auth, i);
                }
                break;
            }
        }
        if(!found) {
            Item p = itemRepository.getById(i.getId());
            i.setName(p.getName());
            i.setCost(p.getCost());
            carts.get(auth.getName()).add(i);
        }
    }

    @PostMapping("/removefromcart")
    public boolean removeFromCart(Authentication auth, @RequestBody ItemBuyDetails i) {
        carts.putIfAbsent(auth.getName(), new ArrayList<>());
        Iterator<ItemBuyDetails> e = carts.get(auth.getName()).iterator();
        while(e.hasNext()) {
            ItemBuyDetails p = e.next();
            if(p.equals(i)) {
                e.remove();
                return true;
            }
        }
        return false;
    }

    @GetMapping("/available")
    @JsonView(View.UserView.class)
    public List<Item> getItemsForSale(@RequestParam(name = "page") int page) {
        return itemRepository.findAllBy(PageRequest.of(page - 1, PAGE_LENGTH));
    }

    @PostMapping("/checkout")
    public Double checkout(Authentication auth) throws Exception{
        carts.putIfAbsent(auth.getName(), new ArrayList<>());
        //TODO also put orders
        ECommerceUser u = (ECommerceUser) auth.getPrincipal();

        orderRepository.saveAll(carts.get(auth.getName()).stream().map(t -> {
            Order o = new Order();
            o.setBuyer(u);
            o.setCompleted(false);
            o.setItem(itemRepository.getById(t.getId()));
            o.setQuantity(t.getQuantity());
            return o;
        }).toList());
        Double sum = carts.get(auth.getName()).stream().mapToDouble(t -> t.getCost() * t.getQuantity()).sum();
        carts.get(auth.getName()).clear();
        return sum;
    }

}

class Pair<T, U> {
    private T first;
    private U second;

    public Pair() {
    }

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public U getSecond() {
        return second;
    }

    public void setSecond(U second) {
        this.second = second;
    }
}