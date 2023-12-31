package com.study.studyretrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductAdapter(private val mList:List<Products>):RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_card,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = mList[position]
        holder.textView.text = product.title
        holder.textPrice.text = "Price: $${product.price}"
        holder.textCategory.text = "Category: ${product.category}"
        holder.textBrand.text = buildString {
            append("Brand: ")
            append(product.brand)
        }

        Picasso.get()
            .load(product.images.get(0))
            .into(holder.imageView)
        holder.count.text = product.stock.toString()

        holder.addBtn.setOnClickListener {
            product.stock = product.stock!! + 1
            holder.count.text = product.stock.toString()
        }
        holder.minusBtn.setOnClickListener {
            if (product.stock!! > 0) {
                product.stock = product.stock!! - 1
                holder.count.text = product.stock.toString()
            }
        }
    }

    class ViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = ItemView.findViewById(R.id.imageViewImage)
        val textView: TextView = ItemView.findViewById(R.id.textViewTitle)
        val textPrice: TextView = ItemView.findViewById(R.id.textViewPrice)
        val textCategory: TextView = ItemView.findViewById(R.id.textViewCategory)
        val textBrand: TextView = ItemView.findViewById(R.id.textViewBrand)

        val count:TextView = ItemView.findViewById(R.id.tv_stockCount)
        val addBtn:ImageButton = ItemView.findViewById(R.id.btn_add)
        val minusBtn:ImageButton = ItemView.findViewById(R.id.btn_minus)

    }

}