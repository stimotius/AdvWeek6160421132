package com.ubaya.advweek6160421132.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.ubaya.advweek6160421132.R
import com.ubaya.advweek6160421132.databinding.FragmentCoffeeDetailBinding
import com.ubaya.advweek6160421132.viewmodel.DetailViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CoffeeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoffeeDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentCoffeeDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoffeeDetailBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_coffee_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()

        viewModel.coffeeLD.observe(viewLifecycleOwner, Observer {
            with(binding) {
                txtNama.setText(it.nama)
                txtHarga.setText(it.harga)
                txtDeskripsi.setText(it.deskripsi)
                txtBahan.setText(it.bahan.toString())
                txtLangkah.setText(it.langkah.toString())

                Picasso.get().load(it.urlGambar).into(imageView2)
            }
        })
    }
}