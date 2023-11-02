package com.example.app_movil_misw4203.data

import android.app.Application
import com.android.volley.VolleyError
import com.example.app_movil_misw4203.network.NetworkServiceAdapter

class AlbumRepository (val application: Application){
  fun refreshData(callback: (List<CollectorDTO>)->Unit, onError: (VolleyError)->Unit) {
    //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
    NetworkServiceAdapter.getInstance(application).getCollectors({
      //Guardar los albumes de la variable it en un almacén de datos local para uso futuro
      callback(it)
    },
      onError
    )
  }
}