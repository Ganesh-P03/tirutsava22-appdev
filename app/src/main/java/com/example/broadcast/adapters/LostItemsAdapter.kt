package com.example.broadcast.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.broadcast.R
import com.example.broadcast.models.Event
import com.example.broadcast.models.Lost
import kotlinx.android.synthetic.main.item_event.view.*
import kotlinx.android.synthetic.main.item_lost.view.*

open class LostItemsAdapter (private val context: Context,
                             private val list:ArrayList<Lost>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var onClickListener: LostItemsAdapter.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_lost,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val model = list[position]


        if (holder is MyViewHolder) {

            Glide
                .with(context)
                .load(model.lost_image)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.itemView.iv_lost_img)

            holder.itemView.tv_lost_name.text = model.lost_name
            holder.itemView.tv_lost_desc.text = model.lost_desc
            holder.itemView.tv_lost_place.text = model.lost_place
            holder.itemView.tv_lost_time.text = model.lost_time
            holder.itemView.tv_lost_contact_num.text = model.contact_num.toString()

            holder.itemView.setOnClickListener {

                if (onClickListener != null) {
                    onClickListener!!.onClick(position, model)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener

    }

    interface OnClickListener {
        fun onClick(position: Int, model: Lost)
    }

    private class MyViewHolder(view: View):RecyclerView.ViewHolder(view)
}