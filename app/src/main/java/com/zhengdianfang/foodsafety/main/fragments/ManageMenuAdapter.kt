package com.zhengdianfang.foodsafety.main.fragments

import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.main.model.MenuItem
import com.zhengdianfang.foodsafety.main.model.SubMenuItem
import com.zhengdianfang.miracleframework.adapter.base.BaseMultiItemQuickAdapter
import com.zhengdianfang.miracleframework.adapter.base.BaseViewHolder
import com.zhengdianfang.miracleframework.adapter.base.entity.MultiItemEntity
class ManageMenuAdapter(data: List<MultiItemEntity>?)
    : BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {

    init {
        addItemType(MenuItem.MAIN_MENU_ITEM, R.layout.manage_menu_item)
        addItemType(SubMenuItem.SUB_MENU_ITEM, R.layout.manage_sub_menu_item)
    }

    override fun convert(holder: BaseViewHolder, item: MultiItemEntity?) {
        when(holder.itemViewType) {
            MenuItem.MAIN_MENU_ITEM -> bindMainMenuDataToView(holder, item as MenuItem)
            SubMenuItem.SUB_MENU_ITEM -> bindSubMenuDataToView(holder, item as SubMenuItem)
        }
    }

    private fun bindMainMenuDataToView(holder: BaseViewHolder, item: MenuItem) {
        val mainMenuView = holder.getView<TextView>(R.id.menuNameView)
        mainMenuView.text = item.name
        val context = holder.itemView.context
        val leftDrawableId = context.resources.getIdentifier("ic_left_menu_${item.id}",
                "drawable", context.packageName)
        val leftDrawable = ContextCompat.getDrawable(context, leftDrawableId)!!
        DrawableCompat.setTint(leftDrawable, ContextCompat.getColor(context, R.color.colorPrimary))
        mainMenuView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                leftDrawable,
                null,
                null,
               null
        )
        mainMenuView.setOnClickListener {
            if (item.isExpanded) {
                collapse(holder.adapterPosition)
            } else {
                expand(holder.adapterPosition)
            }
        }
        val menuEnableSwitch = holder.getView<Switch>(R.id.menuEnableSwitch)
        menuEnableSwitch.isChecked = item.enable
        menuEnableSwitch.setOnCheckedChangeListener(MainMenuSwitchChangeListener(item))
    }

    private fun bindSubMenuDataToView(holder: BaseViewHolder, item: SubMenuItem) {
        val subMenuView = holder.getView<TextView>(R.id.menuNameView)
        subMenuView.text = item.name
        val context = holder.itemView.context
        val leftDrawableId = context.resources.getIdentifier("ic_left_menu_${item.parentMenuId}_${item.id}",
                "drawable", context.packageName)
        val leftDrawable = ContextCompat.getDrawable(context, if(leftDrawableId <= 0) R.drawable.ic_left_menu_1_1 else leftDrawableId)!!
        DrawableCompat.setTint(leftDrawable, ContextCompat.getColor(context, R.color.colorLowerBlack))

        subMenuView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                leftDrawable,
                null,
                null,
               null
        )
        val menuEnableCheckBox = holder.getView<CheckBox>(R.id.menuEnableCheckBox)
        menuEnableCheckBox.isChecked = item.enable
        menuEnableCheckBox.setOnCheckedChangeListener(SubMenuCheckBoxChangeListener(item))
    }

    private inner class SubMenuCheckBoxChangeListener(private val item: SubMenuItem): CompoundButton.OnCheckedChangeListener{
        override fun onCheckedChanged(compoundButton: CompoundButton, checked: Boolean) {
            if (checked) {
                compoundButton.setBackgroundResource(R.drawable.manage_sub_menu_checkbox_checked_background)
            } else {
                compoundButton.setBackgroundResource(R.drawable.manage_sub_menu_checkbox_unchecked_background)
            }
            item.enable = checked
        }
    }

    private inner class MainMenuSwitchChangeListener(private val item: MenuItem): CompoundButton.OnCheckedChangeListener  {
        override fun onCheckedChanged(compoundButton: CompoundButton, checked: Boolean) {
            item.enable = checked
        }
    }
}