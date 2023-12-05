package net.javaguides.springboot.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "products")
public class Product {

    @Id
    private ObjectId _id;
    @Field("productname")
    private String productName;
    @Field("imgurl")
    private String imgUrl;
    @Field("category")
    private String category;
    @Field("size")
    private String size;
    @Field("price")
    private Double price;
    @Field("description")
    private String description;
    @Field("isavailable")
    private boolean isAvailable;

    @Field("quantity")
    private Integer quantity;

    public Product() {
        super();
    }

    public Product(ObjectId _id, String productName, String imgUrl, String category, String size, Double price, String description, boolean isAvailable, Integer quantity) {
        this._id = _id;
        this.productName = productName;
        this.imgUrl = imgUrl;
        this.category = category;
        this.size = size;
        this.price = price;
        this.description = description;
        this.isAvailable = isAvailable;
        this.quantity = quantity;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + _id +
                ", productName='" + productName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", category='" + category + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }

}
