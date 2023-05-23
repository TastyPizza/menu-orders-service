package com.tastypizza.menuorders.repositories

import com.tastypizza.menuorders.entities.IngredientsMenuItemOptions
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface IngredientsMenuItemOptionsRepository : JpaRepository<IngredientsMenuItemOptions, Long> {
    @Query(nativeQuery = true, value =
        "SELECT * FROM ingredients_menu_item_options WHERE menu_item_option_id in :menuItemOptions_id"
    )
    fun getAllByMenuItemOption(
        @Param("menuItemOptions_id") menuItemOptionsId: List<Long>
    ): List<IngredientsMenuItemOptions>
}


