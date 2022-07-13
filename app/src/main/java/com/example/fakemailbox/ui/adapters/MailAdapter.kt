package com.example.fakemailbox.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.fakemailbox.R
import com.example.fakemailbox.data.model.Mail
import com.example.fakemailbox.shared.MailViewModel

class MailAdapter(dataSet : MutableList<Mail>?) : RecyclerView.Adapter<MailAdapter.MailViewHolder>() {

    var mailList = dataSet

    class MailViewHolder(_view : View) : RecyclerView.ViewHolder(_view) {
        val view = _view
        val mailId : TextView = view.findViewById(R.id.mailId)
        val mailDate : TextView = view.findViewById(R.id.date)
        val priorityIndicator : ImageView = view.findViewById(R.id.priorityIndicator)
        val mailHeader : TextView = view.findViewById(R.id.mailHeader)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailAdapter.MailViewHolder {
        return MailViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.mail_inflatable, parent, false)

        )
    }

    override fun onBindViewHolder(holder: MailAdapter.MailViewHolder, position: Int) {
        holder.mailId.text = mailList?.get(position)?.id.toString()
        holder.mailHeader.text  = mailList?.get(position)?.fromDate.toString()
        holder.mailDate.text = mailList?.get(position)?.fromDate.toString()

        if(mailList?.get(position)?.readIndicator == false) {
            holder.mailId.setTextColor(Color.parseColor("#FF000000"))
            holder.mailHeader.setTextColor(Color.parseColor("#FF000000"))
        }

        if(mailList?.get(position)?.priorityIndicator == true) {
            holder.priorityIndicator.setImageResource(R.drawable.ic_baseline_star_24)
        } else {
            holder.priorityIndicator.setImageResource(R.drawable.ic_baseline_star_outline_24)
        }
    }

    override fun getItemCount(): Int {
        return mailList!!.size
    }

    fun update() {
        notifyDataSetChanged()
    }
}