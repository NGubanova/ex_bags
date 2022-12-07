package com.example.ex_bags.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
    private Type type;

    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
    private Brand brand;

    @NotBlank(message = "Заполните название модели")
    private String model;

    private String color;

    private String status;

    @NotNull(message = "Введите цену модели")
    private Integer price;

    @OneToOne(optional = true,mappedBy = "purchase")
    private Bag bags;

    public Purchase() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Bag getBags() {
        return bags;
    }

    public void setBags(Bag bags) {
        this.bags = bags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
