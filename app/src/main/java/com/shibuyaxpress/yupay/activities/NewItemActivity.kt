package com.shibuyaxpress.yupay.activities

import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.shibuyaxpress.yupay.utils.GlideApp
import com.shibuyaxpress.yupay.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class NewItemActivity : AppCompatActivity() {
    private var btn: Button? = null
    private var imageview:ImageView?=null
    private var image:ImageView? = null
    private val GALLERY = 1
    private val CAMERA = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_item)
        btn = findViewById(R.id.btn)
        imageview = findViewById(R.id.iv)
        btn!!.setOnClickListener{
            showPictureDialog()
        }
        //method to display and image from storage, only if its saved the path in the database
        image=findViewById(R.id.imageRoute)
        //val bitmap=BitmapFactory.decodeFile("/storage/emulated/0/yupay/1531649517050.jpg")
        //image!!.setImageBitmap(bitmap)
        GlideApp.with(this@NewItemActivity)
                .load("/storage/emulated/0/yupay/1531649517050.jpg")
                .placeholder(R.drawable.ic_image_black_24dp)
                .fitCenter()
                .into(image!!)
        //.----------------------------------

    }
    private fun showPictureDialog(){
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(pictureDialogItems) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallery()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }
    private fun choosePhotoFromGallery(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, GALLERY)
    }
    private fun takePhotoFromCamera(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            GALLERY->{
                if (data!=null){
                    val contentURI = data.data
                    try {
                        val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,contentURI)
                        val path = saveImage(bitmap)
                        Log.d("TAG",path::class.simpleName)
                        Toast.makeText(this@NewItemActivity,"Image Saved!-->route::"+path,Toast.LENGTH_SHORT).show()
                        imageview!!.setImageBitmap(bitmap)
                    }catch (e:IOException){
                        e.printStackTrace()
                        Toast.makeText(this@NewItemActivity,"Failed!",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            CAMERA->{
                try {
                    val thumbnail = data!!.extras!!.get("data") as Bitmap
                    imageview!!.setImageBitmap(thumbnail)
                    saveImage(thumbnail)
                    Toast.makeText(this@NewItemActivity,"Image Saved!",Toast.LENGTH_SHORT).show()
                }catch (e:NullPointerException){
                    e.printStackTrace()
                    Toast.makeText(this@NewItemActivity,"Retrieved image of camera failed!",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun saveImage(myBitmap:Bitmap):String{
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG,90,bytes)
        val wallpaperDirectory = File(
                (Environment.getExternalStorageDirectory()).toString()+ IMAGE_DIRECTORY)
        Log.d("fee",wallpaperDirectory.toString())
        if (!wallpaperDirectory.exists()){
            wallpaperDirectory.mkdirs()
        }
        try {
            Log.d("heel",wallpaperDirectory.toString())
            val f = File(wallpaperDirectory,((Calendar.getInstance().timeInMillis).toString()+".jpg"))
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(this, arrayOf(f.path), arrayOf("image/jpg"),null)
            fo.close()
            Log.d("TAG","File Saved::---->"+f.absolutePath)
        }catch (e1:IOException){
            e1.printStackTrace()
        }
        return ""
    }
    companion object {
        private const val IMAGE_DIRECTORY = "/yupay"
    }

}
