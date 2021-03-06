package com.zhengdianfang.foodsafety.main.fragments


import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.widget.TextView
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.main.constants.SharedPreferencesKeys.LEFT_MENU_GRID_STYLE
import com.zhengdianfang.foodsafety.main.constants.SharedPreferencesKeys.LEFT_MENU_LIST_STYLE
import com.zhengdianfang.foodsafety.main.model.MenuItem
import com.zhengdianfang.foodsafety.main.model.SubMenuItem
import com.zhengdianfang.miracleframework.adapter.base.BaseMultiItemQuickAdapter
import com.zhengdianfang.miracleframework.adapter.base.BaseViewHolder
import com.zhengdianfang.miracleframework.adapter.base.entity.MultiItemEntity

class LeftMenuRecyclerViewAdapter(data: List<MultiItemEntity>, private val style: String) :
        BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {


    init {
        addItemType(MenuItem.MAIN_MENU_ITEM, R.layout.main_left_menu_item)
        when(style) {
            LEFT_MENU_LIST_STYLE ->
                addItemType(SubMenuItem.SUB_MENU_ITEM, R.layout.main_left_sub_menu_list_item)
            LEFT_MENU_GRID_STYLE ->
                addItemType(SubMenuItem.SUB_MENU_ITEM, R.layout.main_left_sub_menu_grid_item)
        }
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
        val leftDrawable = ContextCompat.getDrawable(context, leftDrawableId)
        DrawableCompat.setTint(leftDrawable!!, Color.WHITE)

        val rightDrawableId = if (item.expandable) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
        mainMenuView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                leftDrawable,
                null,
                ContextCompat.getDrawable(context, rightDrawableId),
               null
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

        val leftDrawable = ContextCompat.getDrawable(context, if(leftDrawableId <= 0) R.drawable.ic_left_menu_1_1 else leftDrawableId)
        DrawableCompat.setTint(leftDrawable!!, Color.WHITE)

        when(style) {
            LEFT_MENU_LIST_STYLE -> {
                subMenuView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        leftDrawable,
                        null,
                        ContextCompat.getDrawable(context, R.drawable.ic_arrow_right),
                       null
                )
            }
            LEFT_MENU_GRID_STYLE -> {
                subMenuView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        null
                        ,
                        leftDrawable,
                        null,
                        null
                )
            }
        }

    }
}
