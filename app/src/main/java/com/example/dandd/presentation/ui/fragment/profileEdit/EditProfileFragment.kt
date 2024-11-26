package com.example.dandd.presentation.ui.fragment.profileEdit

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dungeonanddragonsapp.R
import com.example.dungeonanddragonsapp.databinding.FragmentEditProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.util.Date


class EditProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val editProfileViewModel: EditProfileViewModel by viewModel()

    private val binding get() = _binding!!

    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>

    private var photoUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cameraLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                val imageUri = result.data?.data ?: photoUri
                imageUri?.let {
                    editProfileViewModel.updatePhotoUri(it)
                    binding.editProfilePhoto.setImageURI(editProfileViewModel.profileState.value.photoUri)
                }
            }

        galleryLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val selectedUri = result.data?.data ?: photoUri

                    selectedUri?.let {
                        val destinationFile = createImageFile()
                        copyUriToFile(it, destinationFile)
                        photoUri = Uri.fromFile(destinationFile)

                        editProfileViewModel.updatePhotoUri(photoUri ?: Uri.EMPTY)
                        binding.editProfilePhoto.setImageURI(editProfileViewModel.profileState.value.photoUri)
                    }
                }
            }

        binding.apply {
            editProfileTimePicker.setIs24HourView(true)
            editProfileSaveButton.setOnClickListener {
                editProfileViewModel.save(
                    back = {
                        findNavController().navigate(R.id.navigation_home)
                    },
                    context = requireContext()
                )
            }

            editProfileName.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    editProfileViewModel.updateName(s.toString())
                }
            })

            editProfileDesc.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    editProfileViewModel.updateDescription(s.toString())
                }
            })

            editProfileTimepickerInput.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    editProfileViewModel.updateTimeInput(s.toString())
                    binding.editProfileError.text = editProfileViewModel.profileState.value.timeError
                }
            })

            editProfilePhoto.setOnClickListener {
                showImagePickerDialog()
            }

            editProfileOpenTimer.setOnClickListener {
                toggle()
            }
            editProfileTimePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
                editProfileViewModel.updateTimeTimer(hourOfDay, minute)
            }

            editProfileOkTimer.setOnClickListener{
                toggle()
                binding.editProfileTimepickerInput.setText(editProfileViewModel.profileState.value.timeString)
                binding.editProfileError.text = editProfileViewModel.profileState.value.timeError
            }
        }
    }

    private fun toggle(){
        editProfileViewModel.toggleTimer()
        editProfileViewModel.profileState.value.isNeedToShowTimePicker.also {
            binding.editProfileName.isVisible = !it
            binding.editProfileDesc.isVisible = !it
            binding.editProfilePhoto.isVisible = !it
            binding.editProfileSaveButton.isVisible = !it
            binding.editProfileTimerLayout.isVisible = !it
            binding.editProfileTimePicker.isVisible = it
            binding.editProfileOkTimer.isVisible = it
        }
    }

    private fun showImagePickerDialog() {
        val options = arrayOf("Сделать фото", "Выбрать из галереи")

        AlertDialog.Builder(requireContext())
            .setTitle("Выберите действие")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> openCamera()
                    1 -> openGallery()
                }
            }
            .setNegativeButton("Отмена", null)
            .show()
    }

    private fun openCamera() {
        val baseDir = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES
        )
        val pictureFile = File(baseDir, "picture_${Date().time}.jpg")
        photoUri = FileProvider.getUriForFile(
            requireContext(),
            requireContext().packageName + ".provider",
            pictureFile
        )

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        }
        cameraLauncher.launch(cameraIntent)
    }

    private fun openGallery() {
        val galleryIntent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                type = "image/*"
            }
        galleryLauncher.launch(galleryIntent)
    }

    private fun createImageFile(): File {
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "photo_${System.currentTimeMillis()}",
            ".jpg",
            storageDir
        )
    }

    private fun copyUriToFile(sourceUri: Uri, destinationFile: File) {
        requireContext().contentResolver.openInputStream(sourceUri).use { inputStream ->
            destinationFile.outputStream().use { outputStream ->
                inputStream?.copyTo(outputStream)
            }
        }
    }
}