package com.example.app_movil_misw4203.model.broker

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class VolleyBroker constructor(context: Context) {
    val instance: RequestQueue = Volley.newRequestQueue(context.applicationContext)

    companion object{
        private const val BASE_URL= "https://web-ivandsanchezc.cloud.okteto.net/"
        fun getRequest(
            path:String,
            responseListener: Response.Listener<String>,
            errorListener: Response.ErrorListener): StringRequest =
            StringRequest(Request.Method.GET, "${BASE_URL}${path}", responseListener, errorListener)

        fun postRequest(
            path: String,
            body: JSONObject,
            responseListener: Response.Listener<JSONObject>,
            errorListener: Response.ErrorListener ): JsonObjectRequest =
            JsonObjectRequest(Request.Method.POST, "${BASE_URL}${path}", body, responseListener, errorListener)

    }

}