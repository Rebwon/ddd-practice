package ko.maeng.dddpractice.domain.order;

import ko.maeng.dddpractice.config.QuerydslConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QuerydslConfiguration.class)
class OrderTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EntityManager em;

    @Test
    void createOrder() {
        Order order = Order.builder()
                .memberId(1l)
                .name("김철수")
                .email("chulsu@naver.com")
                .tel("010-2331-3231")
                .orderStatus(OrderStatus.ORDERED)
                .orderedAt(LocalDateTime.now())
                .build();

        OrderLineItem lineItem = OrderLineItem.builder()
                .productId(1l)
                .name("신신파프 핫파스")
                .count(3)
                .build();

        OrderOption orderOption = OrderOption.builder()
                .name("기본")
                .price(13000)
                .build();

        order.addLineItem(lineItem);
        lineItem.addOrderOption(orderOption);

        orderRepository.save(order);

        em.clear();

        List<Order> result = orderRepository.findAll();
        Order dbOrder = result.get(0);

        dbOrder.getOrderLineItems().clear();

        orderRepository.delete(dbOrder);

        assertThat(dbOrder.getOrderLineItems().size()).isEqualTo(0);
    }
}