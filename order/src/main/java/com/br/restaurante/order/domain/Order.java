package com.br.restaurante.order.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String waiter;
    @Column(name = "mesa")
    private Integer table;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    private List<Item> itens;

    @Enumerated(EnumType.STRING)
    private StatusEnum statusBar;

    @Enumerated(EnumType.STRING)
    private StatusEnum statusKitchen;

    public void addItem(Item novoItem) {
        if (itens == null) {
            itens = new ArrayList<>();
        }

        itens.add(novoItem);
        novoItem.setOrder(this);
    }
}
