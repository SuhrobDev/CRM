package com.soul.crm.presentation.adapters.peoples

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.soul.crm.R
import com.soul.crm.data.remote.models.response.user.PeopleResult
import com.soul.crm.databinding.TableListPeopleBinding

class TableViewAdapter : RecyclerView.Adapter<TableViewAdapter.RowViewHolder>() {
    private val list = mutableListOf<PeopleResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RowViewHolder(
        TableListPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int {
        return list.size // one more to add header row
    }

    fun setList(list: List<PeopleResult>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class RowViewHolder(private val binding: TableListPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(model: PeopleResult) {
            binding.apply {
                teacher.text = ""
                group.text = ""
                phoneNumber.text = model.phone_number
                name.text = "${model.first_name} ${model.last_name}"
            }
        }
    }
}