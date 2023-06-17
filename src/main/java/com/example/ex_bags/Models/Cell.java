package com.example.ex_bags.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Cell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Введите столбец")
    private Integer column_;

    @NotNull(message = "Введите ряд")
    private Integer row_;

    public Cell() {
    }

    public Cell(Long id, Integer column_, Integer row_) {
        this.id = id;
        this.column_ = column_;
        this.row_ = row_;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getColumn_() {
        return column_;
    }

    public void setColumn_(Integer column_) {
        this.column_ = column_;
    }

    public Integer getRow_() {
        return row_;
    }

    public void setRow_(Integer row_) {
        this.row_ = row_;
    }
}
