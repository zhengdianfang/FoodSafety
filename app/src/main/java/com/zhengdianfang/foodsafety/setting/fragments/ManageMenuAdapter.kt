package com.zhengdianfang.foodsafety.setting.fragments

import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.main.model.MenuItem
import com.zhengdianfang.foodsafety.main.model.SubMenuItem
import com.zhengdianfang.miracleframework.adapter.base.BaseMultiItemQuickAdapter
import com.zhengdianfang.miracleframework.adapter.base.BaseViewHolder
import com.zhengdianfang.miracleframework.adapter.base.entity.MultiItemEntity

class ManageMenuAdapter(data: MutableList<MultiItemEntity>?)
    : BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {

    init {
        addItemType(MenuItem.MAIN_MENU_ITEM, R.layout.manage_menu_item)
        addItemType(SubMenuItem.SUB_MENU_ITEM, R.layout.main_left_sub_menu_list_item)
    }

    override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {
    }
}