package com.br.restaurante.order.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String waiter;

    @NotNull
    @Positive
    private Integer tableNo;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL)
    private List<BarItem> barItems;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL)
    private List<KitchenItem> kitchenItems;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusEnum statusBar;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusEnum statusKitchen;

    public void adicionarNovoBarItem(BarItem novoItem) {
        if (barItems == null) {
            barItems = new ArrayList<>();
        }

        barItems.add(novoItem);
        novoItem.setOrder(this);
    }

    public void adicionarNovoKitchenItem(KitchenItem novoItem) {
        if (kitchenItems == null) {
            kitchenItems = new ArrayList<>();
        }

        kitchenItems.add(novoItem);
        novoItem.setOrder(this);
    }

    public void done(TipoItemEnum type) {
        if (type.equals(TipoItemEnum.BAR)) {
            this.statusBar = StatusEnum.DONE;
        } else {
            this.statusKitchen = StatusEnum.DONE;
        }
    }
}