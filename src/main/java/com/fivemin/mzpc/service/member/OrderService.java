package com.fivemin.mzpc.service.member;

import com.fivemin.mzpc.data.dto.OrdersDto;
import com.fivemin.mzpc.data.entity.Cart;
import com.fivemin.mzpc.data.entity.Food;
import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.data.entity.Orders;
import com.fivemin.mzpc.data.repository.CartRepository;
import com.fivemin.mzpc.data.repository.OrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PostLoad;
import java.util.List;

@Service
@Slf4j
public class OrderService {

    private final OrdersRepository ordersRepository;
    private final CartRepository cartRepository;

    @Autowired
    public OrderService(OrdersRepository ordersRepository, CartRepository cartRepository) {
        this.ordersRepository = ordersRepository;
        this.cartRepository = cartRepository;
    }

    public Orders checkoutOrder(Members member, String paymentMethod, String notes) {
        Long memberIdx = member.getIdx();
        // 유저별 주문 안한 카트 목록 가져오기 test
        List<Cart> cartList = cartRepository.findAllByMembersIdxAndOrderCompleteIsFalse(memberIdx);
        Integer totalCost = calculateTotalCost(cartList);

        Orders orders = Orders.builder()
                .code(Orders.generateUniqueCode())
                .cookComplete(false)
                .purchaseStatus(false)
                .payment(paymentMethod)
                .totalCost(totalCost)
                .note(notes)
                .carts(cartList)
                .store(member.getStore())
                .build();

        ordersRepository.save(orders);

        for (Cart carts : orders.getCarts()) {
            carts.setOrders(orders);
            carts.setOrderComplete(true);
            cartRepository.save(carts);
        }
        return orders;
    }

    public List<Orders> getOrdersByMemberIdx(Long memberIdx) {
        return ordersRepository.findOrdersByMemberIdx(memberIdx);
    }

    private int calculateTotalCost(List<Cart> cartList) {
        int totalCost = 0;

        for (Cart cart : cartList) {
            Food food = cart.getFood();
            if (food != null) {
                totalCost += food.getPrice();
            }
        }

        return totalCost;
    }

}
