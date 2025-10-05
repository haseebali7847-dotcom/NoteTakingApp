package com.example.notetakingapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notetakingapp.NoteDetailActivity
import com.example.notetakingapp.adapter.NoteAdapter
import com.example.notetakingapp.databinding.FragmentViewNotesBinding
import com.example.notetakingapp.viewmodel.NoteViewModel
import androidx.navigation.fragment.findNavController
import com.example.notetakingapp.R

class ViewNotesFragment : Fragment() {

    private var _binding: FragmentViewNotesBinding? = null
    private val binding get() = _binding!!

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewNotesBinding.inflate(inflater, container, false)

        // Setup RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize adapter once with empty list
        adapter = NoteAdapter(mutableListOf()) { selectedNote ->
            // navigate to NoteDetailActivity (pass data)
            val intent = Intent(requireContext(), NoteDetailActivity::class.java).apply {
                putExtra("note_title", selectedNote.title)
                putExtra("note_description", selectedNote.description)
            }
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter

        // Initialize ViewModel
        noteViewModel = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)

        // Observe LiveData and update adapter whenever data changes
        noteViewModel.allNotes.observe(viewLifecycleOwner) { notes ->
            // update adapter list
            adapter.updateList(notes)
        }

        // When the plus ImageView is clicked, navigate to AddNoteFragment
        binding.imageView3.setOnClickListener {
            // Use Navigation component — this assumes you have an action or fragment id for add fragment
            // If your navGraph id is "addNoteFragment", this will work:
            findNavController().navigate(R.id.addNoteFragment)
            // If you prefer to use a specific action: findNavController().navigate(R.id.action_viewNotesFragment_to_addNoteFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
