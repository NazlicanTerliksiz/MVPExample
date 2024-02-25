package com.example.mvpexample.view.RickAndMorty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvpexample.data.model.Result
import com.example.mvpexample.databinding.FragmentRickAndMortyBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RickAndMortyFragment : Fragment(), RickAndMortyContract.RickAndMortyViewContract {

    @Inject
    lateinit var presenter: RickAndMortyPresenter

    private lateinit var binding: FragmentRickAndMortyBinding
    private lateinit var adapter: RickAndMortyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRickAndMortyBinding.inflate(inflater, container, false)

        adapter = RickAndMortyAdapter()
        binding.rvCharacters.adapter = adapter

        presenter.attachView(this)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        presenter.getRickAndMortyCharacters()
    }

    override fun setRickAndMortyCharacters(charactersList: List<Result>) {
        adapter.updateList(charactersList.toMutableList())
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }
}