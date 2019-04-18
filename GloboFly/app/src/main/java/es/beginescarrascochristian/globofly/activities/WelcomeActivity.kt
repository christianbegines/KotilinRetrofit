package es.beginescarrascochristian.globofly.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import es.beginescarrascochristian.globofly.R
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        message.text = "Sekiro es muy facil , aprende a jugar manco"
    }

    fun getStarted(view:View){
        val intent = Intent(this,DestinationListActivity::class.java)
        startActivity(intent)
        finish()
    }
}
