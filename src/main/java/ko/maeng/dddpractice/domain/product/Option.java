package ko.maeng.dddpractice.domain.product;

import ko.maeng.dddpractice.domain.common.BaseTimeEntity;
import ko.maeng.dddpractice.domain.product.vo.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "OPTION_TYPE")
public abstract class Option extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Money price;
}
