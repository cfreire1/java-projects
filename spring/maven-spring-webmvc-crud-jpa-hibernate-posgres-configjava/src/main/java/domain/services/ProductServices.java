package domain.services;


import domain.entity.Product;

import java.util.List;

public interface ProductServices {

    List<Product> allProduct();

    List<Product> findProduct(int id);

    void addProduct(Product product);

    void editProduct(Product product);

    void deleteProduct(int id);
}
