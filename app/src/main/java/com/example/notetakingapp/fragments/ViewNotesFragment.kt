package com.example.notetakingapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notetakingapp.NoteDetailActivity
import com.example.notetakingapp.R
import com.example.notetakingapp.adapter.NoteAdapter
import com.example.notetakingapp.databinding.FragmentViewNotesBinding
import com.example.notetakingapp.viewmodel.NoteViewModel

class ViewNotesFragment : Fragment() {

    private var _binding: FragmentViewNotesBinding? = null
    private val binding get() = _binding!!

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewNotesBinding.inflate(inflater, container, false)

        // ✅ Initialize RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ✅ Initialize ViewModel
        noteViewModel = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)

        // ✅ Initialize Adapter with view binding + popup menu actions
        adapter = NoteAdapter(
            mutableListOf(),
            onNoteClick = { selectedNote ->
                // Open note details
                val intent = Intent(requireContext(), NoteDetailActivity::class.java).apply {
                    putExtra("note_title", selectedNote.title)
                    putExtra("note_description", selectedNote.description)
                }
                startActivity(intent)
            },
            onDeleteClick = { note ->
                noteViewModel.deleteNote(note)
                Toast.makeText(requireContext(), "Note deleted", Toast.LENGTH_SHORT).show()
            },
            onUpdateClick = { note ->
                val bundle = Bundle().apply {
                    putInt("id", note.id)
                    putString("title", note.title)
                    putString("description", note.description)
                }
                findNavController().navigate(R.id.action_viewNotes_to_updateNote, bundle)
            }
        )

        // ✅ Attach adapter
        binding.recyclerView.adapter = adapter

        // ✅ Observe LiveData
        noteViewModel.allNotes.observe(viewLifecycleOwner) { notes ->
            adapter.updateList(notes)
        }

        // ✅ Add note button click
        binding.imageView3.setOnClickListener {
            findNavController().navigate(R.id.addNoteFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
