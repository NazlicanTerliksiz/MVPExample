package com.example.mvpexample.view.simpson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvpexample.R
import com.example.mvpexample.data.model.SimpsonModel
import com.example.mvpexample.databinding.FragmentSimpsonBinding
import com.example.mvpexample.view.simpsonDetail.SimpsonDetailFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SimpsonFragment : Fragment(), SimpsonContract.SimpsonViewContract {

    @Inject
    lateinit var simpsonPresenter: SimpsonPresenter

    private lateinit var simpsonAdapter: SimpsonAdapter
    private lateinit var binding: FragmentSimpsonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSimpsonBinding.inflate(inflater, container, false)

        simpsonAdapter = SimpsonAdapter(::homeToDetail)
        binding.rvCharacters.adapter = simpsonAdapter

        simpsonPresenter.attachView(this)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        simpsonPresenter.getSimpsonCharacters()
    }

    override fun setSimpsonData(characterList: List<SimpsonModel>) {
        //requireActivity().runOnUiThread(Runnable { simpsonAdapter.setData(characterList.toMutableList()) })
        simpsonAdapter.setData(characterList.toMutableList())
    }

    override fun onDestroyView() {
        simpsonPresenter.detachView()
        super.onDestroyView()
    }

    override fun homeToDetail(simpsonCharacterName: String, simpsonCharacterImage: String) {

        val detailFragment = SimpsonDetailFragment.newInstance(simpsonCharacterName, simpsonCharacterImage)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.constraintLayout, detailFragment)
        transaction.addToBackStack(null)
        transaction.commit()

//        val action = HomeFragmentDirections.actionHomeFragmentToSimpsonDetailFragment(
//            simpsonCharacterName,
//            simpsonCharacterImage
//        )
//        findNavController().navigate(action)
    }

    override fun errorShowSnackBar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
    }

    override fun failShowSnackBar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
    }

}