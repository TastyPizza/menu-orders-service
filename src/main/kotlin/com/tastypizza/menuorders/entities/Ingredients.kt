package com.tastypizza.menuorders.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*
import lombok.Getter
import lombok.Setter

@Entity
@Getter
@Setter
@Table(name = "ingredients")
data class Ingredients(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "description", nullable = false)
    var description: String,

    var price: Double,

)