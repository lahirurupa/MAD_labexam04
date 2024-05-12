package uz.javokhirjambulov.notes.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import uz.javokhirjambulov.notes.MainActivity
import uz.javokhirjambulov.notes.R
import uz.javokhirjambulov.notes.commons.Constants
import uz.javokhirjambulov.notes.commons.Dialog
import uz.javokhirjambulov.notes.database.DeletedNoteDatabase
import uz.javokhirjambulov.notes.database.Note
import uz.javokhirjambulov.notes.database.NoteDatabase
import uz.javokhirjambulov.notes.databinding.ActivitySettingsBinding

import uz.javokhirjambulov.notes.ui.screens.NoteViewModel
import uz.javokhirjambulov.notes.ui.screens.NoteViewModelFactory


@Suppress("DEPRECATION")
class Settings : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var myRef: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var myDeletedNotesRef: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var deletedNoteViewModel: DeletedNotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Firebase.database
        myRef = database.getReference("Notes")
        myDeletedNotesRef = database.getReference("DeletedNotes")
        auth = Firebase.auth
        noteViewModel = ViewModelProvider(
            this,
            NoteViewModelFactory(NoteDatabase.getDataBase())
        )[NoteViewModel::class.java]
        deletedNoteViewModel = ViewModelProvider(this,DeletedNotesViewModelFactory(
            DeletedNoteDatabase.getDataBase()))[DeletedNotesViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings)
        binding.viewSourceCode.setOnClickListener {
            val openGithub = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.sourceCodeURL))
            startActivity(openGithub)
        }

        //initObjects()






        binding.appIntro.setOnClickListener {
            // show app intro
            val i = Intent(this, MainIntroActivity::class.java)
            startActivity(i)
        }




    }


    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }
}