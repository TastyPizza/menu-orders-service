package com.tastypizza.menuorders

import com.tastypizza.menuorders.entities.MenuItem
import com.tastypizza.menuorders.repositories.*
import com.tastypizza.menuorders.services.MenuItemService
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ExtendWith

import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class MenuTestKotlin {


    private var menuItemRepository: MenuItemRepository = Mockito.mock(MenuItemRepository::class.java)


    private var menuItemService: MenuItemService = MenuItemService(menuItemRepository)


    @Test
    fun findAllTest() {

        val menuItems = listOf(
                MenuItem(name = "Pizza Margherita", description = "Classic Italian pizza"),
                MenuItem(name = "Spaghetti Bolognese", description = "Traditional Italian pasta dish"),
                MenuItem(name = "Burger", description = "Big mac burger"),
                MenuItem(name = "Cream soup", description = "Delicious soup from South Italia")


        )

        Mockito.`when`(menuItemRepository.findAll()).thenReturn(menuItems)

        val result = menuItemService.findAll()
        Assertions.assertEquals(menuItems, result)
    }

}