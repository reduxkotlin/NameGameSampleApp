# NameGame

__Android Build & Tests__


[![CircleCI](https://circleci.com/gh/patjackson52/NameGameKotlinMpp.svg?style=svg)](https://circleci.com/gh/patjackson52/NameGameKotlinMpp)

__iOS Build & Tests____

[![Bitrise](https://app.bitrise.io/app/3eaf4fa6c0750504/status.svg?token=N7jdGFn6dvfLcuKZaBMW1g)](https://app.bitrise.io/app/3eaf4fa6c0750504#/builds)

A Kotlin multiplatform (Android/iOS) WT name game.  Player is shown a picture and must guess the name.  User can select from Cats, Dogs, or Trees! (aka Willowtree employees).  The app utilizes the following:
 * [Ktor](https://ktor.io/clients/http-client.html) for networking
 * [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)
 * [Multiplatform Settings](https://github.com/russhwolf/multiplatform-settings)
 * fork of [Reduks](https://github.com/beyondeye/Reduks/tree/3.x_kotlin_1_3) (kotlin redux implementation)


## Android
Building and testing the Android App can be completed with:
```./gradlew build```
or install with:
```./gradle androidInstall```
or opened and ran in Android Studio


## iOS
The iOS workspace in `/iOS/NameGame` can be open and ran from xCode or AppCode.  A run script has been added to the build phase that will compile the common code into a framework which can be used for the project.


## Architecture

A `GameEngine` object holds the state of the app and provides a methods for views (fragments/UIViewControllers) to "attach".  The `GameEngine` is initialized in the Application class on Android, and the AppDelegate on iOS.  Each view must attach/detach from the GameEngine when it is visible.  `GameEngine.attachView(view)` returns the appropriate presenter for the view.

`BaseNameGameViewFragment` & `BaseNameGameViewController` handle attaching/detaching the presenter at the appropriate lifecycle methods.  Each Fragment/ViewController extends from these.

An MVP arch is used with a redux store as the 'Model'.  This approach allows maximum reuse of code and a simple contract for the platforms to satisfy.  Presenters send `ViewStates` (simple data classes with fields needed to render UI) to the View interface.  The View implementation has a reference to the presenter, and calls methods for user interaction.  This creates a unidirectional dataflow:

    User interaction -> Dispatch Action -> new state (reduce) -> view rendered by presenter
    
## "Dumb Views"
Views in this arch are truely 'dumb' - they should contain nearly no logic.  They are responsible for rendering the view based on the `ViewState` given to them by the presenter. 

## Presenters

![](https://storage.googleapis.com/treestorage/ui_f_of_state.png)


Presenters give a layer of control between subscribing to the new state and the View.  Having views subscribing directly to the store results in code and logic in the View which must be duplicated on each platform.  Presenters are singleton objects that contain no state other than the previous AppState.  This works while presenters are for an entire screen, which for this app is the case.  Another approach will be needed if multiple instances of a given presenter are needed.  The presenter is responsible for rendering the view given the AppState or the delta in AppState.  The Reduks library has a port of Reselect, which allows calling code only when a property changes.  Presenters pass `ViewState` to View methods.  All transformations from Appstate -> ViewState are extension functions in `Transformations.kt`.



![arch diagram](https://storage.googleapis.com/treestorage/Kotlin%20MPP%20Demo%20Arch.png)

## Async Actions
In redux world there are many ways to handle creation of async actions.  `Thunks` have been used in this app.  `NetworkThunks` and `TimerThunks` both use coroutines to launch concurrent operations that dispatch actions.

## Navigation
In this app, Navigation is considered a side effect of the AppState.  The `NavigationMiddleware` handles changing screens based on dispatched actions.  The `NavigationMiddleware` takes an implementation of `Navigator` which is implemeneted for each platform.


