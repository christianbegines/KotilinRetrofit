package es.beginescarrascochristian.globofly.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import es.beginescarrascochristian.globofly.R
import es.beginescarrascochristian.globofly.helpers.SampleData
import es.beginescarrascochristian.globofly.models.Destination
import es.beginescarrascochristian.globofly.services.DestinationService
import es.beginescarrascochristian.globofly.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_destiny_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destiny_detail)

        setSupportActionBar(detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val bundle: Bundle? = intent.extras

        if(bundle?.containsKey(ARG_ITEM_ID) !!){
            val id  = intent.getIntExtra(ARG_ITEM_ID,0)


            loadDetails(id)
            initUpdateButton(id)
            intDeleteButton(id)
        }

    }

    private fun loadDetails(id:Int){

        val destinationService: DestinationService = ServiceBuilder.buildService(DestinationService::class.java)
        val requestCall : Call<Destination> = destinationService.getDestination(id)

        requestCall.enqueue(object : Callback<Destination>{
            override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
                if(response.isSuccessful){
                    val destination: Destination? = response.body()
                    destination?.let {
                        et_city.setText(destination.city)
                        et_country.setText(destination.country)
                        et_description.setText(destination.description)
                        collapsing_toolbar.title = destination.city
                    }
                } else{
                Toast.makeText(this@DestinationDetailActivity,
                    "Failed to retrieve items", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Destination>, t: Throwable) {
                Toast.makeText(this@DestinationDetailActivity,
                    "Error Ocurred" + t.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initUpdateButton(id: Int){
        btn_update.setOnClickListener {
            val city = et_city.text.toString()
            val description = et_description.text.toString()
            val country = et_country.text.toString()

            val destination = Destination()
            destination.id = id
            destination.country = country
            destination.city = city
            destination.description = description

            SampleData.updateDestination(destination)
            finish()
        }
    }

    private fun intDeleteButton(id: Int){
        btn_delete.setOnClickListener {
            SampleData.deleteDestination(id)
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if(id == android.R.id.home){
            navigateUpTo(Intent(this,DestinationListActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val ARG_ITEM_ID = "item_id"
    }
}
