package net.breezeware.cosspringproject.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.breezeware.cosspringproject.food.dto.FoodMenuDto;
import net.breezeware.cosspringproject.order.dto.FoodItemDto;
import net.breezeware.cosspringproject.order.dto.OrderDto;
import net.breezeware.cosspringproject.order.dto.OrderViewDto;
import net.breezeware.cosspringproject.order.dto.PlaceOrderDto;
import net.breezeware.cosspringproject.order.entity.Order;
import net.breezeware.cosspringproject.order.service.api.OrderService;
import net.breezeware.cosspringproject.user.entity.UserAddressMap;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/foodMenus")
    List<FoodMenuDto> getFoodMenus(){
        return orderService.viewFoodMenus();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Order createOrder(@RequestBody OrderDto orderDto){
        return orderService.createOrder(orderDto);
    }
    @GetMapping("/{id}")
    OrderViewDto viewOrder(@PathVariable long id){
        return orderService.viewOrder(id);
    }

    @PutMapping("/{id}")
    void updateOrder(@PathVariable long id, @RequestBody List<FoodItemDto> foodItemDtos){
        orderService.updateOrder(id,foodItemDtos);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/address")
    UserAddressMap createAddress(@RequestBody UserAddressMap userAddressMap){
        return orderService.createAddress(userAddressMap);
    }
    @PutMapping("/placeOrder/{id}")
    OrderViewDto placeOrder(@PathVariable("id") long id, @RequestBody PlaceOrderDto placeOrderDto){
        log.info("Entering placeOrder()");
        return orderService.placeOrder(id, placeOrderDto);
    }

}
