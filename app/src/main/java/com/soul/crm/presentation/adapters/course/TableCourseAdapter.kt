package com.soul.crm.presentation.adapters.course

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soul.crm.data.remote.models.response.course.CourseResult
import com.soul.crm.data.remote.models.response.payment.PaymentResult
import com.soul.crm.data.remote.models.response.user.PeopleResult
import com.soul.crm.databinding.TableCourseListBinding
import com.soul.crm.databinding.TablePaymentListBinding

class TableCourseAdapter : RecyclerView.Adapter<TableCourseAdapter.RowViewHolder>() {
    private val list = mutableListOf<CourseResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RowViewHolder(
        TableCourseListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int {
        return list.size // one more to add header row
    }

    fun setList(list: List<CourseResult>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class RowViewHolder(private val binding: TableCourseListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(model: CourseResult) {
            binding.apply {
                courseName.text = model.name
                about.text = model.description
                price.text = model.price
                actions.text = "null"
            }
        }
    }
}