package com.br.restaurante.kitchen.repository;

import com.br.restaurante.kitchen.domain.KitchenOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenRepository extends MongoRepository<KitchenOrder, String> {
}