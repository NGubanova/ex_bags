package com.example.ex_bags.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private User user;

    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 2, max = 255, message = "Поле должно содержать от 2 до 255 символов")
    private String address;

    @NotBlank(message = "Поле не должно быть пустым!")
    private String day;

    @NotBlank(message = "Поле не должно быть пустым!")
    private String period;

    @NotNull(message = "Введите цену дотавки")
    private Integer price;

    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 2, max = 255, message = "Поле должно содержать от 2 до 255 символов")
    private String comment;

    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 2, max = 30, message = "Поле должно содержать от 2 до 30 символов")
    private String type;

    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 2, max = 30, message = "Поле должно содержать от 2 до 30 символов")
    private String payment;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.EAGER)
    private Collection<Sale> sales;

    public Delivery() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Collection<Sale> getSales() {
        return sales;
    }

    public void setSales(Collection<Sale> sales) {
        this.sales = sales;
    }
}
