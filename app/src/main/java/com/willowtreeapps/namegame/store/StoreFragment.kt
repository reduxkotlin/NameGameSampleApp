package com.willowtreeapps.namegame.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beyondeye.reduks.StoreSubscriber
import com.beyondeye.reduks.StoreSubscription
import com.willowtreeapps.common.AppState
import com.willowtreeapps.common.NetworkThunks
import com.willowtreeapps.common.appStore
import com.willowtreeapps.namegame.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class StoreFragment : Fragment(), CoroutineScope, StoreSubscriber<AppState> {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var subscription: StoreSubscription? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.content_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        subscription = appStore.subscribe(this)

        appStore.dispatch(NetworkThunks(Dispatchers.IO).fetchProfiles())
    }


    override fun onDestroyView() {
        subscription?.unsubscribe()
        super.onDestroyView()
    }

    override fun onStateChange() {
        activity?.runOnUiThread {

        }
    }
}
