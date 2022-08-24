package com.sbmsangam.project1.project1.product;

import javax.persistence.*;

@Entity
@Table (name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String sdesc;
    @Column(nullable = false,name = "detail")
    private String detail;
    private Boolean status;

    public Product() {
    }

    public Product(Integer id, String name, String shortDesc, String detail, Boolean status) {
        super();
        this.id = id;
        this.name = name;
        this.sdesc = shortDesc;
        this.detail = detail;
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

    public String getSdesc() {
        return sdesc;
    }

    public void setSdesc(String shortDesc) {
        this.sdesc = shortDesc;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortDesc='" + sdesc + '\'' +
                ", detail='" + detail + '\'' +
                ", status=" + status +
                '}';
    }
}
