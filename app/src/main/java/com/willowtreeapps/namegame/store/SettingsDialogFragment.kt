package com.willowtreeapps.namegame.store

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.willowtreeapps.common.QuestionCategoryId
import com.willowtreeapps.common.SettingsViewState
import com.willowtreeapps.common.ui.SettingsPresenter
import com.willowtreeapps.common.ui.SettingsView
import com.willowtreeapps.namegame.NameGameApp
import com.willowtreeapps.namegame.R
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsDialogFragment: DialogFragment(), SettingsView {

    var presenter: SettingsPresenter? = null

    companion object {
        fun newInstance(): SettingsDialogFragment {
            val f = SettingsDialogFragment()
            return f
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        return  dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        numberPicker.minValue = 1
        numberPicker.maxValue = 20
        numberPicker.setOnValueChangedListener { _, _, newVal ->
            presenter?.numQuestionsChanged(newVal)
        }

        categoryPicker.displayedValues = QuestionCategoryId.displayNameList.toTypedArray()
        categoryPicker.maxValue = 0
        categoryPicker.maxValue = QuestionCategoryId.displayNameList.toTypedArray().size - 1
        categoryPicker.setOnValueChangedListener { _, _, newVal ->
            presenter?.categoryChanged(QuestionCategoryId.fromOrdinal(newVal))
        }
        btn_ok.setOnClickListener { dismiss() }
    }

    override fun onResume() {
        super.onResume()
        presenter = NameGameApp.gameEngine().attachView(this) as SettingsPresenter
    }

    override fun onPause() {
        super.onPause()
        NameGameApp.gameEngine().detachView(this)
    }

    override fun showSettings(viewState: SettingsViewState) {
        numberPicker.value = viewState.numQuestions
        categoryPicker.value = QuestionCategoryId.values().indexOf(viewState.categoryId)
    }
}