package com.tastypizza.menuorders.repositories

import com.tastypizza.menuorders.entities.MenuItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuItemRepository : JpaRepository<MenuItem, Long> {

}