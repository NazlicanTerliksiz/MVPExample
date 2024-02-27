package com.example.mvpexample.view.simpson

import com.example.mvpexample.data.model.SimpsonModel

class SimpsonContract {

    interface SimpsonViewContract {

        fun setSimpsonData(characterList: List<SimpsonModel>)

        fun onDestroyView()

        fun homeToDetail(simpsonCharacterName: String, simpsonCharacterImage: String)

        fun errorShowSnackBar(message: String)

        fun failShowSnackBar(message: String)

        fun showProgressBar() {

        }
        fun hideProgressBar()

    }

    interface SimpsonPresenterContract {

        fun attachView(viewContract: SimpsonViewContract)

        fun detachView()

        fun getSimpsonCharacters()
    }
}