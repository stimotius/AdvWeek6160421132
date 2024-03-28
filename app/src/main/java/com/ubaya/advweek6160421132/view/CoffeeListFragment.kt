package com.ubaya.advweek6160421132.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.advweek6160421132.R
import com.ubaya.advweek6160421132.databinding.FragmentCoffeeListBinding
import com.ubaya.advweek6160421132.viewmodel.ListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CoffeeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoffeeListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val studentListAdapter  = CoffeeListAdapter(arrayListOf())
    private lateinit var binding: FragmentCoffeeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCoffeeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = studentListAdapter

        observeViewModel()

        binding.refreshLayout.setOnRefreshListener {
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }
    }

    fun observeViewModel() {
        viewModel.coffeesLD.observe(viewLifecycleOwner, Observer {
            studentListAdapter.updateStudentList(it)
        })

        viewModel.coffeeLoadErrorLD.observe(viewLifecycleOwner, Observer {if(it == true) {
            binding.txtError?.visibility = View.VISIBLE
        } else {
            binding.txtError?.visibility = View.GONE
        }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {if(it == true) {
            binding.recView.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
        } else {
            binding.recView.visibility = View.VISIBLE
            binding.progressLoad.visibility = View.GONE
        }
        })

    }

}