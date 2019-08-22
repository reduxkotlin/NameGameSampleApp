package org.reduxkotlin.namegame.store

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import org.reduxkotlin.namegame.common.middleware.UiActions
//import com.google.firebase.auth.FirebaseAuth
import org.reduxkotlin.namegame.common.ui.SettingsView
import org.reduxkotlin.namegame.common.ui.SettingsViewState
import org.reduxkotlin.namegame.MainActivity
import org.reduxkotlin.namegame.dispatch
import kotlinx.android.synthetic.main.fragment_settings.*
import org.reduxkotlin.PresenterLifecycleObserver
import org.reduxkotlin.namegame.R
import org.reduxkotlin.namegame.common.QuestionCategoryId
import org.reduxkotlin.namegame.common.util.Logger

class SettingsDialogFragment : DialogFragment(), SettingsView {

    private val presenterObserver = PresenterLifecycleObserver(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(presenterObserver)
    }

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
            dispatch(UiActions.NumQuestionsPicked(newVal))
        }

        categoryPicker.setOnValueChangedListener { _, _, newVal ->
            val newCategory = QuestionCategoryId.fromDisplayName(categoryPicker.displayedValues[categoryPicker.value])!!
            dispatch(UiActions.CategoryPicked(newCategory))
        }
        btn_ok.setOnClickListener { dismiss() }
        switch_mic.setOnCheckedChangeListener { buttonView, isChecked ->
            //TODO consider moving this logic somewhere else
            if (isChecked) {
                askForMicPermissions()
            } else {
                dispatch(UiActions.MicrophoneModeToggled(false))
            }
        }
    }

    override fun showSettings(viewState: SettingsViewState) {
        categoryPicker.displayedValues = viewState.categoryDisplayValues.toTypedArray()
        categoryPicker.maxValue = 0
        categoryPicker.maxValue = viewState.categoryDisplayValues.toTypedArray().size - 1
        numberPicker.value = viewState.numQuestions
        categoryPicker.value = viewState.categoryDisplayValues.indexOf(viewState.categoryId.displayName)
        switch_mic.isChecked = viewState.isMicModeEnabled
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
            dispatch(UiActions.MicrophoneModeToggled(true))
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MainActivity.RECORD_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    dispatch(UiActions.MicrophoneModeToggled(false))
                } else {
                    dispatch(UiActions.MicrophoneModeToggled(true))
                }
            }
        }
    }
}