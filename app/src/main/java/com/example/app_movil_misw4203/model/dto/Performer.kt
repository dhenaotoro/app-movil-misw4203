package com.example.app_movil_misw4203.model.dto

data class Performer(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val creationDate: String? = "",
    val birthDate: String? = ""
)