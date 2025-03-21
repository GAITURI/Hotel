package interfaces

import data.Burgers
import retrofit2.Call
import retrofit2.http.GET

interface Api {
@GET(".")
  fun getAllBurgers() : Call<List<Burgers>>




}


