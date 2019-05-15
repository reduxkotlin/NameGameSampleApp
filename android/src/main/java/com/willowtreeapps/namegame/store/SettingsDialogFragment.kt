package com.willowtreeapps.namegame.store

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseAuth
import com.willowtreeapps.common.Logger
import com.willowtreeapps.common.QuestionCategoryId
import com.willowtreeapps.common.SettingsViewState
import com.willowtreeapps.common.ui.SettingsPresenter
import com.willowtreeapps.common.ui.SettingsView
import com.willowtreeapps.namegame.MainActivity
import com.willowtreeapps.namegame.NameGameApp
import com.willowtreeapps.namegame.R
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsDialogFragment : DialogFragment(), SettingsView {

    override lateinit var presenter: SettingsPresenter

    companion object {
        fun newInstance(): SettingsDialogFragment {
            return SettingsDialogFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        numberPicker.minValue = 1
        numberPicker.maxValue = 20
        numberPicker.setOnValueChangedListener { _, _, newVal ->
            presenter.numQuestionsChanged(newVal)
        }

        categoryPicker.setOnValueChangedListener { _, _, newVal ->
            presenter.categoryChanged(QuestionCategoryId.fromDisplayName(categoryPicker.displayedValues[categoryPicker.value])!!)
        }
        btn_ok.setOnClickListener { dismiss() }
        btn_sign_in.setOnClickListener { launchSignIn() }
        switch_mic.setOnCheckedChangeListener { buttonView, isChecked ->
            presenter.microphoneModeChanged(isChecked)
        }
    }

    override fun onResume() {
        super.onResume()
        NameGameApp.gameEngine().attachView(this)
    }

    override fun onPause() {
        super.onPause()
        NameGameApp.gameEngine().detachView(this)
    }

    override fun showSettings(viewState: SettingsViewState) {
        categoryPicker.displayedValues = viewState.categoryDisplayValues.toTypedArray()
        categoryPicker.maxValue = 0
        categoryPicker.maxValue = viewState.categoryDisplayValues.toTypedArray().size - 1
        numberPicker.value = viewState.numQuestions
        categoryPicker.value = viewState.categoryDisplayValues.indexOf(viewState.categoryId.displayName)
        switch_mic.isChecked = viewState.isMicModeEnabled
        btn_sign_in.setOnClickListener {
            if (viewState.isWillowTree) {
                signOut()
            } else {
                launchSignIn()
            }
        }
//        btn_sign_in.visibility = if (viewState.showSignIn) View.VISIBLE else View.GONE

        btn_sign_in.text = viewState.signInBtnText
    }

    override fun askForMicPermissions() {
        setupPermissions()
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(activity!!,
                Manifest.permission.RECORD_AUDIO)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Logger.d("Permission to record denied")
            requestPermissions(
                    arrayOf(Manifest.permission.RECORD_AUDIO),
                    MainActivity.RECORD_REQUEST_CODE)
        } else {
            presenter.microphonePermissionGranted()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MainActivity.RECORD_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    presenter.microphonePermissionDenied()
                } else {
                    presenter.microphonePermissionGranted()
                }
            }
        }
    }


    private fun launchSignIn() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(listOf(AuthUI.IdpConfig.GoogleBuilder().build()))
                        .build(), 0)
    }

    private fun signOut() {
        AuthUI.getInstance()
                .signOut(activity?.applicationContext!!)
                .addOnCompleteListener {
                    presenter.signOutSuccess()
                }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            val auth = FirebaseAuth.getInstance()
            val email = auth.currentUser?.email!!
            presenter.signInSuccess()
        }

    }
}