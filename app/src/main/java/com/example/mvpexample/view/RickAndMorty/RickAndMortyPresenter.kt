package com.example.mvpexample.view.RickAndMorty

import com.example.mvpexample.data.common.Resource
import com.example.mvpexample.data.model.Result
import com.example.mvpexample.data.model.RickAndMortyModel
import com.example.mvpexample.domain.interactions.GetCharactersInteraction
import com.example.mvpexample.view.simpson.SimpsonContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RickAndMortyPresenter @Inject constructor(private val getCharactersInteraction: GetCharactersInteraction) :
    RickAndMortyContract.RickAndMortyPresenterContract {

    private var view: RickAndMortyContract.RickAndMortyViewContract? = null

    override fun attachView(viewContract: RickAndMortyContract.RickAndMortyViewContract) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    fun getRickAndMortyCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            getCharactersInteraction.requestRickAndMortyCharacters(object :
                GetCharactersInteraction.GetCharacterListener {
                override fun setRickAndMortyCharacters(rickAndMortyCharacters: List<Result>) {
                    //setRickAndMortyCharacters(rickAndMortyCharacters)
                    view?.setRickAndMortyCharacters(rickAndMortyCharacters)
                }
            })
        }
    }

/*    fun setRickAndMortyCharacters(body: List<Result>) {
        CoroutineScope(Dispatchers.Main).launch {
            view?.setRickAndMortyCharacters(body)
        }
    }*/

}
