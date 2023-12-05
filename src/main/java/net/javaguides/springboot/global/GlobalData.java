package net.javaguides.springboot.global;

import net.javaguides.springboot.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Product> cart;
    static {
        cart = new ArrayList<>();
    }
}
