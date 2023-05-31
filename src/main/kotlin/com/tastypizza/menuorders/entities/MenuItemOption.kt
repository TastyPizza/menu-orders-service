package com.tastypizza.menuorders.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Entity
@Getter
@Setter
@Table(name="menuItemOption")
data class MenuItemOption (
    var count: Long=0,
    var name: String="",
    var pictureId: String="",
    var price: Int=0,
    var weight: Int=0,
    var nutritional: Int=0,
    var proteins: Int=0,
    var fats: Int=0,
    var carbohydrates: Int=0,
    var traditionalDough: Boolean=false,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menuItem_id")
    @JsonIgnore
    val menuItem: MenuItem?=null,



    @JsonIgnore
    @OneToMany(mappedBy = "menuItemOption")
    val orderItems: Set<OrderItem> = emptySet(),

    @JsonIgnore
    @OneToMany(mappedBy = "menuItemOption")
    var ingredientsMenuItemOptions: Set<IngredientsMenuItemOptions> = emptySet()


) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null
}