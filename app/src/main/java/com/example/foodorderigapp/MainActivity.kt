package com.example.foodorderigapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

class MainActivity : AppCompatActivity(), FoodItemClicked {
    private lateinit var recyclerView: RecyclerView
    private lateinit var mAdapter:Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchData()
        mAdapter = Adapter(this)
        recyclerView.adapter = mAdapter
    }

    private fun fetchData(){
        val url="https://run.mocky.io/v3/268860e9-4491-4944-9f62-35e6488fc1d1"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener{
                val foodJsonArray=it.getJSONArray("items")
                val foodArray=ArrayList<Food>()
                for(i in 0 until foodJsonArray.length()){
                    val foodJsonObject=foodJsonArray.getJSONObject(i)
                    val food=Food(
                        foodJsonObject.getString(  "title" ),
                        foodJsonObject.getString(  "description" ),
                        foodJsonObject.getString(  "image_Url" ),
                        foodJsonObject.getString(  "cost" )
                    )
                    foodArray.add(food)
                }
                mAdapter.updateFood(foodArray)
            },
            Response.ErrorListener{

            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    override fun onItemClicked(item: Food) {
    }
}
