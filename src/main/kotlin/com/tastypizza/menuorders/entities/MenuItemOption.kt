package com.tastypizza.menuorders.entities

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Entity
@Getter
@Setter
@Table(name="menuItemOption")
data class MenuItemOption (
    val count: Int?=0,
    val name: String?="",
    val pictureId: String?="",
    val price: Int?=0,
    val weight: Int?=0,
    val nutritional: Int?=0,
    val proteins: Int?=0,
    val fats: Int?=0,
    val carbohydrates: Int?=0,
    val traditionalDough: Boolean?=false,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menuItem_id")
    val menuItem: MenuItem?=null


) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null
}