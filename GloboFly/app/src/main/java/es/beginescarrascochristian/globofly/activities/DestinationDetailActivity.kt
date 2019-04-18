package es.beginescarrascochristian.globofly.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import es.beginescarrascochristian.globofly.R
import es.beginescarrascochristian.globofly.helpers.SampleData
import es.beginescarrascochristian.globofly.models.Destination
import kotlinx.android.synthetic.main.activity_destiny_detail.*

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

        val destination = SampleData.getDestinationById(id)

        destination?.let {
            et_city.setText(destination.city)
            et_country.setText(destination.country)
            et_description.setText(destination.description)
            collapsing_toolbar.title = destination.city
        }
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
