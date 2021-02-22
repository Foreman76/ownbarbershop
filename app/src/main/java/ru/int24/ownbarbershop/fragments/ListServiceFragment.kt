package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.BarberToolBar
import ru.int24.ownbarbershop.databinding.FragmentListServiceBinding
import ru.int24.ownbarbershop.fragments.adapters.ServiceAdapter
import ru.int24.ownbarbershop.fragments.viewmodels.VMListService


class ListServiceFragment : Fragment() {

    private val vmListService: VMListService by viewModels()
    private var _binding: FragmentListServiceBinding? = null
    private val binding get() = _binding!!
    private val serviceAdapter = ServiceAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentListServiceBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BarberToolBar).setToolBarTitle(getString(R.string.title_listService))
        vmListService.isLoading.observe(viewLifecycleOwner, { HideShowProgress(it)})
        vmListService.getService().observe(viewLifecycleOwner, {serviceAdapter.refreshAdapter(it)})

        showListService()

    }



    fun HideShowProgress(progress: Boolean){
        when(progress){
            true -> binding.idServiceLoader.visibility = View.VISIBLE
            false -> binding.idServiceLoader.visibility = View.GONE
        }
    }

    fun showListService(){
        vmListService.getServiceVM()
        binding.idRcService.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.idRcService.adapter = serviceAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}