package com.example.BuSUS

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ListaItinerarioActivity : AppCompatActivity() {
    private lateinit var oniRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var oniList: ArrayList<ItinerarioModelo>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        oniRecyclerView = findViewById(R.id.listVagas)
        oniRecyclerView.layoutManager = LinearLayoutManager(this)
        oniRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        oniList = arrayListOf<ItinerarioModelo>()

        getEmployeesData()

    }

    private fun getEmployeesData() {

        oniRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Onibus")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                oniList.clear()
                if (snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val empData = empSnap.getValue(ItinerarioModelo::class.java)
                        oniList.add(empData!!)
                    }
                    val mAdapter = OniAdapter(oniList)
                    oniRecyclerView.adapter = mAdapter

//                    mAdapter.setOnItemClickListener(object : OniAdapter.onItemClickListener{
//                        override fun onItemClick(position: Int) {
//
//                            val intent = Intent(this@FetchingActivity, EmployeeDetailsActivity::class.java)
//
//                            //put extras
//                            intent.putExtra("oniId", oniList[position].oniId)
//                            intent.putExtra("oniData", oniList[position].oniData)
//                            intent.putExtra("oniCidade", oniList[position].oniCidade)
//                            intent.putExtra("oniExame", oniList[position].oniExame)
//                            startActivity(intent)
//                        }
//
//                    })

                    oniRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
