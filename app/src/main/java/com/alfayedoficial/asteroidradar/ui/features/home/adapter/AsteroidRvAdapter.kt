package com.alfayedoficial.asteroidradar.ui.features.home.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import com.alfayedoficial.asteroidradar.BR
import com.alfayedoficial.asteroidradar.R
import com.alfayedoficial.asteroidradar.callback.OnRecyclerViewIsSelected
import com.alfayedoficial.asteroidradar.core.common.adapter.BaseAdapter
import com.alfayedoficial.asteroidradar.core.common.adapter.BaseViewHolder
import com.alfayedoficial.asteroidradar.core.common.adapter.DiffCallBack
import com.alfayedoficial.asteroidradar.databinding.ItemRvAsteroidBinding
import com.alfayedoficial.coreModel.local.AsteroidEarth
import com.alfayedoficial.kotlinutils.kuGetBindingRow

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do : CustomersRvAdapter
 * @see AsteroidEarth
 * Date 21/7/2022 - 3:28 PM
 */
class AsteroidRvAdapter : BaseAdapter<AsteroidEarth>() {

    private var mDiffer = AsyncListDiffer(this, DiffCallBack<AsteroidEarth>())
    private var dataList: List<AsteroidEarth> = arrayListOf()
    private lateinit var selector: OnRecyclerViewIsSelected

    override fun setDataList(dataList: List<AsteroidEarth>) {
        this.dataList = dataList
        mDiffer.submitList(dataList)
    }

    override fun addDataList(dataList: List<AsteroidEarth>) {
        clearDataList()
        setDataList(dataList)
        notifyDataSetChanged()
    }

    override fun clearDataList() {
        this.dataList = arrayListOf()
    }

    fun initSelector(selector: OnRecyclerViewIsSelected) {
        this.selector = selector
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<AsteroidEarth> {
        return ViewHolderCustomer(kuGetBindingRow(parent, R.layout.item_rv_asteroid) as ItemRvAsteroidBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<AsteroidEarth>, position: Int) {
        val model = mDiffer.currentList[position]
        holder.apply {
            bind(model)

            itemView.setOnClickListener {
                selector.onSelected(data = model)
            }
        }
    }

    override fun getItemCount(): Int = mDiffer.currentList.size

    inner class ViewHolderCustomer(binding: ItemRvAsteroidBinding) : BaseViewHolder<AsteroidEarth>(binding) {

        override var itemRowBinding: ViewDataBinding = binding

        override fun bind(result: AsteroidEarth) {
            itemRowBinding.apply {
                setVariable(BR.model, result)
                executePendingBindings()
            }
        }
    }
}



