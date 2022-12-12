package com.example.ex_bags.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Bag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Добавьте ссылку на картинку")
    private String image;

    @NotNull(message = "Введите цену сумки")
    private Integer price;

    @OneToOne(optional = true, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    private boolean status;

    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
    private Cell cell;

    @ManyToMany
    @JoinTable(name = "bag_delivery", joinColumns = @JoinColumn(name = "bag_id"), inverseJoinColumns = @JoinColumn(name = "delivery_id"))
    private List<Delivery> deliveries;

    @ManyToMany
    @JoinTable(name = "bag_user", joinColumns = @JoinColumn(name = "bag_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    public Bag() {
    }

    public void removeUsers(User u) {
        this.users.remove(u);
        u.getBags().remove(this);
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

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
