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
    val name: String="",
    val pictureId: String="",
    val price: Int=0,
    val weight: Int=0,
    val nutritional: Int=0,
    val proteins: Int=0,
    val fats: Int=0,
    val carbohydrates: Int=0,
    val traditionalDough: Boolean=false,

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