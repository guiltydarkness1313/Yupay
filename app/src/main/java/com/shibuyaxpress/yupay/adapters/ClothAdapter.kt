package com.shibuyaxpress.yupay.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shibuyaxpress.yupay.utils.GlideApp
import com.shibuyaxpress.yupay.holders.ClothHolder
import com.shibuyaxpress.yupay.models.Cloth
import com.shibuyaxpress.yupay.R
import com.shibuyaxpress.yupay.activities.DetailClothActivity

class ClothAdapter(context: Context) : RecyclerView.Adapter<ClothHolder>() {
    private var mInflater:LayoutInflater? = null
    private var mCloth:List<Cloth>? = null
    private var context:Context? = null

    init {
        mInflater = LayoutInflater.from(context)
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothHolder {
        var itemView: View =mInflater!!.inflate(R.layout.cloth_item,parent,false)
        return ClothHolder(itemView)
    }

    override fun onBindViewHolder(holder: ClothHolder, position: Int) {
        if(mCloth!=null){
            val current: Cloth =mCloth!!.get(position)
            holder.name!!.text=current.name
            GlideApp.with(mInflater!!.context)
                    .load(current.image)
                    .placeholder(R.drawable.ic_image_black_24dp)
                    .fitCenter()
                    .into(holder.image!!)

            holder.itemView.setOnClickListener {
                val intent = Intent(context,DetailClothActivity::class.java)
                intent.putExtra("name", current.name)
                intent.putExtra("price", current.price)
                intent.putExtra("createdAt",current.createdAt.toString())
                intent.putExtra("inventoryId", current.inventoryId)
                intent.putExtra("image", current.image)
                intent.putExtra("id", current.id)
                context!!.startActivity(intent)
            }
        }else{
            //holder.nameCloth!!.text="No tiene ropa disponible en su inventario!!"
        }
    }

    override fun getItemCount(): Int {
        return if (mCloth!=null){
            mCloth!!.size
        }else{
            0
        }
    }

    fun setNameOfCloth(cloth:List<Cloth>){
        mCloth=cloth
        notifyDataSetChanged()
    }

}