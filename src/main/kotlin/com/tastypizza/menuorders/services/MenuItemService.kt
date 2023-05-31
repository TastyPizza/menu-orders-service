package com.tastypizza.menuorders.services

import com.tastypizza.menuorders.entities.MenuItem
import com.tastypizza.menuorders.repositories.MenuItemRepository
import com.tastypizza.menuorders.repositories.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class MenuItemService @Autowired constructor(private val menuItemRepository: MenuItemRepository) {


    fun findAll(): List<MenuItem> {
        return menuItemRepository.findAll()
    }
}