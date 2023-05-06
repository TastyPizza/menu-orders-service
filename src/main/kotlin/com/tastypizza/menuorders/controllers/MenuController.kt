package com.tastypizza.menuorders.controllers

import com.tastypizza.menuorders.entities.MenuItem
import com.tastypizza.menuorders.entities.MenuItemOption
import com.tastypizza.menuorders.enums.ItemType
import com.tastypizza.menuorders.repositories.MenuItemRepository
import com.tastypizza.menuorders.services.MenuItemService
import org.aspectj.bridge.Message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/menu")
class MenuController() {

    @Autowired
    private lateinit var menuItemService: MenuItemService

    @GetMapping("/")
    fun getAllMenuItems(): List<MenuItem> {
        print("121221")
        return menuItemService.findAll()
    }
}