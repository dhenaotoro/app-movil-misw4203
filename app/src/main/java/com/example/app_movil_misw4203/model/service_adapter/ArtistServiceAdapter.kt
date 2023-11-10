package com.example.app_movil_misw4203.model.service_adapter

import android.content.Context
import com.android.volley.Response
import com.android.volley.VolleyError
import com.example.app_movil_misw4203.model.dto.Artist
import com.example.app_movil_misw4203.model.broker.VolleyBroker
import com.example.app_movil_misw4203.model.dto.Album
import com.example.app_movil_misw4203.model.dto.Performer
import org.json.JSONArray
import org.json.JSONObject

class ArtistServiceAdapter constructor(context: Context) {
  companion object{
    private var instance: ArtistServiceAdapter? = null
    fun getInstance(context: Context) =
      instance ?: synchronized(this) {
        instance ?: ArtistServiceAdapter(context).also {
          instance = it
        }
      }
  }
  private val broker: VolleyBroker by lazy {
    VolleyBroker(context)
  }

  fun getArtists(
    onComplete: (artists: List<Artist>)->Unit,
    onError: (error: VolleyError)->Unit)
  {
    broker.instance.add(
      VolleyBroker.getRequest(
        "musicians",
        readArtists(onComplete)
      ) { errorContent ->
        onError(errorContent)
      }
    )
  }

  private fun readArtists(onComplete: (artists: List<Artist>) -> Unit) = Response.Listener<String> { response ->
    val responseToJSONArray = JSONArray(response)
    val artists = mutableListOf<Artist>()
    for (i in 0 until responseToJSONArray.length()) {
      val artist = responseToJSONArray.getJSONObject(i)
      println(artist.toString())
      artists.add(
        index = i,
        element = Artist(
          id = artist.getInt("id"),
          name = artist.getString("name"),
          image = artist.getString("image"),
          description = artist.getString("description"),
          birthDate = artist.getString("birthDate"),
          /*albums = extractAlbum(artist),*/
        )
      )
    }
    onComplete(artists)
  }

  /*private fun extractPerformers(artist: JSONObject): Set<Performer> =
    artist.getJSONArray("performers")
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
      }*/

  /*private fun extractAlbum(artist: JSONObject): Set<Album> =
    artist.getJSONArray("albums")
      .let {
        val albums = mutableSetOf<Album>()
        for (i in 0 until it.length()) {
          val album = it.getJSONObject(i)
          albums.add(
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
        albums
      }*/
}