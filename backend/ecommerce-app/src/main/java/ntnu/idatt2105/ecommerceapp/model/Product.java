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
    private int productId;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private int price;
    @Column
    private boolean used;
    @Column
    private int sellerId;
    @Column
    private Integer buyerId;
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
    public int getProductId() {
        return productId;
    }

    /**
     * Setter for productId
     * @param productId new productId
     */
    public void setProductId(int productId) {
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
    public int getPrice() {
        return price;
    }

    /**
     * Setter for price
     * @param price new price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Getter for sellerId
     * @return sellerId
     */
    public int getSellerId() {
        return sellerId;
    }

    /**
     * Setter for sellerId
     * @param sellerId new sellerId
     */
    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * Getter for buyerId
     * @return buyerId
     */
    public Integer getBuyerId() {
        return buyerId;
    }

    /**
     * Setter for buyerId
     * @param buyerId new buyerId
     */
    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
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
     * Getter for isUsed
     * @return isUsed
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * Setter for isUsed
     * @param used new used value
     */
    public void setUsed(boolean used) {
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
