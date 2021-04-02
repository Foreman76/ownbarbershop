package ru.int24.ownbarbershop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.ArrowBack
import ru.int24.ownbarbershop.UiInterface.BarberToolBar
import ru.int24.ownbarbershop.databinding.FragmentDetailStaffBinding
import ru.int24.ownbarbershop.di.App
import ru.int24.ownbarbershop.fragments.viewmodels.VMDetailStaffFragment
import ru.int24.ownbarbershop.models.domen.DomStaff
import ru.int24.ownbarbershop.routers.CommonRouter
import javax.inject.Inject


class DetailStaffFragment : Fragment() {


    @Inject lateinit var modelFactory: ViewModelProvider.Factory
    @Inject lateinit var myPicasso: Picasso
    private lateinit var vmDetailStaffFragment: VMDetailStaffFragment
    private var _binding: FragmentDetailStaffBinding? = null
    private val binding get() = _binding!!
    private val router: CommonRouter = CommonRouter(this)
    private lateinit var staff:DomStaff

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments()
            .get("staff")?.let { staff = it as DomStaff }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       _binding = FragmentDetailStaffBinding.inflate(inflater, container , false)
      App.appComponent.inject(this@DetailStaffFragment)
      vmDetailStaffFragment = ViewModelProvider(this , modelFactory).get(VMDetailStaffFragment::class.java)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BarberToolBar).setToolBarTitle(getString(R.string.text_detail_Staff_fragment))
         binding.idBarberName.text = staff.name
         binding.idBarberDescrption.text = getString(R.string.lorem_test)
         myPicasso.load(staff.avatar).placeholder(R.drawable.placeholder_avatar).into(binding.idImageAvatar)
        (activity as ArrowBack).hideShowArrowBack(false)
        (activity as ArrowBack).handlerOnClick(){router.routeDetailStaffFragmentToListStaffFragment()}



    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}