package com.example.recyclerview

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private val itemList: MutableList<String> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)

        fun bind(item: String) {
            textView.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            // Вызываем метод редактирования текста при нажатии на элемент
            editItem(holder)
        }
        holder.itemView.setOnLongClickListener {
            removeItem(position)
            true
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: String) {
        itemList.add(item)
        notifyItemInserted(itemList.size - 1)
    }

    private fun removeItem(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addTextFromEditText(editText: EditText) {
        val text = editText.text.toString().trim()
        if (text.isNotEmpty()) {
            addItem(text)
            editText.text.clear()
        }
    }

    private fun editItem(holder: ViewHolder) {
        val context = holder.itemView.context
        val editText = EditText(context)
        editText.setText(holder.textView.text)
        AlertDialog.Builder(context)
            .setTitle("Редактирование")
            .setView(editText)
            .setPositiveButton("Сохранить") { _, _ ->
                val newText = editText.text.toString().trim()
                if (newText.isNotEmpty()) {
                    val position = holder.adapterPosition
                    itemList[position] = newText
                    notifyItemChanged(position)
                }
            }
            .setNegativeButton("Отмена", null)
            .show()
    }

}