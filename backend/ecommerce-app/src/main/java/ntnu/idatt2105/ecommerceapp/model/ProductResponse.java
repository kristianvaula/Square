package ntnu.idatt2105.ecommerceapp.model;


import java.util.List;

/**
 * Model class for formatting a product-response
 */
public class ProductResponse {

    private Product product;
    private List<Image> imageList;

    /**
     * Constructor for creating a product-response
     * @param product the product to be added
     * @param imageList the images to be added
     */
    public ProductResponse(Product product, List<Image> imageList) {
        this.product = product;
        this.imageList = imageList;
    }

    /**
     * Getter for product
     * @return product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Setter for product
     * @param product new product
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Getter for imageList
     * @return imageList containing all images to be used
     */
    public List<Image> getImageList() {
        return imageList;
    }

    /**
     * Setter for imageList
     * @param imageList new imageList
     */
    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }
}
