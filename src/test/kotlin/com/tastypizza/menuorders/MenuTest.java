package com.tastypizza.menuorders;

import com.tastypizza.menuorders.repositories.MenuItemRepository;
import com.tastypizza.menuorders.services.MenuItemService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MenuTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @InjectMocks
    private MenuItemService menuItemService;


}
