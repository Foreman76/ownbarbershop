package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import ru.int24.ownbarbershop.MainActivity
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.BarberToolBar
import ru.int24.ownbarbershop.databinding.FragmentPersonBinding
import ru.int24.ownbarbershop.di.App
import ru.int24.ownbarbershop.fragments.viewmodels.VMPersonFragment
import ru.int24.ownbarbershop.models.domen.DomSettings
import ru.int24.ownbarbershop.routers.CommonRouter
import ru.int24.ownbarbershop.utilits.initBaseRules
import javax.inject.Inject


class PersonFragment : Fragment() {

    @Inject lateinit var modelFactory: ViewModelProvider.Factory
    @Inject lateinit var picasso: Picasso
    private var _binding: FragmentPersonBinding? = null
    private val binding get() = _binding!!
    private lateinit var vmPersonFragment: VMPersonFragment
    private val router = CommonRouter(this)
    private var domSettings:DomSettings? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        App.appComponent.inject(this@PersonFragment)
        _binding = FragmentPersonBinding.inflate(layoutInflater, container, false)
        vmPersonFragment = ViewModelProvider(this@PersonFragment, modelFactory).get(VMPersonFragment::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BarberToolBar).setToolBarTitle(getString(R.string.title_personInfo))
        initBaseRules(router, activity as MainActivity)
        initObservers()
        initClickListener()
        vmPersonFragment.getSettingsFromDB()

    }

    private fun initClickListener() {
        binding.idPersonSaveBtn.setOnClickListener { handlerSaveSettings() }
    }

    private fun handlerSaveSettings() {
        domSettings?.userName = binding.textInputEditTextEditPersonName.text.toString()
        domSettings?.userEmail = binding.textInputEditTextEditPersonEmail.text.toString()
        vmPersonFragment.saveSettingsToDB(domSettings!!)
    }

    private fun initObservers() {
        vmPersonFragment.isSaveTobase.observe(viewLifecycleOwner, { showMessage(it)})
        vmPersonFragment.getSettingsVM().observe(viewLifecycleOwner, {handlerGetSettings(it)})
    }

    private fun showMessage(isSaveToBase:Boolean) {
        when(isSaveToBase){
            true -> Toast.makeText(context, "Изменения успешно записаны", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handlerGetSettings(dSettings: DomSettings?) {
        dSettings?.let { domSettings = it
        bindingFragment()
        }
    }

    private fun bindingFragment() {
        binding.textInputEditTextEditPersonPhone.setText(domSettings?.phone)
        binding.textInputEditTextEditPersonName.setText(domSettings?.userName)
        binding.textInputEditTextEditPersonEmail.setText(domSettings?.userEmail)
        picasso.load(domSettings?.avatar).placeholder(R.drawable.placeholder_avatar).into(binding.idLogoAvatarPerson)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}