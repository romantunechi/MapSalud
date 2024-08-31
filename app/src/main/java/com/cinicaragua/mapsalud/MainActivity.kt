package com.cinicaragua.mapsalud

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cinicaragua.mapsalud.Adapters.CentrosAdapter
import com.cinicaragua.mapsalud.AppDatabases.AppDatabase
import com.cinicaragua.mapsalud.Fragments.InicioFragment
import com.cinicaragua.mapsalud.Fragments.centroFragment
import com.cinicaragua.mapsalud.Repositories.MainRepository
import com.cinicaragua.mapsalud.ViewModels.MainViewModel
import com.cinicaragua.mapsalud.ViewModels.ViewModelFactory
import com.cinicaragua.mapsalud.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val adapter = CentrosAdapter(ArrayList())

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        val viewModel : MainViewModel by viewModels{
            ViewModelFactory(MainRepository(AppDatabase.getDatabase(this)))
        }

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(binding.fragmentContainer.id, InicioFragment())
            }
        }

        binding.botonAbrirFragment.setOnClickListener {
        centroFragment().show(supportFragmentManager, "centroFragment")
        }

        viewModel.selectAllCentros().observe(this, Observer {
            listaCentros -> adapter.updateCentros(ArrayList(listaCentros))
        })

    }
}