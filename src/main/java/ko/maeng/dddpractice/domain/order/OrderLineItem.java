package ko.maeng.dddpractice.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ko.maeng.dddpractice.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLineItem extends BaseEntity {
    private Long productId;
    private String name;
    private int count;

    @JsonIgnore
    @ManyToOne
    private Order order;

    @Setter(AccessLevel.NONE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "lineItem")
    private List<OrderOption> option = new ArrayList<>();

    @Builder
    public OrderLineItem(Long productId, String name, int count, Order order) {
        this.productId = productId;
        this.name = name;
        this.count = count;
        this.order = order;
    }

    public void setOrder(Order order) {
        if(order.getOrderLineItems() != null) {
            order.getOrderLineItems().remove(this);
        }
        this.order = order;
    }

    public void addOrderOption(OrderOption orderOption) {
        orderOption.setLineItem(this);
        this.option.add(orderOption);
    }

    public void addOrderOptions(List<OrderOption> orderOptions) {
        orderOptions.forEach(o -> addOrderOption(o));
    }
}
