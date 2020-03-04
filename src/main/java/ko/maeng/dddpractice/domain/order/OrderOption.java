package ko.maeng.dddpractice.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ko.maeng.dddpractice.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderOption extends BaseEntity {
    private String name;
    private int price;

    @JsonIgnore
    @ManyToOne
    private OrderLineItem lineItem;

    @Builder
    public OrderOption(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void setLineItem(OrderLineItem lineItem) {
        if(lineItem.getOption() != null){
            lineItem.getOption().remove(this);
        }
        this.lineItem = lineItem;
    }
}
