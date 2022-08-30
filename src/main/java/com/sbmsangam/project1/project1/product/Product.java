package com.sbmsangam.project1.project1.product;



import com.sbmsangam.project1.project1.attributes.ProductAttribute;
import com.sbmsangam.project1.project1.brand.Brand;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


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
    @Column(nullable = false)
    private String detail;
    @Column(columnDefinition = "boolean default false",nullable = false)
    private Boolean status;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<ProductAttribute> productAttribute = new HashSet<>();
    @OneToOne
    @JoinColumn(name = "brand_id",referencedColumnName = "id")
    private Brand brand;
    public Product() {
    }

    public Product(String name, String shortDesc, String detail, Boolean status) {
        super();
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
    @Column(nullable = false,name = "detail")
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
    public Set<ProductAttribute> getProductAttribute() {
        return productAttribute;
    }

    public void setProductAttribute(Set<ProductAttribute> productAttribute) {
        this.productAttribute = productAttribute;
    }
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
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
