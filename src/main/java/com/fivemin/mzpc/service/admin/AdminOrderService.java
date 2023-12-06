package com.fivemin.mzpc.service.admin;

import com.fivemin.mzpc.data.dto.CartDto;
import com.fivemin.mzpc.data.dto.FoodDto;
import com.fivemin.mzpc.data.dto.MembersDto;
import com.fivemin.mzpc.data.dto.OrdersDto;
import com.fivemin.mzpc.data.entity.Orders;
import com.fivemin.mzpc.data.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminOrderService {

    private final OrdersRepository ordersRepository;

    private final EntityManager entityManager;


    @Autowired
    public AdminOrderService(OrdersRepository ordersRepository, EntityManager entityManager) {
        this.ordersRepository = ordersRepository;
        this.entityManager = entityManager;
    }


    public List<OrdersDto> getOrderList(String stoerCode) {

        List<Orders> ordersList = ordersRepository.findAllById(stoerCode);
        List<OrdersDto> ordersDtos = new ArrayList<>();

        for (Orders orders: ordersList) {
            FoodDto foodDto = FoodDto.builder()
                    .name(orders.getCart().getFood().getName())
                    .price(orders.getCart().getFood().getPrice())
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

    @Transactional
    public void completeOrder(OrdersDto ordersDto) {
        Orders orderList = ordersRepository.findByCode(ordersDto.getCode());

        Orders orders = Orders.builder()
                .idx(orderList.getIdx())
                .cookComplete(ordersDto.isCookComplete())
                .purchaseStatus(ordersDto.isPurchaseStatus())
                .build();


        ordersRepository.modifyByCode(orders.getIdx(),orders.isCookComplete(),orders.isPurchaseStatus());

        entityManager.flush();
    }

    private String makeCode(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'J'HHMMyyyymmddss");
        return currentDateTime.format(formatter);
    }
}
