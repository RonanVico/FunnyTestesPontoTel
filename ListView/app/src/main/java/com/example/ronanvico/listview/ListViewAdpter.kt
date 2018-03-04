package com.example.ronanvico.listview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.row_main.view.*

/**
 * Created by Ronan Vico on 04/03/2018.
 */

class  MyCustomAdapter(val datas: MainActivity.HomeFeed): BaseAdapter(){

    override fun getCount(): Int {

        return datas.data.size

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()//To change body of created functions use File | Settings | File Templates.
    }

    override fun getItem(position: Int): Any {
        return "TEST STRING"//To change body of created functions use File | Settings | File Templates.
    }

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

        val rowMain: View

        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(viewGroup!!.context)

            rowMain = layoutInflater.inflate(R.layout.row_main, viewGroup , false)
            val viewHolder =  ViewHolder(rowMain.id_textview,rowMain.name_textview,rowMain.pwd_textview)
            rowMain.tag = viewHolder

        }else {
            rowMain = convertView
        }

        val viewHolder  = rowMain.tag as ViewHolder
        viewHolder.idTextView.text = "ID: " + datas.data.get(position).id
        viewHolder.nameTextView.text = "Name: " + datas.data.get(position).name
        viewHolder.pwdTextView.text = "Pwd: " +  datas.data.get(position).pwd

        return rowMain

    }
    private class ViewHolder(val idTextView: TextView, val nameTextView: TextView, val pwdTextView: TextView)
}