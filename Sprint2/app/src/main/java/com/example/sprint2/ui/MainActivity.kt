package com.example.sprint2.ui

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.Intent.createChooser
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sprint2.R
import com.example.sprint2.ShoppingItemConstants
import com.example.sprint2.ShoppingItemConstants.ICON_IDS
import com.example.sprint2.adapter.FoodListAdapter
import com.example.sprint2.model.FoodItem
import com.example.sprint2.model.SelectedListItem
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

val foodList: ArrayList<FoodItem> = ArrayList()
val selectedList: ArrayList<String> = ArrayList()

class MainActivity : AppCompatActivity() {
    val context = this

    companion object {
        const val NOTIFICATION_ID = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    // ~Line 38-41: Adds food items to food list and pass them to the Recycler View
        for (i in 0 until ICON_IDS.size) {
            foodList.add(FoodItem(i))
        }

        recycler_view.setHasFixedSize(true)
        val adapter = FoodListAdapter(foodList)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_view.layoutManager = manager
        recycler_view.adapter = adapter

    // ~Line 50-81: Sets listener for send_list_bttn that creates a notification and sends implicit intent to other apps
        send_list_bttn.setOnClickListener {
        // **Line 46 - 67: Creates notification for send_list_bttn
            val channelId = "$packageName.simplechannel"
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "Simple Notification Channel"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val description = "Text"

                val channel = NotificationChannel(channelId, name, importance)
                channel.description = description

                notificationManager.createNotificationChannel(channel)
            }

            val builder = NotificationCompat.Builder(this, channelId)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setSmallIcon(R.drawable.bacon)
                .setContentTitle("Shopping List")
                .setContentText("List has been shared!")
            notificationManager.notify(NOTIFICATION_ID, builder.build())
            // **Line 69 - 79: Creates Implicit Intent to send selected item from selected list to another app
            val shoppingList = selectedList.joinToString(
                ", ",
                "Here's the items I bought!: "
            )
            val shareShoppingListIntent = Intent()

            shareShoppingListIntent.action = Intent.ACTION_SEND
            shareShoppingListIntent.type = "text/plain"
            shareShoppingListIntent.putExtra(Intent.EXTRA_TEXT, shoppingList)
            startActivity(createChooser(shareShoppingListIntent, "send to"))
        }
    }
}
