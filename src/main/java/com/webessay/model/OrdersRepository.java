package com.webessay.model;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Orders.class)
public interface OrdersRepository {
}
