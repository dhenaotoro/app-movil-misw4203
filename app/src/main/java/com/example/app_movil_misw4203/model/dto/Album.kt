package com.example.app_movil_misw4203.model.dto

data class Album (
  val id: Int,
  val name: String,
  val cover: String,
  val releaseDate: String,
  val description: String,
  val genre:String,
  val recordLabel:String,
  val tracks: Set<String> = emptySet(),
  val performers: Set<Performer> = emptySet(),
  val comments: List<String> = emptyList()
)