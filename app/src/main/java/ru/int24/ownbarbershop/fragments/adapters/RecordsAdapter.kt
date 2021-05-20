package ru.int24.ownbarbershop.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.databinding.ItemrecordBinding
import ru.int24.ownbarbershop.models.domen.DomRecordServices
import ru.int24.ownbarbershop.models.domen.DomRecords
import ru.int24.ownbarbershop.utilits.GetDataFormat

class RecordsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val dataSet = ArrayList<DomRecords>()

    class ItemRecordViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        private val binding = ItemrecordBinding.bind(itemView)

        fun bind(item:DomRecords){

            val listService = getListService(item.services)
            val totalCost = "Стоимость:     ${getTotalCoast(item.services).toString()} ${item.company.currency_short_title}"

            binding.idRecordNameCompany.text = item.company.title
            binding.idRecordDatetime.text = GetDataFormat.getUserFormatStringDateFromStringDate(
                    "yyyy-MM-dd'T'HH:mm:ssZ",
                    "dd MMMM yyyy HH:mm",
                    item.datetime.replaceAfter("+", GetDataFormat.getCurrentOffset()))
            binding.idRecordListServices.text = listService
            binding.idRecordCoastService.text = totalCost
            binding.idRecordPhone.text = item.company.phone
            binding.idRecordSite.text = item.company.site

        }

        private fun getTotalCoast(arrayList: java.util.ArrayList<DomRecordServices>): Float {
            var cost:Float = 0F

            if (arrayList.count() == 1){
                cost = arrayList[0].cost
            }else{
                arrayList.forEach {
                    cost += it.cost
                }
            }
            return cost
        }

        private fun getListService(arrayList: ArrayList<DomRecordServices>): String {
            var stringService = ""

            if (arrayList.count() == 1){
                stringService = arrayList[0].title
            }else{
                arrayList.forEach {
                    stringService = stringService+it.title+"/"
                }
            }
            return stringService
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemRecordViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemrecord, parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ItemRecordViewHolder -> holder.bind(dataSet[position])
        }
    }

    override fun getItemCount(): Int {
        return dataSet.count()
    }

    fun refreshAdapter(data:ArrayList<DomRecords>?){
        data?.let {
            dataSet.clear()
            dataSet.addAll(it)
            notifyDataSetChanged()
        }


    }
}