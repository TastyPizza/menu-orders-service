package com.tastypizza.menuorders.repositories

import com.tastypizza.menuorders.entities.Ingredients
import com.tastypizza.menuorders.entities.IngredientsMenuItems
import com.tastypizza.menuorders.util.CountsObject
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface IngredientsMenuItemsRepository : JpaRepository<IngredientsMenuItems, Long> {
    @Query(
        "SELECT new CountsObject(ingredients_menuItems.count, restaurant_ingredients.count) FROM ingredients_menuItems INNER JOIN " +
                "restaurant_ingredients ON ingredients_menuItems.ingredient = restaurant_ingredients.ingredient WHERE" +
                " ingredients_menuItems.menuItem =:menuItem_id " +
                " restaurant_ingredients.restaurant = :restaurant_id"
    )
    fun checkForIngredients(
        @Param("restaurant_id") restaurantId: Long,
        @Param("menuItem_id") menuItemId: Long
    ): Optional<List<CountsObject>>
}


