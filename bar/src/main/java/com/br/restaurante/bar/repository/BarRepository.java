package com.br.restaurante.bar.repository;

import com.br.restaurante.bar.domain.BarOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarRepository extends MongoRepository<BarOrder, String> {
}