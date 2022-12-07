package com.example.ex_bags.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

@Entity
public class Bag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Добавьте ссылку на картинку")
    private String image;

    @NotNull(message = "Введите цену сумки")
    private  Integer price;

    @NotBlank(message = "Заполните описание")
    @Size(min=6, max=255, message = "Длина описание должна составлять от 6 до 255 символов")
    private String description;

    @OneToOne(optional = true, cascade = CascadeType.REFRESH)
    @JoinColumn(name="purchase_id")
    private Purchase purchase;

    private boolean status;

    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
    private Cell cell;

    public Bag() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
