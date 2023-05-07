package com.tastypizza.menuorders.entities

import javax.persistence.*

@Entity
@Table(name = "restaurant_ingredients")
data class RestaurantIngredients(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,


    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "restaurant", nullable = false)
    var restaurant: Restaurants,

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "ingredient", nullable = false)
    var ingredient: Ingredients,

    var count: Int = 0


)