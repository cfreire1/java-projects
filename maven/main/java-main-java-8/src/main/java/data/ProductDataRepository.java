package data;

import data.pojo.Product;

import java.util.List;

public interface ProductDataRepository {
    List<Product> getAllProducts();

    Product findProduct(int idProduct);
}
