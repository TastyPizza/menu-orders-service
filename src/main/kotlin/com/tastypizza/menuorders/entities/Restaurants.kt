package com.tastypizza.menuorders.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import lombok.Getter
import lombok.Setter

@Entity
@Getter
@Setter
@Table(name = "restaurants")
data class Restaurants(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(name = "address", nullable = false, unique = true)
    var address: String,

    @Column(name = "phone", nullable = false, unique = true)
    var phone: String,

    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore
    var restaurantIngredients: Set<RestaurantIngredients> = emptySet()


)