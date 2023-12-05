package net.javaguides.springboot.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class Category {
    private ObjectId _id;
    private String categoryName;

    public Category() {
        super();
    }

    public Category(ObjectId _id, String categoryName) {
        super();
        this._id = _id;
        this.categoryName = categoryName;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "_id=" + _id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
