package mpp.exam.template.core.service;

import mpp.exam.template.core.model.LineItem;
import mpp.exam.template.core.model.Order;

import java.util.Date;

public interface OrderService {
    Order addOrder(String email);
    void addOrderLineItem(Long orderId, Long productId, Integer quantity);
    void setOrderStatus(Long id, String status);
    void setAddress(Long id, String address);
    //Order getOrderByDate(Date date);
}
