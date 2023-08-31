package com.ashu.mftracker.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.ashu.mftracker.R
import com.ashu.mftracker.data.response.MutualFundWithoutNav
import javax.inject.Inject
import kotlin.collections.ArrayList

class HomeRecyclerAdapter @Inject constructor():
    RecyclerView.Adapter<HomeRecyclerAdapter.HomeRecyclerAdapterViewHolder>(), Filterable {

    var onItemClick: ((MutualFundWithoutNav) -> Unit)? = null
    var mutualFundWithoutNavList: ArrayList<MutualFundWithoutNav>? = null
    var mutualFundWithoutNav: ArrayList<MutualFundWithoutNav>? = null

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) mutualFundWithoutNav = mutualFundWithoutNav else { val filteredList = ArrayList<MutualFundWithoutNav>()
                    mutualFundWithoutNav!!
                        .filter {
                            (it.schemeName.contains(constraint!!, true)) or
                                    (it.schemeCode.contains(constraint))
                        }
                        .forEach { filteredList.add(it) }
                    mutualFundWithoutNavList = filteredList

                }
                return FilterResults().apply { values = mutualFundWithoutNavList }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                mutualFundWithoutNavList = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<MutualFundWithoutNav>
                notifyDataSetChanged()
            }

        }
    }

    inner class HomeRecyclerAdapterViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val textMutualFundSchemeId: AppCompatTextView
        val textMutualFundSchemeName: AppCompatTextView

        init {
            textMutualFundSchemeId = view.findViewById(R.id.tv_mf_scheme_id)
            textMutualFundSchemeName = view.findViewById(R.id.tv_mf_name)

            view.setOnClickListener {
                mutualFundWithoutNavList?.get(adapterPosition)
                    ?.let { it1 -> onItemClick?.invoke(it1) }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeRecyclerAdapterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_item, parent, false)

        return HomeRecyclerAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeRecyclerAdapterViewHolder, position: Int) {
        holder.apply {
            textMutualFundSchemeId.text = mutualFundWithoutNavList?.get(position)?.schemeCode
            textMutualFundSchemeName.text = mutualFundWithoutNavList?.get(position)?.schemeName
        }
    }

    fun loadData(listOfMutualFundWithoutNav: ArrayList<MutualFundWithoutNav>?) {
        mutualFundWithoutNav = listOfMutualFundWithoutNav
        mutualFundWithoutNavList = mutualFundWithoutNav
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if (mutualFundWithoutNavList != null)
            return mutualFundWithoutNavList?.size!!
        else
            return 0
    }
}