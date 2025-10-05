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

    // ✅ ViewBinding reference
    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    // ViewModel reference
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate layout using ViewBinding
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)

        // Initialize ViewModel
        noteViewModel = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)

        // Set click listener on Save button
        binding.saveBtn.setOnClickListener {
            saveNote()
        }

        return binding.root
    }

    // ✅ Function to capture, validate, and save note
    private fun saveNote() {
        // Get text from EditTexts
        val title = binding.editTextText.text.toString().trim()
        val description = binding.editTextText2.text.toString().trim()

        // Validate input
        if (title.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter a title", Toast.LENGTH_SHORT).show()
            return
        }

        if (description.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter a description", Toast.LENGTH_SHORT).show()
            return
        }

        // Create Note object
        val note = Note(title = title, description = description)

        // Save note using ViewModel
        noteViewModel.addNote(note)

        // Optional: Show success message
        Toast.makeText(requireContext(), "Note saved", Toast.LENGTH_SHORT).show()

        // Optional: Clear fields after saving
        binding.editTextText.text?.clear()
        binding.editTextText2.text?.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}