package com.example.mvpexample.domain.interactions

import android.util.Log
import com.example.mvpexample.data.common.Resource
import com.example.mvpexample.data.model.Result
import com.example.mvpexample.data.model.RickAndMortyModel
import com.example.mvpexample.data.model.SimpsonModel
import com.example.mvpexample.data.services.RickAndMortyApiService
import com.example.mvpexample.data.services.SimpsonApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GetCharactersInteraction @Inject constructor(private val simpsonApiService: SimpsonApiService, private val rickAndMortyApiService: RickAndMortyApiService) {

    interface GetCharacterListener {
        fun setRickAndMortyCharacters(rickAndMortyCharacters: MutableList<Result>)
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
                val response = simpsonApiService.getSimpsonCharacters()
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

    fun requestRickAndMortyCharacters(listener : GetCharacterListener) {
         rickAndMortyApiService.getRickAndMortyCharacters().enqueue(object : Callback<RickAndMortyModel> {
             override fun onResponse(
                 call: Call<RickAndMortyModel>,
                 response: Response<RickAndMortyModel>
             ) {
                 if (response.isSuccessful) {
                     listener.setRickAndMortyCharacters((response.body()?.results.orEmpty()).toMutableList())
                 }else{
                     Log.d("failmessage", response.toString())
                 }
             }

             override fun onFailure(call: Call<RickAndMortyModel>, t: Throwable) {
                 Log.d("errormessage", t.message.toString())
             }

         })
     }
}