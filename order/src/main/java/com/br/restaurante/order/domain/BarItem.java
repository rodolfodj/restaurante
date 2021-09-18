package com.br.restaurante.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class BarItem extends Item {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @JsonIgnore
    @Override
    public TipoItemEnum getType() {
        return TipoItemEnum.BAR;
    }
}