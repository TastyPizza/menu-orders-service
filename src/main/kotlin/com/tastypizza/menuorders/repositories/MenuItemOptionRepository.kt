package com.tastypizza.menuorders.repositories

import com.tastypizza.menuorders.entities.MenuItem
import com.tastypizza.menuorders.entities.MenuItemOption
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuItemOptionRepository : JpaRepository<MenuItemOption, Long> {

}