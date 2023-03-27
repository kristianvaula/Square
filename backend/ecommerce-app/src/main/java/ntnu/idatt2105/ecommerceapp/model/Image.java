package ntnu.idatt2105.ecommerceapp.model;

/**
 * The class represents an image
 * Image contains getters and setters for all attributes
 */
public class Image {

    private String name;
    private String image;

    /**
     * Getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for image
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * Setter for image
     * @param image image
     */
    public void setImage(String image) {
        this.image = image;
    }
}
