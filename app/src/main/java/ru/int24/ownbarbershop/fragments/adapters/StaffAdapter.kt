package ru.int24.ownbarbershop.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.int24.ownbarbershop.R
import ru.int24.ownbarbershop.UiInterface.InterfaceStaffAdapter
import ru.int24.ownbarbershop.databinding.ItemstaffBinding
import ru.int24.ownbarbershop.models.domen.DomStaff

class StaffAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val listStaff: MutableList<DomStaff> = mutableListOf()
    private var delegate: InterfaceStaffAdapter? = null

    fun attachCallBack(myClick: InterfaceStaffAdapter){
        delegate = myClick
    }

    class StaffViewHolder(itemview:View, private val myClick: InterfaceStaffAdapter?): RecyclerView.ViewHolder(itemview){
        val binding = ItemstaffBinding.bind(itemView)

        fun bind(item: DomStaff){
            when(item.bookable){
                true -> bindingStaffAvailability(item)
                false -> bindingStaffNotAvailability(item)
            }


        }

        private fun bindingStaffNotAvailability(item: DomStaff) {
            val raiting:String = "(${item.rating.toString()})"
            val comments:String = "(${item.comments_count.toString()})"
            binding.idStaffName.text = item.name
            binding.idStaffName.setTextColor(itemView.context.resources.getColor(R.color.textcolor_yclients))
            binding.idStaffIconComment.setImageResource(R.drawable.ic_baseline_comment_24_off)
            binding.idStaffSpec.text = item.specialization
            binding.idStaffValueComment.text = comments
            binding.idStaffValueRaiting.text = raiting
            Glide.with(itemView.context).load(item.avatar).circleCrop().into(binding.idStaffAvatar)

        }


        private fun bindingStaffAvailability(item: DomStaff) {
            val raiting:String = "(${item.rating.toString()})"
            val comments:String = "(${item.comments_count.toString()})"
            binding.idStaffName.text = item.name
            binding.idStaffSpec.text = item.specialization
            binding.idStaffValueComment.text = comments
            binding.idStaffValueRaiting.text = raiting
            binding.idStaffRatingBarDefault.rating = 3F
            Glide.with(itemView.context).load(item.avatar).circleCrop().into(binding.idStaffAvatar)
            binding.idItemCardStaff.setOnClickListener { myClick?.chooseStaff(item) }
            binding.idStaffInfo.setOnClickListener { myClick?.showInfo(item) }
        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StaffAdapter.StaffViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemstaff, parent, false), delegate)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = listStaff[position]
        when(holder){
            is StaffAdapter.StaffViewHolder -> holder.bind(item)
        }
    }

    override fun getItemCount(): Int {
        return listStaff.size
    }

    fun refreshAdapter(lService: MutableList<DomStaff>?){
        when(lService){
            is MutableList<DomStaff> -> {
                this.listStaff.clear()
                this.listStaff.addAll(lService)
                notifyDataSetChanged()
            }
        }

    }
}