package com.example.sprint2.model

import com.example.sprint2.ShoppingItemConstants.ICON_IDS
import com.example.sprint2.ShoppingItemConstants.ITEM_NAMES_RAW
import java.io.Serializable

class FoodItem (val i: Int): Serializable {
    fun Icon(): Int {
        val icon: Int = ICON_IDS[i]
        return icon
    }
    fun Name(): String {
        return ITEM_NAMES_RAW[i]
    }
}

