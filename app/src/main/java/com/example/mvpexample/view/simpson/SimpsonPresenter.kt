package com.example.mvpexample.view.simpson

import android.os.Handler
import android.os.Looper
import com.example.mvpexample.data.common.Resource
import com.example.mvpexample.data.model.SimpsonModel
import com.example.mvpexample.domain.interactions.GetCharactersInteraction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SimpsonPresenter @Inject constructor(private val getCharactersInteraction: GetCharactersInteraction) :
    SimpsonContract.SimpsonPresenterContract {

    private var view: SimpsonContract.SimpsonViewContract? = null
    private val handler = Handler(Looper.getMainLooper())

    override fun attachView(view: SimpsonContract.SimpsonViewContract) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

//    override fun getSimpsonCharacters() {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val response = getCharactersInteraction.requestSimpsonCharacters()
//                if (response.isSuccessful) {
//                    setSimspsonCharacters(response.body()!!)
//                    //listener.setSimpsonsCharacters(response.body()?: mutableListOf())
//                    //response.body()
//                }
//            } catch (e: Exception) {
//                Log.d("nazlican", e.message.toString())
//            }
//        }

    override fun getSimpsonCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            when (val result = getCharactersInteraction.requestSimpsonCharacters()) {
                is Resource.Success -> setSimspsonCharacters(result.data.body()!!)
                is Resource.Fail -> getFailMessage(result.failMessage)
                is Resource.Error -> getErrorMessage(result.errorMessage)
            }
        }


//        getCharactersInteraction.getSimpsonCharacterInteraction(object : GetCharactersInteraction.GetCharacterListener{
//            override fun setSimpsonsCharacters(simpsonsCharacters: MutableList<SimpsonModel>) {
//                view?.showRecyclerView(simpsonsCharacters)
//            }
//        })
    }

    private fun getFailMessage(message: String) {
        CoroutineScope(Dispatchers.Main).launch {
            view?.failShowSnackBar(message)
        }
    }

    private fun getErrorMessage(message: String) {
        CoroutineScope(Dispatchers.Main).launch {
            view?.errorShowSnackBar(message)
        }
    }

    private fun setSimspsonCharacters(body: MutableList<SimpsonModel>) {
        CoroutineScope(Dispatchers.Main).launch {
            view?.setSimpsonData(body)
        }
//        handler.post{view?.showRecyclerView(body)}
    }
}