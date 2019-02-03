package mobilni.appka

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import kotlinx.android.synthetic.main.activity_camera.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        profile_button.setOnClickListener{
            val profileIntent = Intent(this, CameraActivity::class.java)
            startActivity(profileIntent)
        }

        button_api.setOnClickListener{
            val apiIntent = Intent(this, ApiActivity::class.java)
            startActivity(apiIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        val root = Environment.getExternalStorageDirectory().toString()
        val OutletFname = "Avatar.jpg"
        val myDir = File(root+"/moje_appka_avatar")
        val file = File(myDir, OutletFname)
        if(file.exists()){
            val bitmap = BitmapFactory.decodeFile(file.path)
            avatarView.setImageBitmap(bitmap)
        }

        // Set Border
        avatarView.setBorderColor(Color.LTGRAY)
        avatarView.setBorderWidth(3F)
        // Add Shadow with default param
        avatarView.addShadow()
    }
}
