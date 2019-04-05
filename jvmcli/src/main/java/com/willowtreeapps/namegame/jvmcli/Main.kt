@file:JvmName("Main")

package com.willowtreeapps.namegame.jvmcli

import com.willowtreeapps.common.GameEngine
import com.willowtreeapps.common.GameResultsViewState
import com.willowtreeapps.common.QuestionViewState
import com.willowtreeapps.common.middleware.Navigator
import com.willowtreeapps.common.middleware.Screen
import com.willowtreeapps.common.ui.*
import kotlinx.coroutines.Dispatchers
import java.util.*

/**
 * Proof of concept CLI implementation of NameGame using ascii art for images.  Images are hard to recognize
 * without tweaking CLI font and size.  Not fully implemented or functioning.  Shows first image and
 * does not respond to input.
 * Run with ./gradlew jvmcli:runMain
 */

val gameEngine = GameEngine(CliNavigator(), Any(), Dispatchers.Unconfined, Dispatchers.Unconfined)
val scanner = Scanner(System.`in`)
var view: CliView = Start

fun main() {
    print("Hello World")

    Start.onCreate()
    while (true) {
        view.handleInput(scanner.next())

    }
}

interface CliView {
    fun onCreate()
    fun onDestroy()
    fun handleInput(input: String)
}

object Start : StartView, CliView {
    private var presenter: StartPresenter? = null

    override fun handleInput(input: String) {
        when (input) {
            "yes", "y" -> presenter?.startGame()
        }
    }

    override fun onCreate() {
        presenter = gameEngine.attachView(this) as StartPresenter
        presenter?.startGame()
        print("Ready to play?")
    }

    override fun onDestroy() {
        gameEngine.detachView(this)
    }

    override fun showLoading() {
        print("Loading...")
    }

    override fun hideLoading() {
        print("----")
    }

    override fun showError(msg: String) {
        print("Error: $msg")
    }

}

object Question: QuestionView, CliView {
    private var presenter: QuestionPresenter? = null

    override fun onCreate() {
        presenter = gameEngine.attachView(this) as QuestionPresenter
    }

    override fun onDestroy() {
        gameEngine.detachView(this)
    }

    override fun handleInput(input: String) {
        presenter?.namePicked(input)
    }

    override fun showProfile(viewState: QuestionViewState) {
        ImageConverter.displayImage(viewState.profileImageUrl)
    }

    override fun showCorrectAnswer(viewState: QuestionViewState, isEndGame: Boolean) {
        print("Correct!!")
    }

    override fun showWrongAnswer(viewState: QuestionViewState, isEndGame: Boolean) {
        print("Wrong!!")
    }

    override fun setTimerText(viewState: QuestionViewState) {
        print(viewState.timerText)
    }

    override fun showTimesUp(viewState: QuestionViewState, isEndGame: Boolean) {
        print(viewState.timerText)
    }



}

object GameResults: GameResultsView, CliView {
    override fun onCreate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showResults(viewState: GameResultsViewState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun handleInput(input: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class CliNavigator : Navigator {
    override fun goto(screen: Screen) {
        view.onDestroy()
        view = when (screen) {
            Screen.START -> Start
            Screen.QUESTION -> Question
            Screen.GAME_COMPLETE -> GameResults
            else -> throw IllegalArgumentException()
        }
        view.onCreate()
    }
}
