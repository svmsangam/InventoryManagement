package com.sbmsangam.project1.project1.attributes;

import com.sbmsangam.project1.project1.product.Product;
import com.sbmsangam.project1.project1.size.Size;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_attributes")
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50,nullable = false)
    private String price;
    @Column(length = 50,nullable = false)
    private String mrp;
    @Column(length = 50,nullable = false)
    private String quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Size size;




    public ProductAttribute() {
    }

    public ProductAttribute(String price, String mrp, String quantity) {
        super();
        this.price = price;
        this.mrp = mrp;
        this.quantity = quantity;
    }
    public ProductAttribute( String price, String  mrp, String quantity, Product product, Size size) {
        super();
        this.price = price;
        this.mrp = mrp;
        this.quantity = quantity;
        this.product = product;
        this.size = size;
    }

    public ProductAttribute(Integer id, String price, String mrp, String quantity, Product product, Size size) {
        super();
        this.id = id;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
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
