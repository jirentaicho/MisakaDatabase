package database.repository;

import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class MultiThreadSampleMdbRepositoryTest {

    private MisakaRepository<Integer, Product> misakaRepository;

    @BeforeEach
    public void prepare(){
        this.misakaRepository = new MdbRepository<>(Product.class);
        this.misakaRepository.deleteAll();
    }

    @Test
    public void test_multi_thread() throws InterruptedException {
        this.misakaRepository.insert(1,new Product(1,"洗剤",100));

        ExecutorService executor = Executors.newFixedThreadPool(1000);
        for(int i = 0; i < 1000; i++){
            executor.execute(()->this.update());
        }

        Thread.sleep(100);
        Product product = this.misakaRepository.findById(1).get();
        assertEquals(100100, product.price);
    }

    private synchronized void update(){
        Product product = this.misakaRepository.findById(1).get();
        Product newProduct = new Product(product.id,product.name,product.price);
        newProduct.addPrice(100);
        this.misakaRepository.update(newProduct.id, newProduct);
    }

}