package com.example.mvpexample.view.RickAndMorty

import com.example.mvpexample.data.model.Result

class RickAndMortyContract {

    interface RickAndMortyViewContract {

        fun setRickAndMortyCharacters(charactersList: MutableList<Result>)

        fun onDestroyView()

        fun showProgressBar()

        fun hideProgressBar()

    }

    interface RickAndMortyPresenterContract {

        fun attachView(viewContract: RickAndMortyViewContract)

        fun detachView()
    }
}