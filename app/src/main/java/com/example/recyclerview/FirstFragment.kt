package com.example.recyclerview

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.recyclerview.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var adapter: CustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initial()
        setupListener()
    }

    private fun initial() {
        adapter = CustomAdapter()
        binding.recyclerView.adapter = adapter
    }

    private fun setupListener() = with(binding) {
        button.setOnClickListener {
            val editText = EditText(context)
            AlertDialog.Builder(context)
                .setTitle("Добавление")
                .setView(editText)
                .setPositiveButton("Сохранить") { _, _ ->
                    val newText = editText.text.toString().trim()
                    if (newText.isNotEmpty()) {
                        adapter.addTextFromEditText(editText)
                    }
                }
                .setNegativeButton("Отмена", null)
                .show()
        }
    }
}