package com.soul.crm.presentation.adapters.payment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soul.crm.data.remote.models.response.payment.PaymentResult
import com.soul.crm.data.remote.models.response.user.PeopleResult
import com.soul.crm.databinding.TablePaymentListBinding

class TablePaymentAdapter : RecyclerView.Adapter<TablePaymentAdapter.RowViewHolder>() {
    private val list = mutableListOf<PaymentResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RowViewHolder(
        TablePaymentListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int {
        return list.size // one more to add header row
    }

    fun setList(list: List<PaymentResult>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class RowViewHolder(private val binding: TablePaymentListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(model: PaymentResult) {
            binding.apply {
                amount.text = model.value
                date.text = model.date
                balance.text = model.user_balance
                name.text = model.full_name
            }
        }
    }
}