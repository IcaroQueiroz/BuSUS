package com.example.BuSUS

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.BuSUS.databinding.ActivityCadastroItinerarioBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CadastroItinerarioActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCadastroItinerarioBinding
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroItinerarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var edData = binding.edData
        var edCidade = binding.edCidade
        var edExame = binding.edExame
        var btCadatrar = binding.button

        dbRef = FirebaseDatabase.getInstance().getReference("Onibus")

        btCadatrar.setOnClickListener{
            val oniData = edData.text.toString()
            val oniCidade = edCidade.text.toString()
            val oniExame = edExame.text.toString()

            if(oniData.isEmpty()){
                edData.error = "Por favor insira uma data."
            }
            if(oniCidade.isEmpty()){
                edCidade.error = "Por favor insira uma cidade"
            }
            if(oniExame.isEmpty()){
                edExame.error = "Por favor insira um exame"
            }

            val oniId = dbRef.push().key!!

            val itinerario = ItinerarioModelo(oniId, oniData, oniCidade, oniExame)

            dbRef.child(oniId).setValue(itinerario)
                .addOnCompleteListener{
                    Toast.makeText(this, "Cadastro realizado com sucesso.", Toast.LENGTH_SHORT).show()

                    edData.text.clear()
                    edCidade.text.clear()
                    edExame.text.clear()

                }.addOnFailureListener{err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
                }
        }

    }


}