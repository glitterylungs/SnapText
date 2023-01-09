package com.example.snaptext.ui

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.snaptext.R
import com.example.snaptext.databinding.ActivityMainBinding
import com.example.snaptext.ui.translator.TranslatorFragment
import com.example.snaptext.ui.translator.TranslatorFragment.Companion.IMAGE_BYTE_ARRAY
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import org.koin.android.ext.android.inject
import java.io.ByteArrayOutputStream

internal class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainActivityViewModel by inject()

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val bitmap = data?.extras?.get("data") as Bitmap

                val bundle = Bundle()
                val translatorFragment = TranslatorFragment()
                val stream = ByteArrayOutputStream()

                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                val byteArray = stream.toByteArray()

                bundle.putByteArray(IMAGE_BYTE_ARRAY, byteArray)
                translatorFragment.arguments = bundle

                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_container, translatorFragment)
                    .commit()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.camera_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.camera -> {
            checkCameraPermission()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun checkCameraPermission() =
        Dexter.withContext(this)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(
                object : PermissionListener {
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                        launchCamera()
                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                        viewModel.showMessage(R.string.no_camera_permission)
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: PermissionRequest?,
                        p1: PermissionToken?
                    ) {
                        showRotationalDialogForPermission()
                    }
                }
            )
            .onSameThread()
            .check()

    private fun showRotationalDialogForPermission() =
        AlertDialog.Builder(this)
            .setMessage(R.string.alert_dialog_message)
            .setPositiveButton(R.string.go_to_settings) { _, _ ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .show()

    private fun launchCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncher.launch(intent)
    }
}