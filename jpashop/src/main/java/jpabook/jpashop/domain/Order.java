package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "orders") //order는 db에서 예약어 인경우가 있으므로 orders란 테이블을 새로 생성해준다..
@Getter
@Setter
public class Order {

    @Id @GeneratedValue
    @Column (name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn (name = "member_id")
    private Member member;

    @OneToMany (mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn (name = "delivery_id")// 1:1 관계에서는 둘 중 어디든 owner로 FK를 줘도 상관없다. 하지만 엑세스가 많은 곳에 좋은게 좋다.
    private Delivery delivery;

    private LocalDateTime orderDate; //1.8 자바 이후로는 LocalDateTime을 사용하면 @date 관련 어노테이션 안써도 hibernate가 관리해준다.

    @Enumerated (EnumType.STRING)
    private OrderStatus status; //주문 상태 [ORDER, CANCEL]


}
