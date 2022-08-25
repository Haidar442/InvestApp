package com.coding.investapp.presentation.portfolio_list

import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.coding.investapp.databinding.PorfolioItemBinding
import com.coding.investapp.domain.model.Portfolio


class PortfolioAdapter :

    RecyclerView.Adapter<PortfolioAdapter.ViewHolder>() {
    var onItemClick: ((Portfolio) -> Unit)? = null
    var portfolios: List<Portfolio> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            PorfolioItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (portfolios.isNotEmpty()) {
            holder.bind(portfolios[position])
            holder.itemView.setOnClickListener {
                onItemClick?.invoke(portfolios[position])
            }
        }

    }

    fun submitData(portfolios: List<Portfolio>) {
        this.portfolios = portfolios
    }

    inner class ViewHolder(binding: PorfolioItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val nameView: TextView = binding.name
        private val balanceView: TextView = binding.balance
        fun bind(portfolio: Portfolio) {
            nameView.text = portfolio.name

            val format = NumberFormat.getCurrencyInstance()
            format.maximumFractionDigits = 0
            format.currency = Currency.getInstance("USD")
            val formattedNumber: String = format.format(portfolio.balance)
            balanceView.text = formattedNumber
        }

    }

    class PortfolioDiffCallback : DiffUtil.ItemCallback<Portfolio>() {
        override fun areItemsTheSame(oldItem: Portfolio, newItem: Portfolio): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Portfolio, newItem: Portfolio): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun getItemCount(): Int {
        return portfolios.size
    }
}