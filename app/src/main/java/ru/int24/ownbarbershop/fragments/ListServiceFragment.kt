package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.int24.ownbarbershop.MainActivity
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.*
import ru.int24.ownbarbershop.databinding.FragmentListServiceBinding
import ru.int24.ownbarbershop.di.App
import ru.int24.ownbarbershop.fragments.adapters.ServiceAdapter
import ru.int24.ownbarbershop.fragments.adapters.ServiceChoseAdapter
import ru.int24.ownbarbershop.fragments.viewmodels.VMListService
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.routers.CommonRouter
import ru.int24.ownbarbershop.utilits.ProgressIndicator
import ru.int24.ownbarbershop.utilits.initBaseRules
import javax.inject.Inject


class ListServiceFragment : Fragment() {

    @Inject lateinit var modelFactory: ViewModelProvider.Factory
    @Inject lateinit var serviceAdapter: ServiceAdapter
    @Inject lateinit var serviceChoseAdapter: ServiceChoseAdapter

    private lateinit var vmListService: VMListService
    private var _binding: FragmentListServiceBinding? = null
    private val binding get() = _binding!!

    private val router = CommonRouter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentListServiceBinding.inflate(inflater, container, false)
        App.appComponent.inject(this@ListServiceFragment)
        val view = binding.root
        vmListService = ViewModelProvider(this, modelFactory).get(VMListService::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BarberToolBar).setToolBarTitle(getString(R.string.title_listService))
        initObservers()

        serviceChoseAdapter.clearAdapter()
        binding.idBtnServiceContinue.setOnClickListener{ router.routeFragmentUp()}

        initBaseRules(router, activity as MainActivity)

        showListService()
        attachListener()
        attachChoseListener()

    }


    private fun initObservers() {
        vmListService.isLoading.observe(viewLifecycleOwner, { ProgressIndicator.showHideProgress(it, binding.idServiceLoader)})
        vmListService.getService().observe(viewLifecycleOwner, {serviceAdapter.refreshAdapter(it)})
        vmListService.isErrorMessage.observe(viewLifecycleOwner, {router.routeThisFragmentToErrorScreen(it,
                R.id.action_listServiceFragment_to_errorFragment)})
        vmListService.isShowChose.observe(viewLifecycleOwner, {refreshChose(it)})
    }


    fun refreshChose(flag:Boolean){
        when(flag){
            false -> binding.idRcChoseService.visibility = View.GONE
            true -> {
                if (binding.idRcChoseService.visibility != View.VISIBLE){
                binding.idRcChoseService.visibility = View.VISIBLE
                showChoseListService()}
            }
        }
    }


    fun showChoseListService(){
        binding.idRcChoseService.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.idRcChoseService.adapter = serviceChoseAdapter
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

    fun attachListener(){
        serviceAdapter.attachDelegate(object : InterfaceServiceAdapter{
            override fun handlerClickItemService(item: DomServices) {
                val lService = mutableListOf<DomServices>()
                lService.add(item)
                serviceAdapter.updateRemoveAdapter(lService)
                serviceChoseAdapter.updateAddAdapter(lService)
                vmListService.changeShowChose(true)
                vmListService.addService(item)
                setChosePrice()
            }
        })
    }

    fun attachChoseListener(){
        serviceChoseAdapter.attachDelegate(object : InterfaceChoseServiceAdapter{
            override fun hadlerClickChoseItemService(item: DomServices) {
                val lService = mutableListOf<DomServices>()
                lService.add(item)
                serviceAdapter.updateAddAdapter(lService)
                serviceChoseAdapter.updateRemoveAdapter(lService)
                if(serviceChoseAdapter.getCountData() == 0){vmListService.changeShowChose(false)}
                vmListService.deleteServiceVM(item)
                setChosePrice()
            }
        })
    }

    fun setChosePrice(){
        val myMap: MutableMap<String, String> = serviceChoseAdapter.countPrice()
        val chose: String = "Выбрано(${myMap["TotalCount"]})"
        binding.idChooseService.text = chose
        binding.idPriceService.text = myMap["Price"]

    }
}