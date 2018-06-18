package mpp.exam.template.web.controller;
import mpp.exam.template.core.model.Order;
import mpp.exam.template.core.model.Product;
import mpp.exam.template.core.service.OrderService;
import mpp.exam.template.core.service.ProductService;
import mpp.exam.template.web.converter.ProductConverter;
import mpp.exam.template.web.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {

    public static final Logger log = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDto> getProducts(){
        List<Product> products = productService.getProducts();
        return products.stream().map(ProductConverter::convertModelToDto).collect(Collectors.toList());
    }

    @ResponseBody
    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public JSONResponse addOrder(
            @RequestBody SimpleStringRequestBody requestBody){
        log.info("addOrder(), email = {}", requestBody.getMessage());
        Order order = orderService.addOrder(requestBody.getMessage());
        log.info("addOrder() = {}", order);
        return new JSONResponse("" + order.getId());
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.POST)
    public JSONResponse addOrderLineItem(
            @PathVariable(name = "orderId") Long orderId,
            @RequestBody LineItemDto requestBody){
        log.info("addOrderLineItem() productId: {}, quantity: {}", requestBody.getProductId(), requestBody.getQuantity());
        orderService.addOrderLineItem(orderId, requestBody.getProductId(), requestBody.getQuantity());
        log.info("addOrderLineItem() done");
        return new JSONResponse("item has been added to the order!");
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.PUT)
    public JSONResponse submitOrder(
            @PathVariable(name = "orderId") Long orderId,
            @RequestBody SimpleStringRequestBody requestBody){
        log.info("submitOrder(), address = {}", requestBody.getMessage());
        orderService.setAddress(orderId, requestBody.getMessage());
        orderService.setOrderStatus(orderId, "submitted");
        log.info("submitOrder() done");
        return new JSONResponse("Order status has been set to submitted");
    }
}
