package mpp.exam.template.core.service;

import mpp.exam.template.core.model.ContactInfo;
import mpp.exam.template.core.model.LineItem;
import mpp.exam.template.core.model.Order;
import mpp.exam.template.core.model.Product;
import mpp.exam.template.core.repository.OrderRepository;
import mpp.exam.template.core.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    public static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Order addOrder(String email) {
        log.info("addOrder() entered, email = {}", email);
        ContactInfo info = ContactInfo.builder().email(email).build();
        Order o = Order.builder()
                .date(new Date())
                .contactInfo(info)
                .status(Order.stringToStatus("cart"))
                .build();
        Order result = orderRepository.save(o);
        log.info("addOrder() = {}", result);
        return result;
    }

    @Transactional
    @Override
    public void addOrderLineItem(Long orderId, Long productId, Integer quantity) {
        log.info("addOrderLineItem entered, orderId = {}, productId = {}", orderId, productId);
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        orderOpt.orElseThrow(() -> new RuntimeException("No such order"));
        Order order = orderOpt.get();
        Optional<Product> productOpt = productRepository.findById(productId);
        productOpt.orElseThrow(() -> new RuntimeException("No such product"));
        Product product = productOpt.get();
        LineItem newItem = new LineItem();
        newItem.setProduct(product);
        newItem.setOrder(order);
        newItem.setQuantity(quantity);
        order.getLineItems().add(newItem);
        log.info("addOrderLineItem lineItem = {}", newItem);
    }

    @Transactional
    @Override
    public void setOrderStatus(Long id, String status) {
        log.info("setOrderStatus entered, id = {}, status = {}", id, status);
        Optional<Order> orderOpt = orderRepository.findById(id);
        orderOpt.orElseThrow(() -> new RuntimeException("No such order"));
        Order order = orderOpt.get();
        order.setStatus(Order.stringToStatus(status));
        log.info("setOrderStatus order ={}", order);
    }

    @Transactional
    @Override
    public void setAddress(Long id, String address) {
        log.info("setAddress entered, id = {}, address = {}", id, address);
        Optional<Order> orderOpt = orderRepository.findById(id);
        orderOpt.orElseThrow(() -> new RuntimeException("No such order"));
        Order order = orderOpt.get();
        order.getContactInfo().setAddress(address);
        log.info("setAddress order = {}", order);
    }
}
