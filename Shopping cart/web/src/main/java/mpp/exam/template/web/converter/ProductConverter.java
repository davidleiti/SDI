package mpp.exam.template.web.converter;

import mpp.exam.template.core.model.Product;
import mpp.exam.template.web.dto.ProductDto;

public class ProductConverter {
    public static Product convertDtoToModel(ProductDto dto){
        Product product = Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .build();
        product.setId(dto.getId());
        return product;
    }

    public static ProductDto convertModelToDto(Product product){
        ProductDto dto = ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .build();
        dto.setId(product.getId());
        return dto;
    }
}
