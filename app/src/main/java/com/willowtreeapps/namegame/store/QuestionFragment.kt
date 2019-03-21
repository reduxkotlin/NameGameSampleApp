package com.willowtreeapps.namegame.store

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.willowtreeapps.common.QuestionPresenter
import com.willowtreeapps.common.QuestionViewState
import com.willowtreeapps.common.view.QuestionScreen
import com.willowtreeapps.namegame.GlideApp
import com.willowtreeapps.namegame.NameGameApp
import com.willowtreeapps.namegame.R
import kotlinx.android.synthetic.main.fragment_question.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size
import kotlin.coroutines.CoroutineContext

class QuestionFragment : Fragment(), CoroutineScope, QuestionScreen {

    private var presenter: QuestionPresenter? = null

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter = NameGameApp.instance.presenterFactory.attachView(this) as QuestionPresenter
    }

    override fun onPause() {
        super.onPause()
        NameGameApp.instance.presenterFactory.detachView(presenter!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
    }

    private fun initViews() {
        button1.setOnClickListener { presenter?.namePicked(button1.text.toString()) }
        button2.setOnClickListener { presenter?.namePicked(button2.text.toString()) }
        button3.setOnClickListener { presenter?.namePicked(button3.text.toString()) }
        button4.setOnClickListener { presenter?.namePicked(button4.text.toString()) }
        btn_next.setOnClickListener { presenter?.nextTapped() }
        btn_end_game.setOnClickListener { presenter?.endGameTapped() }
    }


    override fun showCorrectAnswer() {
        hideButtonsShowNext()
        celebrate()
    }


    override fun showProfile(viewState: QuestionViewState) {
        activity?.runOnUiThread {
            if (btn_next.visibility == View.VISIBLE) {
                fadeNextButton { setProfileAndFadeIn(viewState) }
            } else {
                setProfileAndFadeIn(viewState)
            }
        }
    }

    override fun showWrongAnswer() {
        hideButtonsShowNext()
    }

    override fun showCorrectAnswerEndGame() {
        hideButtonsShowEndAnimatorSet.start()
        celebrate()
    }

    override fun showWrongAnswerEndGame() {
        hideButtonsShowEndAnimatorSet.start()
    }

    private val hideButtonsShowNextAnimatorSet by lazy {
        val hide1 = ObjectAnimator.ofFloat(button1, View.ALPHA, 0F)
        val hide2 = ObjectAnimator.ofFloat(button2, View.ALPHA, 0F)
        val hide3 = ObjectAnimator.ofFloat(button3, View.ALPHA, 0F)
        val hide4 = ObjectAnimator.ofFloat(button4, View.ALPHA, 0F)
        val set = AnimatorSet()
        set.playTogether(hide1, hide2, hide3, hide4)
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                btn_next.visibility = View.VISIBLE
                btn_next.alpha = 0F
                btn_next.animate().alpha(1f)
            }
        })
        set
    }

    private val hideButtonsShowEndAnimatorSet by lazy {
        val hide1 = ObjectAnimator.ofFloat(button1, View.ALPHA, 0F)
        val hide2 = ObjectAnimator.ofFloat(button2, View.ALPHA, 0F)
        val hide3 = ObjectAnimator.ofFloat(button3, View.ALPHA, 0F)
        val hide4 = ObjectAnimator.ofFloat(button4, View.ALPHA, 0F)
        val set = AnimatorSet()
        set.playTogether(hide1, hide2, hide3, hide4)
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                btn_end_game.visibility = View.VISIBLE
                btn_end_game.alpha = 0F
                btn_end_game.animate().alpha(1f)
            }
        })
        set
    }

    private val showButtonsAnimatorSet by lazy {
        val hide1 = ObjectAnimator.ofFloat(button1, View.ALPHA, 1F)
        val hide2 = ObjectAnimator.ofFloat(button2, View.ALPHA, 1F)
        val hide3 = ObjectAnimator.ofFloat(button3, View.ALPHA, 1F)
        val hide4 = ObjectAnimator.ofFloat(button4, View.ALPHA, 1F)
        val set = AnimatorSet()
        set.playTogether(hide1, hide2, hide3, hide4)
        set
    }

    private fun hideButtonsShowNext() {
        hideButtonsShowNextAnimatorSet.start()
    }

    private fun showButtons() {
        showButtonsAnimatorSet.start()
    }

    private fun fadeNextButton(after: () -> Unit) {
        btn_next.animate().alpha(0f).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                btn_next.visibility = View.GONE
                after()
                btn_next.animate().setListener(null)
            }
        })
    }

    private fun setProfileAndFadeIn(viewState: QuestionViewState) {
        with(viewState) {
            txt_results.text = title
            GlideApp.with(this@QuestionFragment).load(profileImageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)
            button1.text = button1Text
            button2.text = button2Text
            button3.text = button3Text
            button4.text = button4Text
            showButtons()
        }
    }

    private fun celebrate() {
        view_konfetti.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(5000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(Size(12), Size(16, 6f))
                .setPosition(-50f, view_konfetti.width + 50f, -50f, -50f)
                .burst(200)
    }

}