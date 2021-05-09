package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import ru.int24.ownbarbershop.MainActivity
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.BarberToolBar
import ru.int24.ownbarbershop.UiInterface.InterfaceArrowBack
import ru.int24.ownbarbershop.config.DefConfig
import ru.int24.ownbarbershop.databinding.FragmentProfileBinding
import ru.int24.ownbarbershop.di.App
import ru.int24.ownbarbershop.fragments.viewmodels.VMProfileFragment
import ru.int24.ownbarbershop.models.domen.DomSettings
import ru.int24.ownbarbershop.routers.CommonRouter
import ru.int24.ownbarbershop.utilits.handlerBindDomSettingsExt
import ru.int24.ownbarbershop.utilits.initBaseRulesMain
import javax.inject.Inject


class ProfileFragment : Fragment() {

    @Inject lateinit var modelFactory: ViewModelProvider.Factory
    @Inject lateinit var myPicasso:Picasso

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var vmProfileFragment: VMProfileFragment
    private val router: CommonRouter = CommonRouter(this)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        App.appComponent.inject(this@ProfileFragment)
        vmProfileFragment = ViewModelProvider(this@ProfileFragment, modelFactory).get(VMProfileFragment::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as BarberToolBar).setToolBarTitle(getString(R.string.title_profile))
        checkAuth()
        initBaseRulesMain(activity as MainActivity)
        initSetOnClickListener()
        initObserve()
        vmProfileFragment.getSettingsFromDBVM()
    }

    private fun initSetOnClickListener() {
        binding.idProfileExitBtn.setOnClickListener { handlerExitFromProfile() }
        binding.idPersonInfoProfile.setOnClickListener { router.routeThisFragmentToAnyFragment(R.id.action_profileFragment_to_personFragment) }
        binding.idLoyaltyCardsProfile.setOnClickListener { router.routeThisFragmentToAnyFragment(R.id.action_profileFragment_to_cardsLoyaltyFragment) }
        binding.idSubscriptionProfile.setOnClickListener { router.routeThisFragmentToAnyFragment(R.id.action_profileFragment_to_subscriptionFragment) }
        binding.idCertificateProfile.setOnClickListener { router.routeThisFragmentToAnyFragment(R.id.action_profileFragment_to_certificateFragment) }
    }

    private fun handlerExitFromProfile() {
        vmProfileFragment.updateSettingsToDBVM()
    }

    private fun initObserve() {
        vmProfileFragment.isSettings.observe(viewLifecycleOwner, {handlerBindDomSettings(it)})
        vmProfileFragment.isDeleteSettings.observe(viewLifecycleOwner, { handlerUpdateSettings(it)})
    }

    private fun handlerUpdateSettings(isDeleteSettings: Boolean?) {
        when(isDeleteSettings){
            true -> router.routeThisFragmentToAnyFragment(R.id.action_profileFragment_to_authFragment)
        }
    }

    private fun handlerBindDomSettings(domSettings: DomSettings?) {
        domSettings?.let { this.handlerBindDomSettingsExt(it, binding, myPicasso) }
    }

    private fun checkAuth() {
        when(DefConfig.settings.userToken.isEmpty()){
           true ->router.routeThisFragmentToAnyFragment(R.id.action_profileFragment_to_authFragment)
        }
    }

    fun initBaseRules() {
        (activity as InterfaceArrowBack).hideShowArrowBack(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}