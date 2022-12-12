package com.example.ex_bags.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 2, max = 30, message = "Поле должно содержать от 2 до 30 символов")
    private String name;

    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 5, max = 30, message = "Поле должно содержать от 5 до 30 символов")
    @Pattern(regexp = "^(?=.{1,64}@)" +
            "[A-Za-z0-9_-]" +
            "+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]" +
            "+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
    message = "Не корректно введена почта")
    private String username;

//    @Pattern(regexp = "^[+][0-9][(][0-9][0-9][0-9][)][0-9][0-9][0-9][-][0-9][0-9][-][0-9][0-9]$",
//            message = "Телефон должен быть записана в формате +7(___)-___-__-__")
    private String phone;

    @Pattern(regexp = "^(?=.*[0-9])" +
        "(?=.*[a-z])" +
        "(?=.*[A-Z])" +
        "(?=.*[!@#$%^&+=])" +
        "(?=\\S+$)" +
        ".{6,255}", message = "Пароль должен быть не меньше 6 символов,"+ "\n" +
            "иметь числа и латинские строчные и заглавные буквы," + "\n" +
            "а также специальные символы")
    private String password;

    private Boolean active;

    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
    private Post post;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Collection<Delivery> deliveries;

    @ManyToMany
    @JoinTable(name="bag_user",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="bag_id"))
    private List<Bag> bags;

    public User() {
    }

    public User(String name, String username, Post post) {
        this.name = name;
        this.username = username;
        this.post = post;
    }

    public void removeBag(Bag b){
        this.bags.remove(b);
        b.getUsers().remove(this);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Collection<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(Collection<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public List<Bag> getBags() {
        return bags;
    }

    public void setBags(List<Bag> bags) {
        this.bags = bags;
    }

}