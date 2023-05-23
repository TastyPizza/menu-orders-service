package com.tastypizza.menuorders.entities

import javax.persistence.*

@Entity
@Table(name = "ingredients_menuItemOptions")
data class IngredientsMenuItemOptions(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    var ingredient: Ingredients,

    @ManyToOne(fetch = FetchType.LAZY)
    var menuItemOption: MenuItemOption,

    var count: Int = 0

)