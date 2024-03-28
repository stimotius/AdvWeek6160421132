package com.ubaya.advweek6160421132.model;

import com.google.gson.annotations.SerializedName

data class Coffee(
        @SerializedName("nama_kopi")
        val nama:String?,
        val harga:String?,
        val deskripsi:String?,
        @SerializedName("url_gambar")
        val urlGambar:String?,
        val bahan: List<String>?,
        val langkah: List<String>?
)
