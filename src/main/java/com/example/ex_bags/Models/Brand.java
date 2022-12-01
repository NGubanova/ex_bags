package com.example.ex_bags.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 2, max = 30, message = "Поле должно содержать от 2 до 30 символов")
    private String name;

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER)
    private Collection<Purchase> purchases;

    public Brand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Collection<Purchase> purchases) {
        this.purchases = purchases;
    }
}
