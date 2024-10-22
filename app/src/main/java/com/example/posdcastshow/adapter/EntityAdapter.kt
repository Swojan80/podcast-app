package com.example.posdcastshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EntityAdapter(private val entities: List<Entity>) : RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val cultureTextView: TextView = itemView.findViewById(R.id.cultureTextView)
        val domainTextView: TextView = itemView.findViewById(R.id.domainTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = entities[position]
        holder.nameTextView.text = entity.name
        holder.cultureTextView.text = "Culture: ${entity.culture}"
        holder.domainTextView.text = "Domain: ${entity.domain}"

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java)

            // Pass data to DetailActivity
            intent.putExtra("EXTRA_NAME", entity.name)
            intent.putExtra("EXTRA_CULTURE", entity.culture)
            intent.putExtra("EXTRA_DOMAIN", entity.domain)
            intent.putExtra("EXTRA_DESCRIPTION", entity.description)
            intent.putExtra("EXTRA_SYMBOL", entity.symbol)
            intent.putExtra("EXTRA_PARENTAGE", entity.parentage)
            intent.putExtra("EXTRA_ROMAN_EQUIVALENT", entity.romanEquivalent)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return entities.size
    }
}
