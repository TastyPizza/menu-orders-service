package com.tastypizza.menuorders.repositories

import com.tastypizza.menuorders.entities.RestaurantIngredients
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RestaurantsIngredientsRepository : JpaRepository<RestaurantIngredients, Long> {
    @Query(nativeQuery = true, value =
    "SELECT * FROM restaurant_ingredients WHERE restaurant_ingredients.restaurant_id =:restaurant_id"
    )
    fun getAllByRestaurantId(
        @Param("restaurant_id") restaurantId: Long
    ): List<RestaurantIngredients>
}


