package com.fivemin.mzpc.service.admin;

import com.fivemin.mzpc.data.dto.CartDto;
import com.fivemin.mzpc.data.dto.FoodDto;
import com.fivemin.mzpc.data.dto.MembersDto;
import com.fivemin.mzpc.data.dto.OrdersDto;
import com.fivemin.mzpc.data.entity.Orders;
import com.fivemin.mzpc.data.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminOrderService {

    private OrdersRepository ordersRepository;

    @Autowired
    public AdminOrderService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<OrdersDto> getOrderList(String stoerCode) {

        List<Orders> ordersList = ordersRepository.findAllById(stoerCode);
        List<OrdersDto> ordersDtos = new ArrayList<>();

        for (Orders orders: ordersList) {
            FoodDto foodDto = FoodDto.builder()
                    .name(orders.getCart().getFood().getName())
                    .topping(orders.getCart().getFood().isTopping())
                    .build();

            MembersDto membersDto = MembersDto.builder()
                    .name(orders.getCart().getMembers().getName())
                    .mileage(orders.getCart().getMembers().getMileage())
                    .build();

            CartDto cartDto = CartDto.builder()
                    .membersDto(membersDto)
                    .foodDto(foodDto)
                    .build();

            OrdersDto ordersDto = OrdersDto.builder()
                    .code(orders.getCode())
                    .cookComplete(orders.isCookComplete())
                    .purchaseStatus(orders.isPurchaseStatus())
                    .payment(orders.getPayment())
                    .cartDto(cartDto)
                    .build();

            ordersDtos.add(ordersDto);
        }

        return ordersDtos;
    }
}
