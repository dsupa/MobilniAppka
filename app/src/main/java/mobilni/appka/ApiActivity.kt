package mobilni.appka

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_api.*

class ApiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)

        btn_search.setOnClickListener {
            displayJoke()
        }
    }

    private fun displayJoke() {
        Thread {
            val joke = ApiService.getJoke()
            runOnUiThread {
                txt_joke.text = joke.joke
            }
        }.start()
    }
}