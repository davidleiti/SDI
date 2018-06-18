package mpp.exam.template.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
//
//@NamedEntityGraphs({
//        @NamedEntityGraph(name = "graph.Products.lineItems",
//                attributeNodes = @NamedAttributeNode(value = "lineItems")
//                ),
//        @NamedEntityGraph(name = "graph.Products.lineItemsAndOrders",
//                attributeNodes = @NamedAttributeNode(value = "lineItems", subgraph = "lineItemsWithOrders"),
//                subgraphs = @NamedSubgraph(name = "lineItemsWithOrders",
//                        attributeNodes = @NamedAttributeNode(value = "order")))})
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = "lineItems", callSuper = true)
@EqualsAndHashCode(exclude = "lineItems", callSuper = true)
public class Product extends BaseEntity<Long>  implements Serializable {

    @Column(unique = true)
    private String name;

    private Integer price;
    private Integer stock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LineItem> lineItems;
}
