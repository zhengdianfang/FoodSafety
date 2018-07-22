package com.zhengdianfang.foodsafety.common.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class User(
        @PrimaryKey
        var id: Int,
        var username: String,
        var nickname: String
)