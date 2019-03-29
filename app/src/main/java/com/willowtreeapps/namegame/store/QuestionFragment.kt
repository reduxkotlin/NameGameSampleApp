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
import android.view.animation.BounceInterpolator
import androidx.fragment.app.Fragment
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.willowtreeapps.common.QuestionPresenter
import com.willowtreeapps.common.QuestionViewState
import com.willowtreeapps.common.view.QuestionScreen
import com.willowtreeapps.namegame.GlideApp
import com.willowtreeapps.namegame.MainActivity
import com.willowtreeapps.namegame.NameGameApp
import com.willowtreeapps.namegame.R
import kotlinx.android.synthetic.main.fragment_question.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size
import java.lang.IllegalStateException
import kotlin.coroutines.CoroutineContext
import android.widget.Button
import androidx.annotation.ColorRes


class QuestionFragment : Fragment(), CoroutineScope, QuestionScreen, MainActivity.IOnBackPressed {

    private var presenter: QuestionPresenter? = null

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    var restoreX: Float? = null
    var restoreY: Float? = null
    @ColorRes
    var lastCorrectBtn: Button? = null
    var lastSelectedBtn: Button? = null

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


    override fun onBackPressed(): Boolean {
        NameGameApp.instance.presenterFactory.detachView(presenter!!)
        presenter?.onBackPressed()
        return false
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


    override fun showCorrectAnswer(viewState: QuestionViewState) {
        hideButtonsShowNext(viewState, false)
        celebrate()
    }

    override fun showWrongAnswer(viewState: QuestionViewState) {
        val selectedBtn = getBtnByNum(viewState.selectedBtnNum)
        selectedBtn.isSelected = true

        wrongBounceAnimation(viewState) { hideButtonsShowNext(viewState, false) }
    }

    override fun showCorrectAnswerEndGame(viewState: QuestionViewState) {
        hideButtonsShowNext(viewState, true)
        celebrate()
    }

    override fun showWrongAnswerEndGame(viewState: QuestionViewState) {
        hideButtonsShowNext(viewState, true)
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

    private fun wrongBounceAnimation(viewState: QuestionViewState, f: () -> Unit) {
        val selectedBtn = getBtnByNum(viewState.selectedBtnNum)
        val animScaleX = ObjectAnimator.ofFloat(selectedBtn, "scaleX", 3F, 0.5F, 1F)
        val animScaleY = ObjectAnimator.ofFloat(selectedBtn, "scaleY", 3F, 0.5F, 1F)
        val upSet = AnimatorSet()
        upSet.playTogether(animScaleX, animScaleY)
        upSet.interpolator = BounceInterpolator()
        upSet.duration = 500
        upSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                f()
            }
        })
        upSet.start()
    }

    private fun hideButtonsShowNext(viewState: QuestionViewState, isEndGame: Boolean) {

        val correctBtn = getBtnByNum(viewState.correctBtnNum)
        val selectedBtn = getBtnByNum(viewState.selectedBtnNum)

        fun View.hideOrMoveAnimation(isCorrect: Boolean): AnimatorSet {
            return if (isCorrect) {

                val endX = imageView.x + (imageView.width - this.width) / 2
                val endY = imageView.y + imageView.height

                val animX = ObjectAnimator.ofFloat(this, "x", endX)
                val animY = ObjectAnimator.ofFloat(this, "y", endY)
                val animScaleX = ObjectAnimator.ofFloat(this, "scaleX", 2F)
                val animScaleY = ObjectAnimator.ofFloat(this, "scaleY", 2F)
                val set = AnimatorSet()
                set.playTogether(animX, animY, animScaleX, animScaleY)
                set
            } else {
                val anim = ObjectAnimator.ofFloat(this, View.ALPHA, 0F)
                val set = AnimatorSet()
                set.playTogether(anim)
                set
            }
        }
        restoreX = correctBtn.x
        restoreY = correctBtn.y
        lastCorrectBtn = correctBtn
        lastSelectedBtn = selectedBtn

        val anim1 = button1.hideOrMoveAnimation(button1 == correctBtn)
        val anim2 = button2.hideOrMoveAnimation(button2 == correctBtn)
        val anim3 = button3.hideOrMoveAnimation(button3 == correctBtn)
        val anim4 = button4.hideOrMoveAnimation(button4 == correctBtn)

        val set = AnimatorSet()
        set.playTogether(anim1, anim2, anim3, anim4)
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                val btn = if (isEndGame) {
                    btn_end_game
                } else {
                    btn_next
                }
                btn.visibility = View.VISIBLE
                btn.alpha = 0F
                btn.animate().alpha(1f)
            }
        })
        set.start()
    }

    private fun showButtons() {
        if (restoreX != null && restoreY != null) {
            lastCorrectBtn?.x = restoreX!!
            lastCorrectBtn?.y = restoreY!!
            lastCorrectBtn?.scaleX = 1F
            lastCorrectBtn?.scaleY = 1F
            lastSelectedBtn?.isSelected = false
        }
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

    private fun getBtnByNum(num: Int): Button = when (num) {
        1 -> button1
        2 -> button2
        3 -> button3
        4 -> button4
        else -> throw IllegalStateException("Invalid correct button index")
    }


}