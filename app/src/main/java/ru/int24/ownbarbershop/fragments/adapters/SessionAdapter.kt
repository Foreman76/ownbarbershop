package ru.int24.ownbarbershop.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.InterfaceSessionAdapter
import ru.int24.ownbarbershop.databinding.ItemsessiontimeBinding
import ru.int24.ownbarbershop.models.domen.DomSession

class SessionAdapter():RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listSession: MutableList<DomSession> = mutableListOf()
    private var delegate: InterfaceSessionAdapter? = null

    fun attachHadlerClickItem(myClick: InterfaceSessionAdapter){
        delegate = myClick
    }

    class SessionViewHolder(itemView: View, private val handlerOnClick: InterfaceSessionAdapter?): RecyclerView.ViewHolder(itemView){
        private val binding = ItemsessiontimeBinding.bind(itemView)

        fun bind(item: DomSession){
            binding.idTextSessionTime.text = item.time
            binding.idCardSessionTime.setOnClickListener { handlerOnClick?.handlerChooseSessionTime(item) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SessionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemsessiontime, parent , false), delegate)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SessionViewHolder -> holder.bind(listSession[position])
        }
    }

    override fun getItemCount(): Int {
        return listSession.size
    }

    fun refreshAdapter(listSessionTime: MutableList<DomSession>?){
        listSessionTime?.let {
            this.listSession.clear()
            this.listSession.addAll(listSessionTime)
            notifyDataSetChanged()
        }

    }
}