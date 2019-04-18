package es.beginescarrascochristian.globofly.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import es.beginescarrascochristian.globofly.R
import es.beginescarrascochristian.globofly.helpers.DestinationAdapter
import es.beginescarrascochristian.globofly.models.Destination
import es.beginescarrascochristian.globofly.services.DestinationService
import es.beginescarrascochristian.globofly.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_destiny_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destiny_list)
        setSupportActionBar(toolbar)

        toolbar.title = title

        fab.setOnClickListener {
            val intent = Intent(this@DestinationListActivity,DestinationCreateActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        loadDestinations()
    }

    private fun loadDestinations() {
        val destinationService:DestinationService = ServiceBuilder.buildService(DestinationService::class.java)
        val requestCall : Call<List<Destination>> = destinationService.getDetinationList()

        requestCall.enqueue(object : Callback<List<Destination>> {
            override fun onResponse(call: Call<List<Destination>>, response: Response<List<Destination>>) {
                if(response.isSuccessful){
                    val destinationList : List<Destination> = response.body() !!
                    destiny_recycler_view.adapter = DestinationAdapter(destinationList)
                }else if(response.code() == 401){
                    Toast.makeText(this@DestinationListActivity,
                        "Your session has expired.Pleas Login again",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this@DestinationListActivity,
                        "Failed to retrieve items",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Destination>>, t: Throwable) {
                Toast.makeText(this@DestinationListActivity,
                    "Error Ocurred" + t.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }
}
