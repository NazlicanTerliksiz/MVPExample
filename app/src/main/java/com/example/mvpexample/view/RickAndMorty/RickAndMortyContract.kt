package com.example.mvpexample.view.RickAndMorty

import com.example.mvpexample.data.model.RickAndMortyModel
import com.example.mvpexample.view.simpson.SimpsonContract

class RickAndMortyContract {

    interface RickAndMortyViewContract {

        fun setRickAndMortyCharacters(charactersList: List<RickAndMortyModel>)
        fun errorShowSnackBar(message: String)
        fun failShowSnackBar(message: String)


    }

    interface RickAndMortyPresenterContract {

        fun attachView(viewContract: RickAndMortyViewContract)

        fun detachView()

        fun setRickAndMortyCharacters(body: List<RickAndMortyModel>)

        fun getErrorMessage(message: String)

        fun getFailMessage(message: String)
    }
}