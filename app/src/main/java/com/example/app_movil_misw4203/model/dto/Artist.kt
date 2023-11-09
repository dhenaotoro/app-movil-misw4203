package com.example.app_movil_misw4203.model.dto

data class Artist (
  val id: Int,
  val name: String,
  val image: String,
  val description: String,
  val birthDate: String,
  val albums: Set<Album> = emptySet()
)