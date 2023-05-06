package com.tastypizza.menuorders.entities

import com.tastypizza.menuorders.enums.ItemType
import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Entity
@Getter
@Setter
data class MenuItem (
    val type: ItemType?=ItemType.OTHER,
    val newBadge: Boolean?=false,
    val name: String?="",
    val description: String?="",
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val menuItemOptions: MutableList<MenuItemOption> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null
}