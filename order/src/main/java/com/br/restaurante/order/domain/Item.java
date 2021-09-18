package com.br.restaurante.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NotBlank
    private String name;

    @Positive
    private Integer quantity;

    private String note;

    public abstract TipoItemEnum getType();

}