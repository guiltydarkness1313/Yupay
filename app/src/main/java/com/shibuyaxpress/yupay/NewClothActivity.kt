package com.shibuyaxpress.yupay

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText

class NewClothActivity : AppCompatActivity() {

        val EXTRA_REPLY:String="com.shibuyaxpress.yupay.REPLY"
    private var mEditClothName:EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_cloth)
        mEditClothName=findViewById(R.id.edit_cloth)
        var button: Button=findViewById(R.id.button_save)
        button.setOnClickListener {
            var replyIntent:Intent=Intent()
            if(TextUtils.isEmpty(mEditClothName!!.text)){
                setResult(Activity.RESULT_CANCELED,replyIntent)
            }else{
                var cloth:String=mEditClothName!!.text.toString()
                Log.d("dato ropa",cloth)
                replyIntent.putExtra(EXTRA_REPLY,cloth)
                setResult(Activity.RESULT_OK,replyIntent)
            }
            finish()
        }
    }
}
