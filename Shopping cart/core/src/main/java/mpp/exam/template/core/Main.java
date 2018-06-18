package mpp.exam.template.core;

import mpp.exam.template.core.model.Product;
import mpp.exam.template.core.repository.ProductRepository;
import mpp.exam.template.core.service.OrderService;
import mpp.exam.template.core.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("mpp.exam.template.core.config");
        ProductRepository productRepository = context.getBean(ProductRepository.class);
        System.out.println(productRepository.findAll());
        System.out.println("Hello");
    }
}
