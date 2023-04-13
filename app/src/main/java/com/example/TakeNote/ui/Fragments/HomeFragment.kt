package com.example.TakeNote.ui.Fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.TakeNote.Adapters.NotesAdapter
import com.example.TakeNote.Model.Notes
import com.example.TakeNote.R
import com.example.TakeNote.databinding.FragmentHomeBinding
import com.example.TakeNote.viewModels.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        const val WORK_PRIORITY = "1"
        const val LIFE_PRIORITY = "2"
        const val FAMILY_PRIORITY = "3"
        const val ENTERTAINMENT_PRIORITY = "4"

    }

    lateinit var binding: FragmentHomeBinding
    private val viewModel: NotesViewModel by activityViewModels()
    lateinit var adapter: NotesAdapter
    var myNotes: MutableList<Notes> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)



        adapter = NotesAdapter()

        onMenuItemClick()
        setHasOptionsMenu(true);

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        onEditNoteClick()
        onAddNotesButtonClick()
        getNotes()
        onWorkFilterClick()
        onLifeFilterClick()
        onFamilyFilterClick()
        onEntertainmentFilterClick()
        onAllNotesClick()


    }

    private fun prepareRecyclerView() {

        binding.rvAllNotes.adapter = adapter
        binding.rvAllNotes.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


    }

    private fun onEditNoteClick() {
        adapter.onNoteClick = { notes ->
            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(notes.id)

            val navHostFragment =
                requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView)

            navHostFragment?.view?.let { Navigation.findNavController(it).navigate(action) }


        }

    }


    private fun onAllNotesClick() {
        binding.imgAllNotes.setOnClickListener {
            resetFilters()
            getNotes()
        }
    }

    private fun resetFilters() {
        binding.filterEntertainment.setBackgroundResource(R.drawable.grey_dot)
        binding.filterWork.setBackgroundResource(R.drawable.grey_dot)
        binding.filterLife.setBackgroundResource(R.drawable.grey_dot)
        binding.filterFamily.setBackgroundResource(R.drawable.grey_dot)


    }

    private fun onEntertainmentFilterClick() {
        binding.filterEntertainment.setOnClickListener {

            lifecycleScope.launchWhenCreated {
                viewModel.getFilteredNotes(HomeFragment.ENTERTAINMENT_PRIORITY)
                    .collectLatest { notesList ->
                        adapter.setMyNotesList(notesList)
                        binding.filterEntertainment.setBackgroundResource(R.drawable.white_dot)
                        binding.filterWork.setBackgroundResource(R.drawable.grey_dot)
                        binding.filterLife.setBackgroundResource(R.drawable.grey_dot)
                        binding.filterFamily.setBackgroundResource(R.drawable.grey_dot)
                    }
            }

        }

    }

    private fun onFamilyFilterClick() {
        binding.filterFamily.setOnClickListener {

            lifecycleScope.launchWhenCreated {
                viewModel.getFilteredNotes(HomeFragment.FAMILY_PRIORITY)
                    .collectLatest { notesList ->
                        adapter.setMyNotesList(notesList)
                        binding.filterFamily.setBackgroundResource(R.drawable.white_dot)
                        binding.filterEntertainment.setBackgroundResource(R.drawable.grey_dot)
                        binding.filterLife.setBackgroundResource(R.drawable.grey_dot)
                        binding.filterWork.setBackgroundResource(R.drawable.grey_dot)
                    }
            }


        }
    }

    private fun onLifeFilterClick() {
        binding.filterLife.setOnClickListener {

            lifecycleScope.launchWhenCreated {
                viewModel.getFilteredNotes(HomeFragment.LIFE_PRIORITY).collectLatest { notesList ->
                    adapter.setMyNotesList(notesList)
                    binding.filterLife.setBackgroundResource(R.drawable.white_dot)
                    binding.filterWork.setBackgroundResource(R.drawable.grey_dot)
                    binding.filterFamily.setBackgroundResource(R.drawable.grey_dot)
                    binding.filterEntertainment.setBackgroundResource(R.drawable.grey_dot)
                }
            }


        }
    }

    private fun onWorkFilterClick() {
        binding.filterWork.setOnClickListener {

            lifecycleScope.launchWhenCreated {
                viewModel.getFilteredNotes(HomeFragment.WORK_PRIORITY).collectLatest { notesList ->
                    adapter.setMyNotesList(notesList)
                    binding.filterWork.setBackgroundResource(R.drawable.white_dot)
                    binding.filterEntertainment.setBackgroundResource(R.drawable.grey_dot)
                    binding.filterLife.setBackgroundResource(R.drawable.grey_dot)
                    binding.filterFamily.setBackgroundResource(R.drawable.grey_dot)
                }
            }


        }
    }

    private fun getNotes() {

        lifecycleScope.launchWhenCreated {
            viewModel.getAllNotes().collect {
                adapter.setMyNotesList(it)
                if (!it.isEmpty()) {
                    myNotes = it as MutableList<Notes>

                }
            }


        }

    }


    private fun onAddNotesButtonClick() {
        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)
        }
    }


    private fun onMenuItemClick() {
        val menuHost: MenuHost = requireActivity()
        resetFilters()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.clear()

                menuInflater.inflate(R.menu.search_menu, menu)
                val item = menu.findItem(R.id.search_note)
                val searchView = item?.actionView as SearchView?

                searchView?.queryHint = "Type note here ..."
                searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        findNotes(newText)
                        return true
                    }

                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }


        })
    }

    private fun findNotes(newText: String?) {
        newText?.let {
            newText.toLowerCase()
            val filteredList = mutableListOf<Notes>()

            for (note in myNotes) {
                if (note.title.toLowerCase().contains(newText) || note.notes.toLowerCase()
                        .contains(newText)
                ) {
                    filteredList.add(note)
                }
            }
            Log.i("@@@", filteredList.joinToString())

            adapter.setMyNotesList(filteredList)
        }


    }


}