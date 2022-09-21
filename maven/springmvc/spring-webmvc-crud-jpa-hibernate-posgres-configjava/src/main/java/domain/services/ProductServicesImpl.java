package domain.services;


import domain.dao.repository.ProductRepository;
import domain.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServicesImpl implements ProductServices {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> allProduct() {
        return productRepository.allProduct();
    }

    @Override
    public List<Product> findProduct(int id) {
        return productRepository.findProduct(id);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    @Override
    public void editProduct(Product product) {
        productRepository.editProduct(product);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteProduct(id);
    }
}
