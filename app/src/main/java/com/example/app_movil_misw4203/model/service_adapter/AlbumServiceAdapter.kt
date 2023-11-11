package com.example.app_movil_misw4203.model.service_adapter

import android.content.Context
import com.android.volley.Response
import com.android.volley.VolleyError
import com.example.app_movil_misw4203.model.dto.Album
import com.example.app_movil_misw4203.model.broker.VolleyBroker
import com.example.app_movil_misw4203.model.dto.Performer
import org.json.JSONArray
import org.json.JSONObject

class AlbumServiceAdapter constructor(context: Context) {
  companion object{
    private var instance: AlbumServiceAdapter? = null
    fun getInstance(context: Context) =
      instance ?: synchronized(this) {
        instance ?: AlbumServiceAdapter(context).also {
          instance = it
        }
      }
  }
  private val broker: VolleyBroker by lazy {
    // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
    VolleyBroker(context)
  }

  fun getAlbums(
    onComplete: (albums: List<Album>)->Unit,
    onError: (error: VolleyError)->Unit)
  {
    broker.instance.add(
      VolleyBroker.getRequest(
        "albums",
        readAlbums(onComplete)
      ) { errorContent ->
        println(errorContent.networkResponse)
        println(errorContent.message)
        onError(errorContent)
      }
    )
  }

  private fun readAlbums(onComplete: (albums: List<Album>) -> Unit) = Response.Listener<String> { response ->
    val responseToJSONArray = JSONArray(response)
    val albums = mutableListOf<Album>()
    for (i in 0 until responseToJSONArray.length()) {
      val album = responseToJSONArray.getJSONObject(i)
      println(album.toString())
      albums.add(
        index = i,
        element = Album(
          id = album.getInt("id"),
          name = album.getString("name"),
          cover = album.getString("cover"),
          releaseDate = album.getString("releaseDate"),
          description = album.getString("description"),
          genre = album.getString("genre"),
          recordLabel = album.getString("recordLabel"),
          performers = extractPerformers(album)
        )
      )
    }
    onComplete(albums)
  }

  private fun extractPerformers(album: JSONObject): Set<Performer> =
    album.getJSONArray("performers")
      .let {
        val performers = mutableSetOf<Performer>()
        for (i in 0 until it.length()) {
          val performer = it.getJSONObject(i)
          performers.add(
            element = Performer(
              id = performer.getInt("id"),
              name = performer.getString("name"),
              image = performer.getString("image"),
              description = performer.getString("description"),
              creationDate = performer.optString("creationDate"),
              birthDate = performer.optString("birthDate")
            )
          )
        }
        performers
      }
}