package ru.int24.ownbarbershop.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.databinding.GroupitemlistserviceBinding
import ru.int24.ownbarbershop.databinding.ImageitemserviceBinding
import ru.int24.ownbarbershop.databinding.ItemserviceBinding
import ru.int24.ownbarbershop.models.domen.DomServices
import ru.int24.ownbarbershop.models.domen.TypeCardService

class ServiceAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataService: MutableList<DomServices> = mutableListOf<DomServices>()

    class ItemViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
            val binding = ItemserviceBinding.bind(itemView)

        fun bind(item: DomServices){
            val price_min: String = item.price_min.toString()
            val price_max: String = item.price_max.toString()
            val price: String = "$price_min - $price_max"
            binding.idServiceTitle.text = item.title
            binding.idServicePrice.text = price

        }
    }

    class GroupViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        val binding = GroupitemlistserviceBinding.bind((itemview))
        fun bind(item:DomServices){
            binding.titleServiceGroup.text = item.title
        }
    }

    class ImageViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        val binding = ImageitemserviceBinding.bind(itemview)
        fun bind(item:DomServices){
            val price_min: String = item.price_min.toString()
            val price_max: String = item.price_max.toString()
            val price: String = "$price_min - $price_max"
            binding.idServiceTitle.text = item.title
            binding.idServicePrice.text = price
            Glide.with(itemView.context).load(item.image).into(binding.idIconService)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

           return when(viewType){
                2 -> GroupViewHolder(itemview = LayoutInflater.from(parent.context).inflate(R.layout.groupitemlistservice,parent,false))
                3 -> ImageViewHolder(itemview = LayoutInflater.from(parent.context).inflate(R.layout.imageitemservice,parent,false))
               else -> ItemViewHolder(itemview = LayoutInflater.from(parent.context).inflate(R.layout.itemservice,parent,false))
           }
    }


    override fun getItemViewType(position: Int): Int {
        val item: DomServices = dataService[position]
        return when(item.type_card){
            is TypeCardService.ItemService -> 1
            is TypeCardService.GroupService -> 2
            is TypeCardService.ImageService -> 3
        }

    }

    override fun getItemCount(): Int {
        return dataService.size
    }

    fun refreshAdapter(lService: MutableList<DomServices>?){
        when(lService){
            is MutableList<DomServices> -> {
                this.dataService.clear()
                this.dataService.addAll(lService)
                notifyDataSetChanged()
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataService[position]
        when(holder){
            is ItemViewHolder -> holder.bind(item)
            is GroupViewHolder -> holder.bind(item)
            is ImageViewHolder -> holder.bind(item)
        }
    }
}