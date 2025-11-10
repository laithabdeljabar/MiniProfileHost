package com.laithabdeljabar.miniprofilehost

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laithabdeljabar.miniprofilehost.databinding.ActivityMiniProfileBinding
import java.text.DateFormat
import java.util.Date

class MiniProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMiniProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMiniProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonClose.setOnClickListener { finish() }
        binding.buttonRefresh.setOnClickListener { renderProfile() }
        binding.buttonClear.setOnClickListener {
            val manager = ProfileManager.getInstance(this)
            manager.setUserName("")
            manager.setUserEmail("")
            manager.setProfileImage("")
            manager.setUserPhone("")
            renderProfile()
        }

        renderProfile()
    }

    private fun renderProfile() {
        val manager = ProfileManager.getInstance(this)
        if (!manager.isInitialized()) {
            binding.profileStatus.text = getString(R.string.mini_profile_not_initialized)
            binding.profileDetailsGroup.alpha = 0.5f
            binding.profileNameValue.text = getString(R.string.mini_profile_placeholder)
            binding.profileEmailValue.text = getString(R.string.mini_profile_placeholder)
            binding.profilePhoneValue.text = getString(R.string.mini_profile_placeholder)
            binding.profileImageValue.text = getString(R.string.mini_profile_placeholder)
            binding.profileUpdatedValue.text = getString(R.string.mini_profile_placeholder)
            return
        }

        binding.profileStatus.text = getString(R.string.mini_profile_initialized)
        binding.profileDetailsGroup.alpha = 1f
        binding.profileNameValue.text = manager.getUserName().orPlaceholder()
        binding.profileEmailValue.text = manager.getUserEmail().orPlaceholder()
        binding.profilePhoneValue.text = manager.getUserPhone().orPlaceholder()
        binding.profileImageValue.text = manager.getProfileImage().orPlaceholder()
        binding.profileUpdatedValue.text = manager.getLastUpdatedTime()
            .takeIf { it > 0 }
            ?.let { millis -> DateFormat.getDateTimeInstance().format(Date(millis)) }
            ?: getString(R.string.mini_profile_placeholder)
    }

    private fun String?.orPlaceholder(): String {
        return if (isNullOrBlank()) {
            getString(R.string.mini_profile_placeholder)
        } else {
            this
        }
    }
}

