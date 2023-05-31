package com.tastypizza.menuorders;


import com.tastypizza.menuorders.repositories.*;
import com.tastypizza.menuorders.services.OrderService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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






}
