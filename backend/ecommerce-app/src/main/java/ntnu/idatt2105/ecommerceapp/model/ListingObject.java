package ntnu.idatt2105.ecommerceapp.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class ListingObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private Product product;
    @Column
    private String username;
    @Column
    private List<Integer> subcategories;

    @Autowired
    public ListingObject() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Integer> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Integer> subcategories) {
        this.subcategories = subcategories;
    }

}
