package ko.maeng.dddpractice.domain.product;

import ko.maeng.dddpractice.domain.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    private String informationJson;
    private String imageJson;
    private String thumbnailJson;

    @Enumerated(EnumType.STRING)
    @Column
    private ProductStatus productStatus;
}
