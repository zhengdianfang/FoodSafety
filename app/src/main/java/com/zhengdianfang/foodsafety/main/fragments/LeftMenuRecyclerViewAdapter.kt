package com.zhengdianfang.foodsafety.main.fragments


import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.main.model.MenuItem
import com.zhengdianfang.foodsafety.main.model.SubMenuItem
import com.zhengdianfang.miracleframework.adapter.base.BaseMultiItemQuickAdapter
import com.zhengdianfang.miracleframework.adapter.base.BaseViewHolder
import com.zhengdianfang.miracleframework.adapter.base.entity.MultiItemEntity

class LeftMenuRecyclerViewAdapter(
        data: List<MultiItemEntity>?) :
        BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {


    init {
        addItemType(MenuItem.MAIN_MENU_ITEM, R.layout.main_left_menu_item)
        addItemType(SubMenuItem.SUB_MENU_ITEM, R.layout.main_left_sub_menu_item)
    }

    override fun convert(holder: BaseViewHolder, item: MultiItemEntity) {
        when(holder.itemViewType) {
            MenuItem.MAIN_MENU_ITEM -> bindMainMenuDataToView(holder, item as MenuItem)
            SubMenuItem.SUB_MENU_ITEM -> bindSubMenuDataToView(holder, item as SubMenuItem)
        }
    }

    private fun bindMainMenuDataToView(holder: BaseViewHolder, item: MenuItem) {
        holder.setText(R.id.mainMenuTextView, item.name)
        holder.itemView.setOnClickListener {
            if (item.isExpanded) {
                collapse(holder.adapterPosition)
            } else {
                expand(holder.adapterPosition)
            }
        }
    }

    private fun bindSubMenuDataToView(holder: BaseViewHolder, item: SubMenuItem) {
        holder.setText(R.id.subMenuTextView, item.name)
    }
}
