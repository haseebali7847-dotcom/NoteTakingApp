package com.example.notetakingapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.notetakingapp.Model.Note
import com.example.notetakingapp.databinding.FragmentAddNoteBinding
import com.example.notetakingapp.viewmodel.NoteViewModel

class AddNoteFragment : Fragment() {


    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!


    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)


        noteViewModel = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)


        binding.saveBtn.setOnClickListener {
            saveNote()
        }

        return binding.root
    }


    private fun saveNote() {

        val title = binding.editTextText.text.toString().trim()
        val description = binding.editTextText2.text.toString().trim()


        if (title.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter a title", Toast.LENGTH_SHORT).show()
            return
        }

        if (description.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter a description", Toast.LENGTH_SHORT).show()
            return
        }


        val note = Note(title = title, description = description)


        noteViewModel.addNote(note)


        Toast.makeText(requireContext(), "Note saved", Toast.LENGTH_SHORT).show()


        binding.editTextText.text?.clear()
        binding.editTextText2.text?.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}