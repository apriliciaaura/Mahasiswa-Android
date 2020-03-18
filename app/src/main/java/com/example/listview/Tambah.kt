package com.example.listview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_tambah.*
import org.json.JSONArray
import org.json.JSONObject

class Tambah : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)

        tambah.setOnClickListener {
            var textViewName : String = name.text.toString()
            var textViewNumber : String = number.text.toString()
            var textViewAddress : String = address.text.toString()

            postkeserver(textViewName, textViewNumber, textViewAddress)

            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
            finish()
        }

        kembali.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun postkeserver(data1:String, data2:String, data3:String){
        AndroidNetworking.post("http://192.168.0.6/datamahasiswa/tambah.php")
            .addBodyParameter("nama_mahasiswa", data1)
            .addBodyParameter("nomor_mahasiswa", data2)
            .addBodyParameter("alamat_mahasiswa", data3)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {
                    Log.i("Coba", "Sukses")
                    Toast.makeText(applicationContext, "Berhasil", Toast.LENGTH_LONG).show()
                }

                override fun onError(anError: ANError) { // handle error
                    Log.i("_err", anError.toString())
                }
            })
    }
}
