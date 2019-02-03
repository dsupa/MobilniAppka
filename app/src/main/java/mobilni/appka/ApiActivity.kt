package mobilni.appka

import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_api.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

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

    override fun onResume() {
        super.onResume()
        val root = Environment.getExternalStorageDirectory().toString()
        val OutletFname = "Avatar.jpg"
        val myDir = File(root+"/moje_appka_avatar")
        val file = File(myDir, OutletFname)
        if(file.exists()){
            val bitmap = BitmapFactory.decodeFile(file.path)
            avatarViewApi.setImageBitmap(bitmap)
        }

        // Set Border
        avatarViewApi.setBorderColor(Color.LTGRAY)
        avatarViewApi.setBorderWidth(3F)
        // Add Shadow with default param
        avatarViewApi.addShadow()
    }
}