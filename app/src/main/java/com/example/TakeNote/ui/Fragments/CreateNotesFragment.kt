package com.example.TakeNote.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.text.method.ScrollingMovementMethod
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.TakeNote.Model.Notes
import com.example.TakeNote.databinding.FragmentCreateNotesBinding
import com.example.TakeNote.viewModels.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CreateNotesFragment : Fragment() {

    lateinit var binding: FragmentCreateNotesBinding
    val viewModel: NotesViewModel by activityViewModels()
    var priority: String = "1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvWork.setCompoundDrawablesWithIntrinsicBounds(
            com.example.TakeNote.R.drawable.ic_yes,
            0,
            0,
            0
        );
        binding.edtNotes.setMovementMethod(ScrollingMovementMethod())

        onCreateNoteButtonClick()
        onBlueClick()
        onGreenClick()
        onRedClick()
        onYellowClick()


    }

    private fun onRedClick() {
        binding.tvFamily.setOnClickListener {
            priority = "3"
            binding.tvFamily.setCompoundDrawablesWithIntrinsicBounds(
                com.example.TakeNote.R.drawable.ic_yes,
                0,
                0,
                0
            );
            binding.tvWork.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            binding.tvLife.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            binding.tvEntertainment.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        }
    }

    private fun onBlueClick() {
        binding.tvWork.setOnClickListener {
            priority = "1"
            binding.tvWork.setCompoundDrawablesWithIntrinsicBounds(
                com.example.TakeNote.R.drawable.ic_yes,
                0,
                0,
                0
            );
            binding.tvFamily.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            binding.tvLife.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            binding.tvEntertainment.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        }
    }

    private fun onYellowClick() {
        binding.tvEntertainment.setOnClickListener {
            priority = "4"
            binding.tvEntertainment.setCompoundDrawablesWithIntrinsicBounds(
                com.example.TakeNote.R.drawable.ic_yes,
                0,
                0,
                0
            );
            binding.tvFamily.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            binding.tvLife.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            binding.tvWork.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);


        }
    }

    private fun onGreenClick() {
        binding.tvLife.setOnClickListener {
            priority = "2"
            binding.tvLife.setCompoundDrawablesWithIntrinsicBounds(
                com.example.TakeNote.R.drawable.ic_yes,
                0,
                0,
                0
            );
            binding.tvFamily.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            binding.tvWork.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            binding.tvEntertainment.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        }
    }

    private fun onCreateNoteButtonClick() {
        binding.btnCreateNote.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val note = binding.edtNotes.text.toString()
            val d = Date()
            val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)
            if (title.isNotEmpty() || note.isNotEmpty()) {
                val notes = Notes(
                    title = title,
                    notes = note,
                    priority = priority,
                    date = notesDate.toString()
                )
                viewModel.addNote(notes)
            }
            Navigation.findNavController(it)
                .navigate(com.example.TakeNote.R.id.action_createNotesFragment_to_homeFragment)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item: MenuItem = menu.findItem(com.example.TakeNote.R.id.search_note)
        item.setVisible(false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {

            requireActivity().onBackPressed()
            return false
        }

        return super.onOptionsItemSelected(item)
    }

}