package ntnu.idatt2105.ecommerceapp.model;


import java.util.List;

/**
 * Model class for formatting a product response
 */
public class ProductResponse {
    private Product product;
    private List<Image> imageList;

    public ProductResponse(Product product, List<Image> imageList) {
        this.product = product;
        this.imageList = imageList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }
}
