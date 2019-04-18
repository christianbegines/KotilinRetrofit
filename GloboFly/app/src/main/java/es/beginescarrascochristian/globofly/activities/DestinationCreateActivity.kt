package es.beginescarrascochristian.globofly.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import es.beginescarrascochristian.globofly.R
import es.beginescarrascochristian.globofly.helpers.SampleData
import es.beginescarrascochristian.globofly.models.Destination
import kotlinx.android.synthetic.main.activity_destiny_create.*

class DestinationCreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destiny_create)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        btn_add.setOnClickListener {
            val newDestination = Destination()
            newDestination.city = et_city.text.toString()
            newDestination.description = et_description.text.toString()
            newDestination.country = et_country.text.toString()

            SampleData.addDestination(newDestination)
            finish()
        }
    }
}
