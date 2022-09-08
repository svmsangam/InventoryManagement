package com.sbmsangam.project1.project1.size;

import com.sbmsangam.project1.project1.attributes.ProductAttribute;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "sizes")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "boolean default false",nullable = false)
    private boolean status;


    @OneToMany(mappedBy = "size",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<ProductAttribute> attribute = new ArrayList<>();


    public Size() {

    }
    public Size( String name, boolean status) {
        super();
        this.name = name;
        this.status = status;
    }



    public Integer getId() {
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Size{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
    public List<ProductAttribute> getProductAttributes() {
        return attribute;
    }

    public void setProductAttributes(List<ProductAttribute> productAttributes) {
        this.attribute = productAttributes;
    }
}
