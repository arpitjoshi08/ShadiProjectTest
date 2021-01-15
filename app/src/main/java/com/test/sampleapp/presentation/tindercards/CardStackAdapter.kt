package com.yuyakaido.android.cardstackview.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apilib.response.model.cardlist.CardList
import com.bumptech.glide.Glide
import com.test.sampleapp.R

class CardStackAdapter(
        private var cardList: List<CardList> = emptyList()
) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardData = cardList[position]
        holder.name.text = "${cardData.name.first}${" "} ${cardData.name.last}"
        Glide.with(holder.image)
                .load(cardData.picture.medium)
                .into(holder.image)

    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun setCardList(cards: List<CardList>) {
        this.cardList = cards
    }

    fun getCardList(): List<CardList> {
        return cardList
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.item_name)
        var city: TextView = view.findViewById(R.id.item_city)
        var image: ImageView = view.findViewById(R.id.item_image)
    }

}
