package mpp.exam.template.core.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@IdClass(LineItemPk.class)
@EqualsAndHashCode(exclude = {"order"})
@ToString(exclude = {"order", "product"})
public class LineItem {
    private Integer quantity;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
