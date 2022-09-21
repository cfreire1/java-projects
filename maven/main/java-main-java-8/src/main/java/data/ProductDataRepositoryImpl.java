package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.pojo.Product;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ProductDataRepositoryImpl extends ObjectMapperUtil implements ProductDataRepository  {

    @Override
    public List<Product> getAllProducts(){
        return (List<Product>) this.getJsonObj("json-data/product-list-data.json",new TypeReference<List<Product>>() {});
    }

    @Override
    public Product findProduct(int idProduct) {
        List<Product> productList;
        productList = this.getJsonObj("json-data/product-list-data.json",new TypeReference<List<Product>>() {});
        return getObjectMapper().convertValue(productList.get(idProduct),Product.class);
    }

}
