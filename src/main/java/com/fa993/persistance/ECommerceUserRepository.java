package com.fa993.persistance;

import com.fa993.pojos.ECommerceUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ECommerceUserRepository extends JpaRepository<ECommerceUser, String> {

    public Optional<ECommerceUser> findByEmail(String email);

}
