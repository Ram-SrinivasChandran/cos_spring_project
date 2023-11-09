package net.breezeware.cosspringproject.order.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.breezeware.cosspringproject.food.dto.FoodMenuDto;
import net.breezeware.cosspringproject.order.dto.FoodItemDto;
import net.breezeware.cosspringproject.order.dto.OrderDto;
import net.breezeware.cosspringproject.order.dto.OrderViewDto;
import net.breezeware.cosspringproject.order.dto.PlaceOrderDto;
import net.breezeware.cosspringproject.order.entity.Order;
import net.breezeware.cosspringproject.order.service.api.OrderService;
import net.breezeware.cosspringproject.user.entity.UserAddressMap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/foodMenus")
    List<FoodMenuDto> retrieveAvailableFoodMenusForToday() {
        return orderService.retrieveAvailableFoodMenusForToday();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Order createOrder(@RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @GetMapping("/{order-id}")
    OrderViewDto viewOrder(@PathVariable(name = "order-id", required = true) long orderId) {
        return orderService.viewOrder(orderId);
    }

    @PutMapping("/{order-id}")
    void updateOrder(@PathVariable(name = "order-id", required = true) long orderId,
            @RequestBody List<FoodItemDto> foodItemDtos) {
        orderService.updateOrder(orderId, foodItemDtos);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/address")
    UserAddressMap createAddress(@RequestBody UserAddressMap userAddressMap) {
        return orderService.createAddress(userAddressMap);
    }

    @PutMapping("/placeOrder/{order-id}")
    OrderViewDto placeOrder(@PathVariable(name = "order-id", required = true) long orderId,
            @RequestBody PlaceOrderDto placeOrderDto) {
        log.info("Entering placeOrder()");
        return orderService.placeOrder(orderId, placeOrderDto);
    }

    @PutMapping("/cancelOrder/{order-id}")
    void cancelOrder(@PathVariable(name = "order-id", required = true) long orderId) {
        orderService.cancelOrder(orderId);
    }

    @GetMapping("/activeOrders")
    List<OrderViewDto> activeOrders(@RequestParam(value = "user-id") long id) {
        return orderService.viewActiveOrders(id);
    }

    @GetMapping("/receivedOrder/{order-id}")
    OrderViewDto receivedOrder(@RequestParam(value = "user-id") long userId,
            @PathVariable(name = "order-id", required = true) long orderId) {
        return orderService.viewReceivedOrder(userId, orderId);
    }

    @PutMapping("/orderPrepared/{order-id}")
    void changeOrderStatusToOrderPrepared(@RequestParam(value = "user-id") long userId,
            @PathVariable(name = "order-id", required = true) long orderId) {
        orderService.changeStatusToWaitingForDelivery(userId, orderId);
    }

    @PutMapping("/pendingDelivery/{order-id}")
    void changeOrderStatusToDeliveryPending(@RequestParam(value = "user-id") long userId,
            @PathVariable(name = "order-id", required = true) long orderId) {
        orderService.changeStatusToPendingDelivery(userId, orderId);
    }

    @PutMapping("/orderDelivered/{order-id}")
    void changeOrderStatusToOrderDelivered(@RequestParam(value = "user-id") long userId,
            @PathVariable(name = "order-id", required = true) long orderId) {
        orderService.changeStatusToOrderDelivered(userId, orderId);
    }

    @GetMapping("/cancelledOrders")
    List<OrderViewDto> cancelledOrders(@RequestParam(value = "user-id") long id) {
        return orderService.viewCancelledOrders(id);
    }

    @GetMapping("/cancelledOrder/{order-id}")
    OrderViewDto cancelledOrder(@RequestParam(value = "user-id") long userId,
            @PathVariable(name = "order-id", required = true) long orderId) {
        return orderService.viewCancelledOrder(userId, orderId);
    }

    @GetMapping("/completedOrders")
    List<OrderViewDto> completedOrders(@RequestParam(value = "user-id") long id) {
        return orderService.viewCompletedOrders(id);
    }

    @GetMapping("/completedOrder/{order-id}")
    OrderViewDto completedOrder(@RequestParam(value = "user-id") long userId,
            @PathVariable(name = "order-id", required = true) long orderId) {
        return orderService.viewCompletedOrder(userId, orderId);
    }
}
