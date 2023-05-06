package com.tastypizza.menuorders.repositories

import com.tastypizza.menuorders.entities.MenuItem
import org.springframework.data.jpa.repository.JpaRepository

interface MenuItemRepository : JpaRepository<MenuItem, Int> {

}