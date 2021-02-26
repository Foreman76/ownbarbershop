package ru.int24.ownbarbershop.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.InterfaceChoseServiceAdapter
import ru.int24.ownbarbershop.databinding.ItemShoseServiceBinding
import ru.int24.ownbarbershop.models.domen.DomServices

class ServiceChoseAdapter: RecyclerView.Adapter<ServiceChoseAdapter.ChoseServiceViewHolder>() {

    private var choseService: MutableList<DomServices> = mutableListOf<DomServices>()
    private var delegate: InterfaceChoseServiceAdapter? = null

    fun attachDelegate(click: InterfaceChoseServiceAdapter){
        delegate = click
    }

    class ChoseServiceViewHolder(itemview: View, val myClick: InterfaceChoseServiceAdapter?): RecyclerView.ViewHolder(itemview){
        val binding = ItemShoseServiceBinding.bind(itemView)
        fun bind(item:DomServices){
            binding.idShoseService.text = item.title
            binding.idServiceIconDelete.setOnClickListener { myClick?.hadlerClickChoseItemService(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceChoseAdapter.ChoseServiceViewHolder {
       return ChoseServiceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_shose_service, parent, false), delegate)
    }

    override fun onBindViewHolder(holder: ServiceChoseAdapter.ChoseServiceViewHolder, position: Int) {
        val item = choseService[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int {
        return choseService.size
    }

    fun getCountData():Int = choseService.size

    fun refreshAdapter(lService: MutableList<DomServices>?){
        when(lService){
            is MutableList<DomServices> -> {
                this.choseService.clear()
                this.choseService.addAll(lService)
                notifyDataSetChanged()
            }
        }

    }

    fun addItemService(item: DomServices){
        choseService.add(0,item)
        notifyItemInserted(0)
    }

    fun deleteService(position: Int){
        choseService.removeAt(position)
        notifyItemRemoved(position)
    }

    fun updateAddAdapter(lService: MutableList<DomServices>){
        lService.forEach{ v->
            addItemService(v)
        }
    }

    fun updateRemoveAdapter(lChoseService: MutableList<DomServices>){
        lChoseService.forEach{ v ->

            val l = choseService.find { it.id == v.id }
            if (l !=null){ deleteService(choseService.indexOf(l)) }
        }

    }

    fun countPrice():MutableMap<String, String>{
        val myMap = mutableMapOf<String, String>()
        val price_min_total = choseService.sumOf { it.price_min } ?: 0
        val price_max_total = choseService.sumOf { it.price_max } ?: 0
        myMap["Price"] = price_min_total.toString() +" - "+ price_max_total.toString()
        myMap["TotalCount"] = choseService.size.toString() ?: "0"
        return myMap
    }
}