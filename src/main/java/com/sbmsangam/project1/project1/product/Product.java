package com.sbmsangam.project1.project1.product;



import com.sbmsangam.project1.project1.attributes.ProductAttribute;
import com.sbmsangam.project1.project1.brand.Brand;
import com.sbmsangam.project1.project1.size.Size;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table (name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT",nullable = false)
    private String sdesc;
    @Column(columnDefinition = "TEXT",nullable = false)
    private String detail;
    @Column(columnDefinition = "boolean default false",nullable = false)
    private Boolean status;
    @Column(length = 45,nullable = true)
    private String image;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<ProductAttribute> attribute = new ArrayList<>();


    @OneToOne
    @JoinColumn(name = "brand_id",referencedColumnName = "id")
    private Brand brand;
    public Product() {
    }

    public Product(String name, String shortDesc, String detail, Boolean status,String image) {
        super();
        this.name = name;
        this.sdesc = shortDesc;
        this.detail = detail;
        this.status = status;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public List<ProductAttribute> getAttribute() {
        return attribute;
    }

    public void setAttribute(List<ProductAttribute> attribute) {
        this.attribute = attribute;
    }
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    @Transient
    public String getImagePath(){
        if(image == null || id == null) return null;

        return "/product-image"+"/"+id+"/"+image;
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

    public void addAttributes(Size size,String quantity,String mrp,String price){
        this.attribute.add(new ProductAttribute(price,mrp,quantity,this,size));
    }
    public void updateAttribute(Integer id,String price, String mrp, String quantity,Size size ){
        this.attribute.add(new ProductAttribute(id,price,mrp,quantity,this,size));
    }
}
