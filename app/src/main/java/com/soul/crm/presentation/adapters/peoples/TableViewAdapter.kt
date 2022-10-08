package com.soul.crm.presentation.adapters.peoples

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.soul.crm.R
import com.soul.crm.data.remote.models.response.user.PeopleResult
import com.soul.crm.databinding.TableListPeopleBinding

class TableViewAdapter(private val context:Activity) : RecyclerView.Adapter<TableViewAdapter.RowViewHolder>() {
    private val list = mutableListOf<PeopleResult>()
    private var pos = -1
    private var isEnable = false
    private var isSelectAll = false
    private val listSelect = mutableListOf<PeopleResult>()

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
                select.visibility = View.GONE
            }

//            itemView.setOnLongClickListener {
//                if (!isEnable){
//                    val callback = object : ActionMode.Callback{
//                        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean    {
//                            val menuInflater = mode?.menuInflater
//                            menuInflater?.inflate(R.menu.select_menu,menu)
//                            return false
//                        }
//
//                        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
//                            isEnable = true
//                            click()
//                            return true
//                        }
//
//                        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
//                            when(item?.itemId){
//                                R.id.coursesFragment->{
//                                    for (s:PeopleResult in listSelect){
//                                        list.remove(s)
//                                    }
//                                    if (list.size == 0){
//                                        Toast.makeText(binding.root.context, "Empty", Toast.LENGTH_SHORT).show()
//                                    }
//                                    mode?.finish()
//                                }
//                                R.id.paymentFragment->{
//                                    if (listSelect.size == list.size){
//                                        isSelectAll = false
//                                        listSelect.clear()
//                                    }else{
//                                        isSelectAll = true
//                                        listSelect.clear()
//                                        listSelect.addAll(list)
//                                    }
//                                    notifyDataSetChanged()
//                                }
//                            }
//                            return true
//                        }
//
//                        override fun onDestroyActionMode(mode: ActionMode?) {
//                            isEnable = false
//                            isSelectAll = false
//                            listSelect.clear()
//                            notifyDataSetChanged()
//                        }
//                    }
//                    context.startActionMode(callback)
//                }else{
//                    click()
//                }
//                return@setOnLongClickListener true
//            }

//            itemView.setOnClickListener {
//                if (isEnable){
//                    click()
//                }else{
//                    Toast.makeText(binding.root.context, "You clicked $model", Toast.LENGTH_SHORT).show()
//                }
//            }

//            if (isSelectAll){
//                binding.select.visibility = View.VISIBLE
//            }else{
//                binding.select.visibility = View.GONE
//            }
        }

        private fun click(){
            if (binding.select.visibility == View.GONE){
                binding.select.visibility = View.VISIBLE
                listSelect.add(list[layoutPosition])
            }else{
                binding.select.visibility = View.VISIBLE
                listSelect.remove(list[layoutPosition])
            }
        }
    }
}




//            var click = true
//            binding.select.setOnClickListener {
//                if (click){
//                    click = false
//                    binding.select.setImageResource(R.drawable.unselect)
//                }else{
//                    click = true
//                    binding.select.setImageResource(R.drawable.selected_image)
//                }
//            }