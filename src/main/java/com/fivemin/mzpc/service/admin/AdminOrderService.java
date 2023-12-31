package com.fivemin.mzpc.service.admin;

import com.fivemin.mzpc.data.dto.CartDto;
import com.fivemin.mzpc.data.dto.FoodDto;
import com.fivemin.mzpc.data.dto.OrdersDto;
import com.fivemin.mzpc.data.entity.Cart;
import com.fivemin.mzpc.data.entity.Orders;
import com.fivemin.mzpc.data.repository.CartRepository;
import com.fivemin.mzpc.data.repository.OrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AdminOrderService {

    private final OrdersRepository ordersRepository;

    private final CartRepository cartRepository;

    private final EntityManager entityManager;


    @Autowired
    public AdminOrderService(OrdersRepository ordersRepository, CartRepository cartRepository, EntityManager entityManager) {
        this.ordersRepository = ordersRepository;
        this.cartRepository = cartRepository;
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public List<OrdersDto> getOrderList(String storeCode) {
        List<Orders> ordersList = ordersRepository.findAllCookIncompleteByStoreCode(storeCode);
        List<OrdersDto> ordersDtos = new ArrayList<>();

        if (ordersList != null) {
            for (Orders orders: ordersList) {
                if (orders != null && orders.getCarts() != null && orders.getMembers() != null) {
                    List<CartDto> cartDtos = new ArrayList<>();

                    for (Cart cart : orders.getCarts()) {
                        FoodDto foodDto = FoodDto.builder()
                                .idx(cart.getFood().getIdx())
                                .code(cart.getFood().getCode())
                                .name(cart.getFood().getName())
                                .price(cart.getFood().getPrice())
                                .topping(cart.getFood().isTopping())
                                .build();

                        CartDto cartDto = CartDto.builder()
                                .idx(cart.getIdx())
                                .code(cart.getCode())
                                .foodDto(foodDto)
                                .orderComplete(cart.isOrderComplete())
                                .build();

                        cartDtos.add(cartDto);

                    }

                    OrdersDto ordersDto = OrdersDto.builder()
                            .code(orders.getCode())
                            .cookComplete(orders.isCookComplete())
                            .purchaseStatus(orders.isPurchaseStatus())
                            .payment(orders.getPayment())
                            .note(orders.getNote())
                            .totalCost(orders.getTotalCost())
                            .cartDtos(cartDtos)
                            .createdAt(orders.getCreatedAt())
                            .memberName(orders.getMembers().getName())
                            .build();

                    ordersDtos.add(ordersDto);
                }

            }
            return ordersDtos;

        } else {
            throwOrderError();
        }

        return null;
    }


    @Transactional
    public void completeOrder(OrdersDto ordersDto) {
        Orders orders = ordersRepository.findByCode(ordersDto.getCode());

        if (orders != null) {
            orders.setCookComplete(ordersDto.isCookComplete());
            orders.setPurchaseStatus(ordersDto.isPurchaseStatus());

            entityManager.merge(orders);

            entityManager.flush();
        } else {
            throwOrderError();
        }

    }

    @Transactional
    public void rejectOrder(String orderCode) {
        Orders orders = ordersRepository.findByCode(orderCode);
        List<Cart> carts = cartRepository.findByOrdersIdx(orders.getIdx());

        if (carts != null) {
            for (Cart cart : carts) {
                cart.setOrderComplete(false);
                cartRepository.save(cart);
            }
            deleteOrder(orders, carts);
        } else {
            throwOrderError();
        }

    }

    @Transactional
    public void deleteOrder(Orders orders, List<Cart> carts) {

        for ( Cart cart : carts) {
            cart.setOrders(null);
            cartRepository.save(cart);

        }
        ordersRepository.delete(orders);

    }

    @Transactional(readOnly = true)
    public boolean checkOrder() {
        List<Cart> carts = cartRepository.findAll();
        boolean Orderstatus = true;

        if (carts != null) {
            for (Cart cart : carts) {
                if (!cart.isOrderComplete()) {
                    Orderstatus = false;
                    break;
                }

            }
            return Orderstatus;

        } else {
            throw new IllegalArgumentException("Cart not found");
        }

    }

    @Transactional(readOnly=true)
    public boolean alarmOrder(String storeCode) {
        List<Cart> carts = cartRepository.findAllByStoreIdx(storeCode);
        List<Orders> orders = ordersRepository.findAllByStoreCode(storeCode);

        return !carts.isEmpty() && !orders.isEmpty();

    }

    private void throwOrderError() {
        throw new IllegalArgumentException("Order not found");
    }

}
