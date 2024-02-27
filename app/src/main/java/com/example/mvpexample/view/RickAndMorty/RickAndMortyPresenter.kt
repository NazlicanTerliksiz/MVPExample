package com.example.mvpexample.view.RickAndMorty

import android.os.Handler
import android.os.Looper
import com.example.mvpexample.data.model.Result
import com.example.mvpexample.domain.interactions.GetCharactersInteraction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RickAndMortyPresenter @Inject constructor(private val getCharactersInteraction: GetCharactersInteraction) :
    RickAndMortyContract.RickAndMortyPresenterContract {

    private var view: RickAndMortyContract.RickAndMortyViewContract? = null
    private val handler = Handler(Looper.getMainLooper())

    override fun attachView(view: RickAndMortyContract.RickAndMortyViewContract) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    fun getRickAndMortyCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
           handler.post { view?.showProgressBar() }
            getCharactersInteraction.requestRickAndMortyCharacters(object :
                GetCharactersInteraction.GetCharacterListener {
                override fun setRickAndMortyCharacters(rickAndMortyCharacters: MutableList<Result>) {
                    view?.setRickAndMortyCharacters(rickAndMortyCharacters)
                    handler.post { view?.hideProgressBar() }
                }
            })
        }
    }
}