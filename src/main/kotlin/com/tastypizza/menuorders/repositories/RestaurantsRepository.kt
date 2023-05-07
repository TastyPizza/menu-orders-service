package com.tastypizza.menuorders.repositories

import com.tastypizza.menuorders.entities.Ingredients
import com.tastypizza.menuorders.entities.Restaurants
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface RestaurantsRepository : JpaRepository<Restaurants, Long> {
}