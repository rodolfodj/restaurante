package com.br.restaurante.order.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer quantity;
    private String note;

    @Enumerated(EnumType.STRING)
    private TipoItemEnum type;

    @ManyToOne
    private Order order;

}