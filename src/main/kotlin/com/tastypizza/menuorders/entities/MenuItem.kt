package com.tastypizza.menuorders.entities

import com.tastypizza.menuorders.enums.ItemType
import lombok.Getter
import lombok.Setter
import net.minidev.json.annotate.JsonIgnore
import java.io.Serializable
import javax.persistence.*

@Entity
@Getter
@Setter
@Table(name="menuItem")
data class MenuItem (
    val type: ItemType?=ItemType.OTHER,
    val newBadge: Boolean?=false,
    val name: String?="",
    val description: String?="",
    @OneToMany(mappedBy = "menuItem", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @com.fasterxml.jackson.annotation.JsonIgnore
    val menuItemOptions: MutableList<MenuItemOption> = mutableListOf()
): Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null
}