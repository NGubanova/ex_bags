//package com.example.ex_bags.Models;
//
//import javax.persistence.*;
//
//@Entity
//public class Sale {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String datetime;
//
//    @ManyToOne(optional = true, cascade = CascadeType.ALL)
//    private User user;
//
//    @ManyToOne(optional = true, cascade = CascadeType.ALL)
//    private Delivery delivery;
//
//    public Sale() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getDatetime() {
//        return datetime;
//    }
//
//    public void setDatetime(String datetime) {
//        this.datetime = datetime;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Delivery getDelivery() {
//        return delivery;
//    }
//
//    public void setDelivery(Delivery delivery) {
//        this.delivery = delivery;
//    }
//}
