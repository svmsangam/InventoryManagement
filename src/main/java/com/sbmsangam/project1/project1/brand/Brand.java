package com.sbmsangam.project1.project1.brand;

import com.sbmsangam.project1.project1.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(columnDefinition = "boolean default false",nullable = false)
    private Boolean status;


    public Brand(){
    }
    public Brand(Integer id, String name, Boolean status) {
        super();
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
