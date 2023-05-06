package com.tastypizza.menuorders.controllers

import com.tastypizza.menuorders.entities.MenuItem
import com.tastypizza.menuorders.entities.MenuItemOption
import com.tastypizza.menuorders.enums.ItemType
import com.tastypizza.menuorders.repositories.MenuItemRepository
import org.aspectj.bridge.Message
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/menu")
class MenuController(val menuItemRepository: MenuItemRepository) {
    @GetMapping("/")
    fun index(): List<MenuItemOption> {
        if (menuItemRepository.count() == 0L) {
            val menuItem = MenuItem(ItemType.OTHER, true, "Деревенская", "Деревенская с картошечкой")
            val menuItemOption = MenuItemOption()
            menuItem.menuItemOptions.add(menuItemOption)
            menuItemRepository.save(menuItem)

        }
//        menuItemRepository.save(MenuItem(ItemType.OTHER, false, "Тест", null))
        return emptyList()
    }
}