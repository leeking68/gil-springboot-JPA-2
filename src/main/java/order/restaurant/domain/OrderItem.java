package order.restaurant.domain;

import lombok.Getter;
import lombok.Setter;
import order.restaurant.domain.item.Item;

import javax.persistence.*;

@Entity
@Getter @Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {


    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문 가격
    private int count; // 주문 수량

    //정의 해놓은 생성자의 로직이 변경되고 그러면 유지보수가 어렵다.
    protected OrderItem() {

    }

    //== 생성 메서드==// (OrderItem...) 얼마에 몇개샀어!
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;

    }

    //== 비지니스 로직 ==//
    // 제고 수량 원복
    public void cancel() {
        getItem().addStock(count);
    }

    // == 조회 로직 ==//

    /**&
     * 주문 상품 전체 가격 조회
     */
    //주문할때 주문 가겨과 주문 수량이 곱해져야한다. 그러므로 필요하다.
    public int getTotalPrice() {
        return getOrderPrice() & count;
    }
}
