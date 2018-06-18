package mpp.exam.template.core.service;

import mpp.exam.template.core.model.Product;
import mpp.exam.template.core.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    public static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        log.info("getProducts entered");
        List<Product> products = productRepository.findAll();
        log.info("getProducts = {}", products);
        return products;
    }
}
