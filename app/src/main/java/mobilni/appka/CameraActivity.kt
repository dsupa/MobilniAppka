package mobilni.appka

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import java.io.File
import java.io.FileOutputStream
import java.util.*
import android.content.Intent
import kotlinx.android.synthetic.main.activity_camera.*
import java.lang.Exception

class CameraActivity : AppCompatActivity() {

    val REQUEST_PERM_WRITE_STORAGE = 102
    private val CAPTURE_PHOTO = 104
    private var imagePath: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        btn_call_camera.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(this@CameraActivity, arrayOf(Manifest.permission.CAMERA), 1)
                }
                if (ActivityCompat.checkSelfPermission(applicationContext,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(this@CameraActivity,
                        arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_PERM_WRITE_STORAGE)
                }

                takePhotoByCamera()
            }
            else
            {
                takePhotoByCamera()
            }
        }
    }

    private fun takePhotoByCamera(){
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAPTURE_PHOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAPTURE_PHOTO && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            saveImage(imageBitmap)
            imageViewCamera.setImageBitmap(imageBitmap)
        }
    }

    private fun saveImage(finalBitmap: Bitmap) {
        val root = Environment.getExternalStorageDirectory().toString()
        val myDir = File(root+"/moje_appka_avatar")
        myDir.mkdir()
        val generator = Random()
        var n = 10000
        n = generator.nextInt(n)
        val OutletFname = "Avatar.jpg"
        val file = File(myDir, OutletFname)
        if(file.exists()){
            file.delete()
        }
        try {
            val out = FileOutputStream(file)
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            imagePath = file.absolutePath
            out.flush()
            out.close()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

}
