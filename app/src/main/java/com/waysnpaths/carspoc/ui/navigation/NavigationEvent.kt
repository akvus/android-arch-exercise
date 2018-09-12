package com.waysnpaths.carspoc.ui.navigation

import io.reactivex.subjects.PublishSubject

object NavigationEvent {
    val subject: PublishSubject<NavigationState> = PublishSubject.create()

    fun goTo(state: NavigationState) {
        subject.onNext(state)
    }
}