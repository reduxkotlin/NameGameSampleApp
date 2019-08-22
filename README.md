# NameGame

__Android Build & Tests__


[![CircleCI](https://circleci.com/gh/patjackson52/NameGameKotlinMpp.svg?style=svg)](https://circleci.com/gh/patjackson52/NameGameKotlinMpp)

__iOS Build & Tests____

[![Bitrise](https://app.bitrise.io/app/3eaf4fa6c0750504/status.svg?token=N7jdGFn6dvfLcuKZaBMW1g)](https://app.bitrise.io/app/3eaf4fa6c0750504#/builds)

A Kotlin multiplatform (Android/iOS) name game.  Player is shown a picture of a cat or dog and must guess the name.  User can select from Cats, Dogs.  The app utilizes the following:
 * [Ktor](https://ktor.io/clients/http-client.html) for networking
 * [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)
 * [Multiplatform Settings](https://github.com/russhwolf/multiplatform-settings)
 * [ReduxKotlin](https://github.com/reduxkotlin/redux-kotlin/)

## Android
Building and testing the Android App can be completed with:
```./gradlew build```
or install with:
```./gradle androidInstall```
or opened and ran in Android Studio


## iOS
The iOS workspace in `/iOS/NameGame` can be open and ran from xCode or AppCode.  A run script has been added to the build phase that will compile the common code into a framework which can be used for the project.


## Architecture

More to come
    
## "Dumb Views"
Views in this arch are truely 'dumb' - they should contain nearly no logic.  They are responsible for rendering the view based on the `ViewState` given to them by the presenter. They are implemented for each platform and utilize native UI SDKs and libs for each platform.  Android uses Fragments and iOS uses UIViewControllers.

## Presenters

![](https://storage.googleapis.com/treestorage/ui_f_of_state.png)

more to come


![arch diagram](https://storage.googleapis.com/treestorage/Kotlin%20MPP%20Demo%20Arch.png)

## Async Actions
In redux world there are many ways to handle creation of async actions.  `Thunks` have been used in this app.  `NetworkThunks` and `TimerThunks` both use coroutines to launch concurrent operations that dispatch actions.

## Navigation
In this app, Navigation is considered a side effect of the AppState.  The `NavigationMiddleware` handles changing screens based on dispatched actions.  The `NavigationMiddleware` takes an implementation of `Navigator` which is implemeneted for each platform.


