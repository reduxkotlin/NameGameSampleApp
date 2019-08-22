package org.reduxkotlin.namegame.common.util

import org.reduxkotlin.AttachView
import org.reduxkotlin.DetachView
import org.reduxkotlin.View
import org.reduxkotlin.rootDispatch

//Here only until a solution is found for rootDispatch & AttachView class not
//visible from swift.
fun attachView(view: View) = rootDispatch(AttachView(view))
fun detachView(view: View) = rootDispatch(DetachView(view))
fun clearView(view: View) = rootDispatch(DetachView(view))
