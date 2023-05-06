package com.tastypizza.menuorders.services

import com.tastypizza.menuorders.entities.MenuItem
import com.tastypizza.menuorders.repositories.MenuItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class MenuItemService {

    @Autowired
    private lateinit var menuItemRepository: MenuItemRepository


    fun findAll(): List<MenuItem>{
        return menuItemRepository.findAll()
    }
}