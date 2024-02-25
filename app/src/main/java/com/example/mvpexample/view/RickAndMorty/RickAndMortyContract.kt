package com.example.mvpexample.view.RickAndMorty

import com.example.mvpexample.data.model.Result

class RickAndMortyContract {

    interface RickAndMortyViewContract {

        fun setRickAndMortyCharacters(charactersList: List<Result>)

        fun onDestroyView()

    }

    interface RickAndMortyPresenterContract {

        fun attachView(viewContract: RickAndMortyViewContract)

        fun detachView()
    }
}