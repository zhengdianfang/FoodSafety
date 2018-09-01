package com.zhengdianfang.foodsafety.main.fragments


import android.view.View
import com.zhengdianfang.foodsafety.main.model.MenuItem
import com.zhengdianfang.miracleframework.adapter.base.BaseSectionQuickAdapter
import com.zhengdianfang.miracleframework.adapter.base.BaseViewHolder
import com.zhengdianfang.miracleframework.adapter.base.entity.SectionEntity

class LeftMenuRecyclerViewAdapter(
        layoutResId: Int,
        data: MutableList<SectionEntity<MenuItem>>?) :
        BaseSectionQuickAdapter<SectionEntity<MenuItem>, LeftMenuRecyclerViewAdapter.ViewHolder>(layoutResId, data) {

    override fun convertHead(helper: ViewHolder?, item: SectionEntity<MenuItem>?) {
    }

    override fun convert(helper: ViewHolder?, item: SectionEntity<MenuItem>?) {
    }


    inner class ViewHolder(itemView: View?) : BaseViewHolder(itemView) {

    }

}
