package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.BarberToolBar
import ru.int24.ownbarbershop.databinding.FragmentAuthBinding
import ru.int24.ownbarbershop.di.App
import ru.int24.ownbarbershop.fragments.viewmodels.VMAuthFragment
import ru.int24.ownbarbershop.routers.CommonRouter
import javax.inject.Inject


class AuthFragment : Fragment() {


    @Inject lateinit var modelFactory: ViewModelProvider.Factory

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!
    private lateinit var vmAuthFragment: VMAuthFragment
    private val router = CommonRouter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        App.appComponent.inject(this@AuthFragment)
        vmAuthFragment = ViewModelProvider(this@AuthFragment, modelFactory).get(VMAuthFragment::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BarberToolBar).setToolBarTitle(getString(R.string.title_auth))
        initObserve()
        binding.authGetSmsCode.setOnClickListener{ handlerGetSMSCode() }
        binding.authUpdateSmsCode.setOnClickListener { handlerUpdateSMSCode() }
        binding.authConfirmPhoneNumber.setOnClickListener { handlerConfirmPhoneNumber() }
        vmAuthFragment.getSettingsFromDB()
    }

    private fun handlerConfirmPhoneNumber() {
        vmAuthFragment.getAuthUserFromNet(binding.textInputEditTextPhone.text.toString(), binding.textInputEditTextSmsCode.text.toString())
    }

    private fun handlerUpdateSMSCode() {
        binding.enterSmsCode.visibility = View.GONE
        binding.authGetSmsCode.visibility = View.VISIBLE
        binding.textInputLayoutPhone.visibility = View.VISIBLE
        binding.textInputLayoutUserName.visibility = View.VISIBLE
    }

    private fun handlerGetSMSCode() {
        val phone = binding.textInputEditTextPhone.text.toString()
        val userName = binding.textInputEditTextUserName.text.toString()
        if (phone.isNotEmpty() && userName.isNotEmpty()) {
            vmAuthFragment.getSMSCodeFromNet(phone = phone, name = userName)
            vmAuthFragment.getTimerValue()
        }else{
            showToast(getString(R.string.error_requared_field))
        }
    }


    fun initObserve(){
        vmAuthFragment.isErrorMessage.observe(viewLifecycleOwner, {router.routeThisFragmentToErrorScreen(it,
                R.id.action_authFragment_to_errorFragment)})
        vmAuthFragment.isPhone.observe(viewLifecycleOwner, {setPhone(it)})
        vmAuthFragment.isName.observe(viewLifecycleOwner, {setName(it)})
        vmAuthFragment.isGetSMSCode.observe(viewLifecycleOwner, {showViewEnterSMSCode(it)})
        vmAuthFragment.isTimerValue.observe(viewLifecycleOwner, {showTimerValue(it)})
        vmAuthFragment.isUserToken.observe(viewLifecycleOwner,{routeThisFragmentToParentFragment(it)})

    }

    private fun routeThisFragmentToParentFragment(userToken:Boolean){
        when(userToken){
            true -> {
                router.routeFragmentUp()
            }
        }
    }

    private fun showTimerValue(timerValue: String?) {
        timerValue?.let {
            binding.authTimerSmsCode.text = it
            when(it){
                "0" -> handlerUpdateSMSCode()
            }
        }
    }

    private fun showViewEnterSMSCode(show: Boolean?) {
        show?.let {
            binding.enterSmsCode.visibility = View.VISIBLE
            binding.textPhoneNumber.text = binding.textInputEditTextPhone.text
            binding.authGetSmsCode.visibility = View.GONE
            binding.textInputLayoutPhone.visibility = View.GONE
            binding.textInputLayoutUserName.visibility = View.GONE
            binding.textInputEditTextSmsCode.setText("")
        }
    }

    private fun setName(username: String?) {
        username?.let {binding.textInputEditTextUserName.setText(it) }
    }

    private fun setPhone(userphone: String?) {
        userphone?.let { binding.textInputEditTextPhone.setText(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showToast(message:String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}