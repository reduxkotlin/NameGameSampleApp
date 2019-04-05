package com.willowtreeapps.namegame.store

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import androidx.fragment.app.Fragment
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.willowtreeapps.common.QuestionViewState
import com.willowtreeapps.common.ui.QuestionView
import kotlinx.android.synthetic.main.fragment_question.*
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size
import kotlin.coroutines.CoroutineContext
import android.widget.Button
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.willowtreeapps.common.ui.QuestionPresenter
import com.willowtreeapps.namegame.*
import kotlinx.coroutines.*


class QuestionFragment : Fragment(), CoroutineScope, QuestionView, MainActivity.IOnBackPressed {

    private lateinit var presenter: QuestionPresenter

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var restoreX: Float? = null
    private var restoreY: Float? = null
    var lastCorrectBtn: Button? = null
    private var lastSelectedBtn: Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter = NameGameApp.gameEngine().attachView(this) as QuestionPresenter
    }

    override fun onPause() {
        super.onPause()
        NameGameApp.gameEngine().detachView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
    }

    private fun initViews() {
        button1.setOnClickListener { presenter.namePicked(button1.text.toString()) }
        button2.setOnClickListener { presenter.namePicked(button2.text.toString()) }
        button3.setOnClickListener { presenter.namePicked(button3.text.toString()) }
        button4.setOnClickListener { presenter.namePicked(button4.text.toString()) }
        btn_next.setOnClickListener { presenter.nextTapped() }
        btn_end_game.setOnClickListener { presenter.endGameTapped() }
    }


    override fun onBackPressed(): Boolean {
        NameGameApp.gameEngine().detachView(this)
        presenter.onBackPressed()
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

    override fun showCorrectAnswer(viewState: QuestionViewState, isEndGame: Boolean) {
        hideButtonsShowNext(viewState, isEndGame)
        celebrate()
    }

    override fun showWrongAnswer(viewState: QuestionViewState, isEndGame: Boolean) {
        wrongShakeAnimation(viewState) { hideButtonsShowNext(viewState, isEndGame) }
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

    private fun wrongShakeAnimation(viewState: QuestionViewState, after: () -> Unit) {
        val selectedBtn = getBtnByNum(viewState.selectedBtnNum)
        if (selectedBtn != null) {
            selectedBtn.isSelected = true
            val animScaleX = ObjectAnimator.ofFloat(selectedBtn, View.SCALE_X, 3F, 0.5F, 1F)
            val animScaleY = ObjectAnimator.ofFloat(selectedBtn, View.SCALE_Y, 3F, 0.5F, 1F)
            val upSet = AnimatorSet()
            upSet.playTogether(animScaleX, animScaleY)
            upSet.interpolator = BounceInterpolator()
            upSet.duration = 500
            upSet.onComplete { after() }
            upSet.start()
        } else {
            after()
        }
    }

    /**
     *  Hides the incorrect buttons and animates the correct name to be centered below profile image
     */
    private fun hideButtonsShowNext(viewState: QuestionViewState, isEndGame: Boolean) {

        val correctBtn = getBtnByNum(viewState.correctBtnNum)
        val selectedBtn = getBtnByNum(viewState.selectedBtnNum)

        fun View.hideOrMoveAnimation(): AnimatorSet {
            return if (this == correctBtn) {
                val endX = imageView.x + (imageView.width - this.width) / 2
                val endY = imageView.y + imageView.height

                val animX = ObjectAnimator.ofFloat(this, View.X, endX)
                val animY = ObjectAnimator.ofFloat(this, View.Y, endY)
                val animScaleX = ObjectAnimator.ofFloat(this, View.SCALE_X, 2F)
                val animScaleY = ObjectAnimator.ofFloat(this, View.SCALE_Y, 2F)
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
        restoreX = correctBtn?.x
        restoreY = correctBtn?.y
        lastCorrectBtn = correctBtn
        lastSelectedBtn = selectedBtn

        val anim1 = button1.hideOrMoveAnimation()
        val anim2 = button2.hideOrMoveAnimation()
        val anim3 = button3.hideOrMoveAnimation()
        val anim4 = button4.hideOrMoveAnimation()

        val set = AnimatorSet()
        set.playTogether(anim1, anim2, anim3, anim4)
        set.onComplete {
            val btn = if (isEndGame) {
                btn_end_game
            } else {
                btn_next
            }
            btn.visibility = View.VISIBLE
            btn.alpha = 0F
            btn.animate().alpha(1f)
        }
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
        btn_next.animate().alpha(0f).withEndAction {
            btn_next.visibility = View.GONE
            after()
        }
    }

    private fun setProfileAndFadeIn(viewState: QuestionViewState) {
        with(viewState) {
            txt_results.text = title
            GlideApp.with(this@QuestionFragment).load(profileImageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            return true
                        }
                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            showButtons()
                            return false
                        }
                    })
                    .into(imageView)
            button1.text = button1Text
            button2.text = button2Text
            button3.text = button3Text
            button4.text = button4Text
        }
    }

    override fun setTimerText(viewState: QuestionViewState) {
        activity?.runOnUiThread {
            txt_timer.scaleX = 0f
            txt_timer.scaleY = 0f
            txt_timer.alpha = 1f
            txt_timer.text = viewState.timerText.toString()
            txt_timer.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setInterpolator(FastOutSlowInInterpolator())
                    .setDuration(500)
                    .withEndAction {
                        if (txt_timer != null) {
                            txt_timer.animate()
                                    .scaleX(0f)
                                    .scaleY(0f)
                                    .duration = 500
                        }
                    }
        }
    }

    override fun showTimesUp(viewState: QuestionViewState, isEndGame: Boolean) {
        activity?.runOnUiThread {
            txt_timer.scaleX = 0f
            txt_timer.scaleY = 0f
            txt_timer.text = viewState.timerText
            val restoreColor = txt_timer.currentTextColor
            txt_timer.setTextColor(ResourcesCompat.getColor(context?.resources!!, R.color.red, null))
            txt_timer.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setInterpolator(FastOutSlowInInterpolator())
                    .setDuration(500)
                    .withEndAction {
                        showWrongAnswer(viewState, isEndGame)
                        txt_timer.animate().alpha(0f)
                                .withEndAction { txt_timer.setTextColor(restoreColor)  }
                    }

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

    private fun getBtnByNum(num: Int): Button? = when (num) {
        1 -> button1
        2 -> button2
        3 -> button3
        4 -> button4
        else -> null//throw IllegalStateException("Invalid button index")
    }


}