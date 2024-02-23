package com.example.mvpexample.domain.interactions

import com.example.mvpexample.data.common.Resource
import com.example.mvpexample.data.model.RickAndMortyModel
import com.example.mvpexample.data.model.SimpsonModel
import com.example.mvpexample.data.services.ApiServices
import com.example.mvpexample.infrastructure.MainApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GetCharactersInteraction @Inject constructor(private val apiServices: ApiServices) {

    interface GetCharacterListener {
        fun setRickAndMortyCharacters(rickAndMortyCharacters: MutableList<RickAndMortyModel>)
    }

    //main threadte verileri çağırma
//    private val executor = Executors.newSingleThreadExecutor()
//
//    suspend fun requestSimpsonCharacters(){
//        executor.execute {
//            simpsonServices.getSimpsonCharacters()
//        }
//    }

    suspend fun requestSimpsonCharacters(): Resource<Response<MutableList<SimpsonModel>>> =
        withContext(Dispatchers.IO) {
            try {
                val response = apiServices.getSimpsonCharacters()
                if (response.isSuccessful) {
                    Resource.Success(response)
                } else {
                    Resource.Fail("An error occurred")
                }
            } catch (e: Exception) {
                Resource.Error(e.message.toString())
            }
        }


//    suspend fun requestSimpsonCharacters(): Response<MutableList<SimpsonModel>> {
//        return simpsonServices.getSimpsonCharacters()
//    }

//listener kullanarak
//    suspend fun getSimpsonCharacterInteraction(listener: GetCharacterListener) {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val response = simpsonServices.getSimpsonCharacters()
//                if (response.isSuccessful) {
//                    response.body()!!
//                    //listener.setSimpsonsCharacters(response.body()?: mutableListOf())
//                    //response.body()
//                }
//            } catch (e: Exception) {
//                Log.d("nazlican", e.message.toString())
//            }
//        }
//    }

    fun requestRickAndMortyCharacters() {
         apiServices.getRickAndMortyCharacters().enqueue(object : Callback<List<RickAndMortyModel>> {
             override fun onResponse(
                 call: Call<List<RickAndMortyModel>>,
                 response: Response<List<RickAndMortyModel>>
             ) {
                response.body().orEmpty()
             }
             override fun onFailure(call: Call<List<RickAndMortyModel>>, t: Throwable) {
                Resource.Error(t.message.toString())
             }
         })
     }
}