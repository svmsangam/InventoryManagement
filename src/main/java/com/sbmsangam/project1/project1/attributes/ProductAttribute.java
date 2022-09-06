package com.sbmsangam.project1.project1.attributes;

import com.sbmsangam.project1.project1.product.Product;
import com.sbmsangam.project1.project1.size.Size;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_attributes")
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private float price;
    @Column(nullable = false)
    private float mrp;
    @Column(nullable = false)
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;




    public ProductAttribute() {
    }

    public ProductAttribute(float price, float mrp, Integer quantity) {
        super();
        this.price = price;
        this.mrp = mrp;
        this.quantity = quantity;
    }
    public ProductAttribute( float price, float mrp, Integer quantity, Product product, Size size) {
        super();
        this.price = price;
        this.mrp = mrp;
        this.quantity = quantity;
        this.product = product;
        this.size = size;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getMrp() {
        return mrp;
    }

    public void setMrp(float mrp) {
        this.mrp = mrp;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public Product getProduct(){
        return product;
    }
    public void setProduct(Product product){
        this.product = product;
    }

    public Size getSize(){
        return size;
    }
    public void setSize(Size size){
        this.size= size;
    }

    @Override
    public String toString() {
        return "ProductAttribute{" +
                "id=" + id +
                ", price=" + price +
                ", mrp=" + mrp +
                ", quantity=" + quantity +
                ", size=" + size +
                '}';
    }
}
