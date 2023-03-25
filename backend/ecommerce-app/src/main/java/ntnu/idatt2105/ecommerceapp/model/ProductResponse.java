package ntnu.idatt2105.ecommerceapp.model;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ProductResponse {
    private Product product;
    private List<String> imageList;

    public ProductResponse(Product product, List<Blob> blobList) throws SQLException {
        this.product = product;
        this.imageList = new ArrayList<>();
        for (Blob blob : blobList) {
            String base64Image = Base64.getEncoder().encodeToString(blob.getBytes(1, (int)blob.length()));
            this.imageList.add("data:image/png;base64,"+base64Image);
        }
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
}
