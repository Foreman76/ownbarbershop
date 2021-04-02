package ru.int24.ownbarbershop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.int24.ownbarbershop.config.DefConfig
import ru.int24.ownbarbershop.databinding.FragmentErrorBinding
import ru.int24.ownbarbershop.routers.CommonRouter

class ErrorFragment : Fragment() {

    private var errorMessage: String? = null
    private var _binding: FragmentErrorBinding? = null
    private val binding get() = _binding!!
    private val router = CommonRouter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            errorMessage = it.getString(DefConfig.key_error)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentErrorBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtError.text = errorMessage
        binding.btnError.setOnClickListener { handlerBtnErrorUpdate() }

    }


    fun handlerBtnErrorUpdate(){
        router.routeErrorToOrderfragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
