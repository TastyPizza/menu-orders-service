package com.tastypizza.menuorders;


import com.tastypizza.menuorders.entities.MenuItem;
import com.tastypizza.menuorders.entities.MenuItemOption;
import com.tastypizza.menuorders.entities.Order;
import com.tastypizza.menuorders.entities.OrderItem;
import com.tastypizza.menuorders.enums.ItemType;
import com.tastypizza.menuorders.enums.OrderStatus;
import com.tastypizza.menuorders.dto.OrderStatusesDTO;
import com.tastypizza.menuorders.repositories.*;
import com.tastypizza.menuorders.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private MenuItemOptionRepository menuItemOptionRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private IngredientsMenuItemOptionsRepository ingredientsMenuItemOptionsRepository;

    @Mock
    private RestaurantsIngredientsRepository restaurantsIngredientsRepository;

    @Mock
    private RestaurantsRepository restaurantsRepository;

    @InjectMocks
    private OrderService orderService;



    @Test
    void todayOrdersTest(){
        Order order1 = new Order();
        order1.setOrderDate(LocalDateTime.now());
        order1.setPacking(true);


        Order order2 = new Order();
        order2.setOrderDate(LocalDateTime.now());
        order2.setPacking(false);

        var startDate = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        var endDate = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);

        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);

        when(orderRepository.findAllByOrderDateBetween(startDate, endDate)).thenReturn(orderList);
        List<Order> orderListFromService = this.orderService.todayOrders(1);

        assertFalse(orderListFromService.isEmpty());

    }





}
