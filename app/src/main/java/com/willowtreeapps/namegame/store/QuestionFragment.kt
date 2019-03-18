package com.willowtreeapps.namegame.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.willowtreeapps.common.repo.Profile
import com.willowtreeapps.common.view.QuestionScreen
import com.willowtreeapps.namegame.NameGameApp
import com.willowtreeapps.namegame.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class QuestionFragment : Fragment(), CoroutineScope, QuestionScreen {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        NameGameApp.instance.presenter.attachView(this)
    }

    override fun showCorrectAnswer(profileId: String) {
    }

    override fun showLoading() {
    }

    override fun showProfile(profile: Profile) {
    }

    override fun showWrongAnswer(profileId: String) {
    }

    override fun onDestroyView() {
        NameGameApp.instance.presenter.detachView()
        super.onDestroyView()
    }

}