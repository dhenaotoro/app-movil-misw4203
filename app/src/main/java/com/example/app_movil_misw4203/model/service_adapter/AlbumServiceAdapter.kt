package com.example.app_movil_misw4203.model.service_adapter

import android.content.Context
import com.example.app_movil_misw4203.model.dto.Album
import com.example.app_movil_misw4203.model.broker.VolleyBroker
import com.example.app_movil_misw4203.model.dto.Performer

import com.google.gson.Gson
import com.example.app_movil_misw4203.model.dto.Track
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

data class AlbumBackendEntity (
  val name: String,
  val cover: String,
  val releaseDate: String,
  val description: String,
  val genre: String,
  val recordLabel: String,
)

data class TrackBackendEntity (
  val name: String,
  val duration: String,
)

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
    VolleyBroker(context)
  }

  suspend fun getAlbums() : List<Album> = suspendCoroutine { cont ->
    broker.instance.add(
      VolleyBroker.getRequest(
        "albums",
        { response ->
          val responseToJSONArray = JSONArray(response)
          val albums = mutableListOf<Album>()
          var album: JSONObject? = null
          for (i in 0 until responseToJSONArray.length()) {
            album = responseToJSONArray.getJSONObject(i)
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
                performers = extractPerformers(album),
                tracks = extractTracks(album)
              )
            )
          }
          cont.resume(albums)
        }
      ) { errorContent ->
        println(errorContent.networkResponse)
        println(errorContent.message)
        cont.resumeWithException(errorContent)
      }
    )
  }

  private fun extractPerformers(album: JSONObject): List<Performer> =
    album.getJSONArray("performers")
      .let {
        val performers = mutableListOf<Performer>()
        var performer: JSONObject? = null
        for (i in 0 until it.length()) {
          performer = it.getJSONObject(i)
          performers.add(
            Performer(
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

  private fun extractTracks(album: JSONObject): List<Track> =
    album.getJSONArray("tracks")
      .let {
        val tracks = mutableListOf<Track>()
        var track: JSONObject? = null
        for (i in 0 until it.length()) {
          track = it.getJSONObject(i)
          tracks.add(
            Track(
              id = track.getInt("id"),
              name = track.getString("name"),
              duration = track.getString("duration")
            )
          )
        }
        tracks
      }

  suspend fun postAlbum(album: Album) : Album = suspendCoroutine {cont ->
    println("Creando album ${Gson().toJson(AlbumBackendEntity(
      name = album.name,
      cover = album.cover,
      releaseDate = album.releaseDate,
      description =  album.description,
      genre = album.genre,
      recordLabel = album.recordLabel,
    ))}")
    broker.instance.add(
      VolleyBroker.postRequest(
        "albums",
        JSONObject(Gson().toJson(AlbumBackendEntity(
          name = album.name,
          cover = album.cover,
          releaseDate = album.releaseDate,
          description =  album.description,
          genre = album.genre,
          recordLabel = album.recordLabel,
        ))),
        { album ->
          cont.resume(Album(
            id = album.getInt("id"),
            name = album.getString("name"),
            cover = album.getString("cover"),
            releaseDate = album.getString("releaseDate"),
            description = album.getString("description"),
            genre = album.getString("genre"),
            recordLabel = album.getString("recordLabel")
          ))
        }
      ) { errorContent ->
        println("Ocurrio la siguiente respuesta de red: ${errorContent.networkResponse}")
        println("Mensaje de error: ${errorContent.message}")
        cont.resumeWithException(errorContent)
      }
    )
  }

  suspend fun getTracksForAlbum(albumId: Int): List<Track> = suspendCoroutine { cont ->
    broker.instance.add(
      VolleyBroker.getRequest(
        "albums/$albumId/tracks",
        { response ->
          val responseToJSONArray = JSONArray(response)
          val tracks = mutableListOf<Track>()
          var track: JSONObject? = null
          for (i in 0 until responseToJSONArray.length()) {
            track = responseToJSONArray.getJSONObject(i)
            tracks.add(
              Track(
                id = track.getInt("id"),
                name = track.getString("name"),
                duration = track.getString("duration")
              )
            )
          }
          cont.resume(tracks)
        }
      ) { errorContent ->
        println(errorContent.networkResponse)
        println(errorContent.message)
      }
    )
  }

  suspend fun postTrack(idTrack: Int, track: Track) = suspendCoroutine { cont ->
    println("Asociando track $idTrack con ${Gson().toJson(TrackBackendEntity(
      name = track.name,
      duration = track.duration,
    ))}")
    broker.instance.add(
      VolleyBroker.postRequest(
        "albums/$idTrack/tracks",
        JSONObject(Gson().toJson(TrackBackendEntity(
          name = track.name,
          duration = track.duration,
        ))),
        { track ->
          cont.resume(Track(
            id = track.getInt("id"),
            name = track.getString("name"),
            duration = track.getString("duration")
          ))
        }
      ) { errorContent ->
        println("Ocurrio la siguiente respuesta de red: ${errorContent.networkResponse}")
        println("Mensaje de error: ${errorContent.message}")
        cont.resumeWithException(errorContent)
      }
    )
  }
}