package com.example.notetakingapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notetakingapp.Model.Note
import com.example.notetakingapp.R
import com.example.notetakingapp.databinding.FragmentUpdateNoteBinding
import com.example.notetakingapp.viewmodel.NoteViewModel

class UpdateNoteFragment : Fragment() {

    private var _binding: FragmentUpdateNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var noteViewModel: NoteViewModel
    private var noteId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
        noteViewModel = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)

        // ✅ Get note data passed from ViewNotesFragment
        arguments?.let {
            noteId = it.getInt("id")
            binding.editTitle.setText(it.getString("title"))
            binding.editDescription.setText(it.getString("description"))
        }

        // ✅ When user presses update button
        binding.updateBtn.setOnClickListener {
            val title = binding.editTitle.text.toString().trim()
            val description = binding.editDescription.text.toString().trim()

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                val updatedNote = Note(id = noteId, title = title, description = description)
                noteViewModel.updateNote(updatedNote)
                Toast.makeText(requireContext(), "Note updated", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateNote_to_viewNotes)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
