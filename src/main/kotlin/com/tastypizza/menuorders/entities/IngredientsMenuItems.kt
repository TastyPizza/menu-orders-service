package com.tastypizza.menuorders.entities

import javax.persistence.*

@Entity
@Table(name = "ingredients_menuItems")
data class IngredientsMenuItems(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,


    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "ingredient", nullable = false)
    var ingredient: Ingredients,

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "menuItem", nullable = false)
    var menuItem: MenuItem,

    var count: Int = 0

)