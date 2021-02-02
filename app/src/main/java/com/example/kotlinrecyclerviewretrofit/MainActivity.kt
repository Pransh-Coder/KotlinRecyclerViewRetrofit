package com.example.kotlinrecyclerviewretrofit

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    //lateinit - For late initialization of variables. When you are sure that it will be initialized in future
    lateinit var recyclerView: RecyclerView
    lateinit var progressDialog: ProgressDialog
    lateinit var recyclerAdapter:RecyclerAdapter      //var recyclerAdapter:RecyclerAdapter?= null
    var photoList = ArrayList<Photos>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.recyclerView);
        /*recyclerAdapter = RecyclerAdapter(this,photoList)
        recyclerView.adapter = recyclerAdapter*/
        //recyclerAdapter = RecyclerAdapter(this,photoList)

        recyclerView.layoutManager =  LinearLayoutManager(this)

        getData()

    }

    private fun getData() {

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait..")
        progressDialog.setMessage("Loading items....")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val call: Call<List<Photos>> = ApiClient.getClient.getPhotos()
        call.enqueue(object : Callback<List<Photos>> {

            override fun onResponse(call: Call<List<Photos>>?, response: Response<List<Photos>>?) {

                progressDialog.dismiss()
                response?.body()?.let {     //It is used to check if both response and body are null then only add items to photoList
                    photoList.addAll(it)
                    recyclerAdapter = RecyclerAdapter(this@MainActivity,photoList)
                    recyclerView.adapter = recyclerAdapter
                    //recyclerAdapter.notifyDataSetChanged()
                }


            }

            override fun onFailure(call: Call<List<Photos>>?, t: Throwable?) {
                progressDialog.dismiss()
            }
        })
    }
}
