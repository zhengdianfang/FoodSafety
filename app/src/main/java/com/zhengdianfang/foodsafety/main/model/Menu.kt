package com.zhengdianfang.foodsafety.main.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Relation

class MenuItem {

    var id: Int = 0
    var name: String = ""
    var icon: String = ""

    @Relation(parentColumn = "id", entityColumn = "parentMenuId", entity = SubMenuItem::class)
    var subMenuItems: List<SubMenuItem>? = null
}

@Entity
data class MainMenuItem (
        var name: String,
        var icon: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Entity
data class SubMenuItem(
        var parentMenuId: Int,
        var name: String,
        var icon: String,
        var enable: Boolean = true
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}