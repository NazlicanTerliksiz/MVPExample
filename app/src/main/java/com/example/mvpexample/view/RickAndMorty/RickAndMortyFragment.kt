package com.example.mvpexample.view.RickAndMorty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvpexample.R
import com.example.mvpexample.data.model.Result
import com.example.mvpexample.databinding.FragmentRickAndMortyBinding
import com.example.mvpexample.util.gone
import com.example.mvpexample.util.visible
import com.example.mvpexample.view.RickAndMorty.rickAndMortyDetailFragment.RickAndMortyDetailFragment
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

        adapter = RickAndMortyAdapter(::homeToDetail)
        binding.rvCharacters.adapter = adapter

        presenter.attachView(this)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        presenter.getRickAndMortyCharacters()
    }

    override fun setRickAndMortyCharacters(charactersList: MutableList<Result>) {
        adapter.updateList(charactersList.toMutableList())
    }

    private fun homeToDetail(characterImage: String, characterName: String){

        val rickAndMortyDetailFragment = RickAndMortyDetailFragment()

        val bundle = Bundle()
        bundle.putString("characterImage", characterImage)
        bundle.putString("characterName", characterName)
        rickAndMortyDetailFragment.arguments = bundle

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.constraintLayout, rickAndMortyDetailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun showProgressBar() {
        binding.progressBar.visible()
    }

    override fun hideProgressBar() {
        binding.progressBar.gone()
    }
}