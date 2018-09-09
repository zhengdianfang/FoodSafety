package com.zhengdianfang.foodsafety.main.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Relation
import com.zhengdianfang.miracleframework.adapter.base.entity.IExpandable
import com.zhengdianfang.miracleframework.adapter.base.entity.MultiItemEntity

class MenuItem: IExpandable<SubMenuItem>, MultiItemEntity {

    companion object {
        const val MAIN_MENU_ITEM = 1
    }
    var id: Int = 0
    var name: String = ""
    var icon: String = ""
    var enable: Boolean = true

    @Ignore
    var expandable  = false

    @Relation(parentColumn = "id", entityColumn = "parentMenuId", entity = SubMenuItem::class)
    var subMenuItems: List<SubMenuItem>? = null

    override fun isExpanded(): Boolean {
        return expandable
    }

    override fun setExpanded(expanded: Boolean) {
        this.expandable = expanded
    }

    override fun getSubItems(): MutableList<SubMenuItem> {
        return subMenuItems?.toMutableList() ?: mutableListOf()
    }

    override fun getLevel(): Int {
        return 0
    }

    override fun getItemType(): Int {
        return MAIN_MENU_ITEM
    }

    fun createMainMenuItem (): MainMenuItem {
        return MainMenuItem(id, name, icon, enable)
    }
}

@Entity
data class MainMenuItem (
        @PrimaryKey
        var id:Int,
        var name: String,
        var icon: String,
        var enable: Boolean = true
)

@Entity
data class SubMenuItem(
        @PrimaryKey
        var id: Int,
        var parentMenuId: Int,
        var name: String,
        var icon: String,
        var enable: Boolean = true
) : MultiItemEntity {

    companion object {
        const val SUB_MENU_ITEM = 2
    }


    override fun getItemType(): Int {
        return SUB_MENU_ITEM
    }
}