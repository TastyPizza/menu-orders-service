package com.tastypizza.menuorders.entity

import lombok.Data
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name="menuItemOption")
@Data
data class MenuItemOption(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @NotNull
    val count: Int? = null,

    @NotNull
    val pictureId: String? = null,

    @NotNull
    val price: Int? = null
)
