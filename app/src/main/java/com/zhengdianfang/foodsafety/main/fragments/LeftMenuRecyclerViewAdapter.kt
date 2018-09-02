package com.zhengdianfang.foodsafety.main.fragments


import android.widget.TextView
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.main.model.MenuItem
import com.zhengdianfang.foodsafety.main.model.SubMenuItem
import com.zhengdianfang.miracleframework.adapter.base.BaseMultiItemQuickAdapter
import com.zhengdianfang.miracleframework.adapter.base.BaseViewHolder
import com.zhengdianfang.miracleframework.adapter.base.entity.MultiItemEntity

class LeftMenuRecyclerViewAdapter(data: List<MultiItemEntity>) :
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
        val mainMenuView = holder.itemView as TextView
        mainMenuView.text = item.name
        val context = holder.itemView.context
        val leftDrawableId = context.resources.getIdentifier("ic_left_menu_${item.id}",
                "drawable", context.packageName)

        val rightDrawableId = if (item.expandable) R.drawable.ic_arrow_down else R.drawable.ic_arrow_down
        mainMenuView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                leftDrawableId,
                0,
                rightDrawableId,
                0
        )
        mainMenuView.setOnClickListener {
            if (item.isExpanded) {
                collapse(holder.adapterPosition)
            } else {
                expand(holder.adapterPosition)
            }
        }
    }

    private fun bindSubMenuDataToView(holder: BaseViewHolder, item: SubMenuItem) {
        val subMenuView = holder.itemView as TextView
        subMenuView.text = item.name
        val context = holder.itemView.context
        val leftDrawableId = context.resources.getIdentifier("ic_left_menu_${item.parentMenuId}_${item.id}",
                "drawable", context.packageName)
        subMenuView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                if(leftDrawableId <= 0) R.drawable.ic_left_menu_1_1 else leftDrawableId,
                0,
                R.drawable.ic_arrow_right,
                0
        )

    }
}
