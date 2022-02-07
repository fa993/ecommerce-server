package com.fa993.persistance;

import com.fa993.details.ItemBuyDetails;
import com.fa993.pojos.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    public List<Item> findAllBy(Pageable p);

}
