package ntnu.idatt2105.ecommerceapp.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * The class represents a product
 * Product contains getters and setters for attributes
 */
public class Product {

    @Id
    private Integer productId;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private Integer price;
    @Column
    private Integer sellerId;
    @Column
    private int used;
    @Column
    private Integer sold;
    @Column
    private Timestamp timeCreated;

    /**
     * Default constructor
     */
    @Autowired
    public Product() {
    }

    /**
     * Getter for productId
     * @return productId
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * Setter for productId
     * @param productId new productId
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * Getter for description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description
     * @param description new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for price
     * @return price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Setter for price
     * @param price new price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * Getter for sellerId
     * @return sellerId
     */
    public Integer getSellerId() {
        return sellerId;
    }

    /**
     * Setter for sellerId
     * @param sellerId new sellerId
     */
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    /**
     * Getter for title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for title
     * @param title new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * getts used
     * @return
     */
    public Integer getUsed() {
        return used;
    }


    public void setUsed(Integer used) {
        this.used = used;
    }

    /**
     * Getter for isUsed
     * @return isUsed
     */
    public int isUsed() {
        return used;
    }

    /**
     * Setter for isUsed
     * @param used new used value
     */
    public void setUsed(int used) {
        this.used = used;
    }

    /**
     * Getter for timeCreated
     * @return timeCreated timeStamp
     */
    public Timestamp getTimeCreated() {
        return timeCreated;
    }

    /**
     * Setter for timeCreated
     * @param timeCreated new timeStamp for timeCreated
     */
    public void setTimeCreated(Timestamp timeCreated) {
        this.timeCreated = timeCreated;
    }

}
