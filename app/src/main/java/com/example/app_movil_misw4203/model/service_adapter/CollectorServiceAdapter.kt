package com.example.app_movil_misw4203.model.service_adapter

import android.content.Context
import com.android.volley.Response
import com.android.volley.VolleyError
import com.example.app_movil_misw4203.model.broker.VolleyBroker
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
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
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
            println(collector.toString())
            collectors.add(
                index = i,
                element = Collector(
                    id = collector.getInt("id"),
                    name = collector.getString("name"),
                    telephone = collector.getString("telephone"),
                    email = collector.getString("email")
                )
            )
        }
        onComplete(collectors)
    }

}