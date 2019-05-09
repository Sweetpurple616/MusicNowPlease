package nl.zoedewaard.musicnowplease

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.event_row.view.*

class EventsAdapter(private val events: MutableList<Event>) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_row, parent, false)
        return  ViewHolder(view)
    }

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = events[position]
        holder.name.text = event.name
        holder.date.text = event.date
    }
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val name:TextView = itemView.name
        val date:TextView = itemView.date

    }

}
