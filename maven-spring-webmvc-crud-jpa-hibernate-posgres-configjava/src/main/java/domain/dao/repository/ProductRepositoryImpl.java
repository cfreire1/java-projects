package domain.dao.repository;

import domain.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository{
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public List<Product> allProduct() {
        return entityManager.createQuery("SELECT a FROM Product a", Product.class).getResultList();
    }

    @Override
    public List<Product> findProduct(int id) {

        return entityManager
                .createQuery("SELECT a FROM Product a where a.id=:id", Product.class)
                .setParameter("id",id)
                .getResultList();
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        entityManager.persist(product);
    }

    @Override
    @Transactional
    public void editProduct(Product product) {
        entityManager.merge(product);
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        entityManager
                .createQuery("DELETE FROM Product a where a.id=:id")
                .setParameter("id",id)
                .executeUpdate();
    }


}
