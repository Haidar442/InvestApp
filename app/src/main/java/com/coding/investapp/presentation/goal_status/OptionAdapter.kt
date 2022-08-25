package com.coding.investapp.presentation.goal_status

import android.graphics.Color
import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coding.investapp.databinding.OptionItemBinding
import com.coding.investapp.domain.model.Option


class OptionAdapter :
    ListAdapter<Option, OptionAdapter.ViewHolder>(OptionDiffCallback()) {
    var onItemClick: ((Option) -> Unit)? = null
    private var selectedPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            OptionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        if (selectedPosition == position) {
            holder.itemView.isSelected = true //using selector drawable
            holder.itemView.setBackgroundColor(Color.BLUE)
            holder.nameView.setTextColor(Color.WHITE)
            holder.descriptionView.setTextColor(Color.WHITE)
            holder.depositView.setTextColor(Color.WHITE)
            holder.optionView.setTextColor(Color.WHITE)
            holder.selectedView.visibility = View.VISIBLE
            holder.selectedView.setTextColor(Color.WHITE)

        } else {
            holder.itemView.isSelected = false
            holder.itemView.setBackgroundColor(Color.WHITE)
            holder.nameView.setTextColor(Color.BLACK)
            holder.descriptionView.setTextColor(Color.BLACK)
            holder.depositView.setTextColor(Color.BLACK)
            holder.optionView.setTextColor(Color.BLACK)
            holder.selectedView.visibility = View.INVISIBLE
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(getItem(position))
            if (selectedPosition >= 0) notifyItemChanged(selectedPosition)
            selectedPosition = holder.adapterPosition
            notifyItemChanged(selectedPosition)
        }

    }

    fun submitData(options: List<Option>) {
        super.submitList(options)
    }

    inner class ViewHolder(binding: OptionItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameView: TextView = binding.name
        val descriptionView: TextView = binding.description
        val depositView: TextView = binding.deposit
        val optionView: TextView = binding.optionNumber
        val selectedView: TextView = binding.selected
        fun bind(option: Option) {
            optionView.text = "Option${(position + 1)}"
            nameView.text = option.name
            descriptionView.text = option.short_description

            val formatter = NumberFormat.getCurrencyInstance()
            formatter.maximumFractionDigits = 0
            formatter.currency = Currency.getInstance("USD")

            val myNumber = option.risk_score * 1000

            val formattedNumber: String = formatter.format(myNumber)
            depositView.text = formattedNumber
        }

    }

    class OptionDiffCallback : DiffUtil.ItemCallback<Option>() {
        override fun areItemsTheSame(oldItem: Option, newItem: Option): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Option, newItem: Option): Boolean {
            return oldItem.name == newItem.name
        }
    }
}