package com.fivemin.mzpc.service.admin;

import com.fivemin.mzpc.data.dto.CartDto;
import com.fivemin.mzpc.data.dto.FoodDto;
import com.fivemin.mzpc.data.dto.MembersDto;
import com.fivemin.mzpc.data.dto.OrdersDto;
import com.fivemin.mzpc.data.entity.Cart;
import com.fivemin.mzpc.data.entity.Orders;
import com.fivemin.mzpc.data.repository.CartRepository;
import com.fivemin.mzpc.data.repository.OrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
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



    public List<OrdersDto> getOrderList(String stoerCode) {

        List<Orders> ordersList = ordersRepository.findAllById(stoerCode);
        List<OrdersDto> ordersDtos = new ArrayList<>();
        List<CartDto> cartDtos = new ArrayList<>();

        for (Orders orders: ordersList) {

            for (Cart cart :orders.getCarts()) {
                FoodDto foodDto = FoodDto.builder()
                        .idx(cart.getFood().getIdx())
                        .code(cart.getFood().getCode())
                        .name(cart.getFood().getName())
                        .price(cart.getFood().getPrice())
                        .topping(cart.getFood().isTopping())
                        .build();

                MembersDto membersDto = MembersDto.builder()
                        .idx(cart.getMembers().getIdx())
                        .code(cart.getMembers().getCode())
                        .name(cart.getMembers().getName())
                        .mileage(cart.getMembers().getMileage())
                        .build();

                CartDto cartDto = CartDto.builder()
                        .idx(cart.getIdx())
                        .code(cart.getCode())
                        .membersDto(membersDto)
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
                    .cartDtos(cartDtos)
                    .build();

            ordersDtos.add(ordersDto);
        }

        return ordersDtos;
    }

    @Transactional
    public void completeOrder(OrdersDto ordersDto) {
        Orders orders = ordersRepository.findByCode(ordersDto.getCode());

        orders.setCookComplete(ordersDto.isCookComplete());
        orders.setPurchaseStatus(ordersDto.isPurchaseStatus());

        entityManager.merge(orders);

        entityManager.flush();
    }

    @Transactional
    public void rejectOrder(String orderCode) {
        Orders orders = ordersRepository.findByCode(orderCode);

        List<Cart> carts = cartRepository.findByOrdersIdx(orders.getIdx());

        for ( Cart cart : carts) {
            cart.setOrderComplete(false);
            entityManager.merge(cart);
        }

    }
}
