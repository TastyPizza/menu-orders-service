package com.tastypizza.menuorders.repositories

import com.tastypizza.menuorders.entities.IngredientsMenuItemOptions
import com.tastypizza.menuorders.util.CountsObject
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface IngredientsMenuItemOptionsRepository : JpaRepository<IngredientsMenuItemOptions, Long> {
    @Query(
        "SELECT new CountsObject(ingredients_menuItemOptions.count, restaurant_ingredients.count, ingredients_menuItemOptions.menuItemOption) FROM ingredients_menuItemOptions INNER JOIN " +
                "restaurant_ingredients ON ingredients_menuItemOptions.ingredient = restaurant_ingredients.ingredient WHERE" +
                " ingredients_menuItemOptions.menuItemOption IN :menuItemOption_id " +
                " restaurant_ingredients.restaurant = :restaurant_id"
    )
    fun checkForIngredients(
        @Param("restaurant_id") restaurantId: Long,
        @Param("menuItemOptions_id") menuItemOptionsId: List<Long>
    ): Optional<List<CountsObject>>
}


