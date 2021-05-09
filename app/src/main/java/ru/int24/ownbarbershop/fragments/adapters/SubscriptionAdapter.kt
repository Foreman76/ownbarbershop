package ru.int24.ownbarbershop.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.databinding.ItemSubscriptionBinding
import ru.int24.ownbarbershop.models.domen.DomSubscription

class SubscriptionAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val listSub: MutableList<DomSubscription> = mutableListOf()

    class SubscriptionViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        private val binding = ItemSubscriptionBinding.bind(itemView)

        fun bind(item: DomSubscription){
            val subNumber = "Номер: ${item.number}"
            binding.idNumberSubscription.text = subNumber
            binding.idBalanceStringSubscription.text = item.balance_string
            if (item.is_frozen){
                binding.idIsFrozenSubscription.text = itemView.context.getString(R.string.text_subscription_frozen)
            }else {
                binding.idIsFrozenSubscription.text = itemView.context.getString(R.string.text_subscription_not_freeze)
            }

            var chislo = false
            val period = item.period.toString()
            if (item.period > 1){
                chislo = true
            }

            when(item.period_unit_id){
                1 -> {
                    if (chislo){
                        binding.idPeriodSubscription.text = textPeriodAvailable(str="дней", period)
                    }else{
                        binding.idPeriodSubscription.text = textPeriodAvailable(str="день", period)
                    }
                }
                2 -> {
                    if (chislo){
                        binding.idPeriodSubscription.text = textPeriodAvailable(str="недель", period)
                    }else{
                        binding.idPeriodSubscription.text = textPeriodAvailable(str="неделя", period)
                    }
                }
                3 -> {
                    if (chislo){
                        binding.idPeriodSubscription.text = textPeriodAvailable(str="месяцев", period)
                    }else{
                        binding.idPeriodSubscription.text = textPeriodAvailable(str="месяц", period)
                    }
                }
                4 -> {
                    if (chislo){
                        binding.idPeriodSubscription.text = textPeriodAvailable(str="лет", period)
                    }else{
                        binding.idPeriodSubscription.text = textPeriodAvailable(str="год", period)
                    }
                }
            }
        }

        fun textPeriodAvailable(str:String, period:String): String{
            return itemView.context.getString(R.string.text_period_availability)+" "+ period+" "+"str"
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SubscriptionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_subscription, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SubscriptionViewHolder -> holder.bind(listSub[position])
        }
    }

    override fun getItemCount(): Int {
        return listSub.count()
    }

    fun refreshAdapter(listItemSub:MutableList<DomSubscription>){
        listSub.clear()
        listSub.addAll(listItemSub)
        notifyDataSetChanged()
    }
}