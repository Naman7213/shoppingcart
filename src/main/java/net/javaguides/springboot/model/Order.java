package net.javaguides.springboot.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "orders")
public class Order {
    @Id
    private ObjectId _id;
    private ObjectId userId;
    private String productName;
    private String category;
    private Double price;
    private Date orderDate;

    public Order() {
        super();
    }

    public Order(ObjectId _id, ObjectId userId, String productName, String category, Double price,Date date) {
        super();
        this._id = _id;
        this.userId = userId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.orderDate=date;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
