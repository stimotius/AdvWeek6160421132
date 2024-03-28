package com.ubaya.advweek6160421132.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.advweek6160421132.databinding.CoffeeListItemBinding
import com.ubaya.advweek6160421132.model.Coffee

class CoffeeListAdapter(val coffeeList:ArrayList<Coffee>):RecyclerView.Adapter<CoffeeListAdapter.StudentViewHolder>()
{
    class StudentViewHolder(var binding: CoffeeListItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = CoffeeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return coffeeList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.binding.txtNama.text = coffeeList[position].nama
        holder.binding.txtHarga.text = coffeeList[position].harga
        holder.binding.txtDeskripsi.text = coffeeList[position].deskripsi

        holder.binding.btnDetail.setOnClickListener {
            val action = CoffeeListFragmentDirections.actionCoffeeDetail()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateStudentList(newStudentList: ArrayList<Coffee>) {
        coffeeList.clear()
        coffeeList.addAll(newStudentList)
        notifyDataSetChanged()
    }


}
