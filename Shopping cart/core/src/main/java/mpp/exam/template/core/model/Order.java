package mpp.exam.template.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = "lineItems", callSuper = true)
@EqualsAndHashCode(exclude = "lineItems", callSuper = true)
@Table(name = "orders")
public class Order extends BaseEntity<Long> {

    @Column(unique = true)
    private Date date;
    private Integer totalPrice;

    @Embedded
    private ContactInfo contactInfo;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LineItem> lineItems;

    public enum Status{
        CART,
        SUBMITTED
    }

    public static boolean isValidStatus(String status){
        String val = status.toLowerCase().trim();
        return val.equals("cart") || val.equals("submitted");
    }

    public static Status stringToStatus(String status){
        String val = status.toLowerCase().trim();
        return (val.equals("cart")) ? Status.CART : Status.SUBMITTED;
    }

}
