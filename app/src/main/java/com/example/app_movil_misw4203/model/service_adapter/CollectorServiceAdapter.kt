package com.example.app_movil_misw4203.model.service_adapter

import android.content.Context
import com.android.volley.Response
import com.android.volley.VolleyError
import com.example.app_movil_misw4203.model.broker.VolleyBroker
import com.example.app_movil_misw4203.model.dto.Artist
import com.example.app_movil_misw4203.model.dto.Collector
import com.example.app_movil_misw4203.model.dto.Performer
import org.json.JSONArray
import org.json.JSONObject

class CollectorServiceAdapter constructor(context: Context) {
    companion object{
        private var instance: CollectorServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CollectorServiceAdapter(context).also {
                    instance = it
                }
            }
    }
    private val broker: VolleyBroker by lazy {
        VolleyBroker(context)
    }

    fun getCollectors(
        onComplete: (collectors: List<Collector>)->Unit,
        onError: (error: VolleyError)->Unit)
    {
        broker.instance.add(
            VolleyBroker.getRequest(
                "collectors",
                readCollectors(onComplete)
            ) { errorContent ->
                onError(errorContent)
            }
        )
    }

    private fun readCollectors(onComplete: (collectors: List<Collector>) -> Unit) = Response.Listener<String> { response ->
        val responseToJSONArray = JSONArray(response)
        val collectors = mutableListOf<Collector>()
        for (i in 0 until responseToJSONArray.length()) {
            val collector = responseToJSONArray.getJSONObject(i)
            collectors.add(
                index = i,
                element = Collector(
                    id = collector.getInt("id"),
                    name = collector.getString("name"),
                    telephone = collector.getString("telephone"),
                    email = collector.getString("email"),
                    favoritePerformers = extractFavoritePerformers(collector)
                )
            )
        }
        onComplete(collectors)
    }

    private fun extractFavoritePerformers(collector: JSONObject): Set<Artist> {
        val favoriteArtistsArray = collector.optJSONArray("favoritePerformers")
        val artists = mutableSetOf<Artist>()

        favoriteArtistsArray?.let {
            for (i in 0 until it.length()) {
                val artist = it.getJSONObject(i)
                artists.add(
                    Artist(
                        id = artist.getInt("id"),
                        name = artist.getString("name"),
                        image = artist.getString("image"),
                        description = artist.getString("description"),
                        birthDate = artist.optString("birthDate")
                    )
                )
            }
        }

        return artists
    }


}