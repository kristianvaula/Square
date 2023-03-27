package ntnu.idatt2105.ecommerceapp.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Blob;

/**
 * The class represents a product-image
 * ProdImage contains getters and setters for attributes
 */
public class ProdImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private Blob image;
    @Column
    private int productId;

    /**
     * Default constructor
     */
    @Autowired
    public ProdImage() {
    }

    /**
     * Getter for id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for image
     * @return image
     */
    public Blob getImage() {
        return image;
    }

    /**
     * Setter for image
     * @param image new image
     */
    public void setImage(Blob image) {
        this.image = image;
    }

    /**
     * Getter for productId
     * @return productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Setter for productId
     * @param productId new productID
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }
}