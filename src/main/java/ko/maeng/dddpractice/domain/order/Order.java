package ko.maeng.dddpractice.domain.order;

import ko.maeng.dddpractice.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "ORDERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {
    @Column
    private Long memberId;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String tel;
    @Column
    private LocalDateTime orderedAt;
    @Column
    private int price;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Setter(AccessLevel.NONE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private List<OrderLineItem> orderLineItems = new ArrayList<>();

    @Builder
    public Order(Long memberId, String name, String email, String tel, LocalDateTime orderedAt, int price, OrderStatus orderStatus) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.orderedAt = orderedAt;
        this.price = price;
        this.orderStatus = orderStatus;
    }

    public void addLineItem(OrderLineItem lineItem){
        lineItem.setOrder(this);
        this.orderLineItems.add(lineItem);
    }

    public void addLineItems(List<OrderLineItem> lineItems){
        lineItems.forEach(i -> addLineItem(i));
    }
}
