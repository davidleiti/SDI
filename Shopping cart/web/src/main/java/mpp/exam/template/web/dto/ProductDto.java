package mpp.exam.template.web.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductDto extends BaseDto{
    private String name;
    private Integer price;
}
