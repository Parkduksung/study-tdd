package com.example.study_tdd.espressotest.recyclerview

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.study_tdd.R
import com.example.study_tdd.espressotest.recyclerview.adapter.viewholder.EspressoRecyclerViewHolder
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EspressoRecyclerVIewActivityTest {

    companion object {
        private const val LAST_ITEM_POSITION = 49
        private const val LAST_ITEM = "박덕성 : 30 : programmer : 50"

    }

    @Test
    fun should_show_recyclerview_when_init() {

        ActivityScenario.launch(EspressoRecyclerVIewActivity::class.java)

        onView(ViewMatchers.withId(R.id.espresso_recycler)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }


    @Test
    fun should_not_show_last_item_when_not_scrolled_down() {

        ActivityScenario.launch(EspressoRecyclerVIewActivity::class.java)

        onView(ViewMatchers.withText(LAST_ITEM)).check(ViewAssertions.doesNotExist())
    }

    @Test
    fun should_show_last_position_when_launch() {

        ActivityScenario.launch(EspressoRecyclerVIewActivity::class.java)

        //원하는 Item Position 을 집어넣으면 거기까지 스크롤된다.
        Espresso.onView(ViewMatchers.withId(R.id.espresso_recycler)).perform(
            RecyclerViewActions.scrollToPosition<EspressoRecyclerViewHolder>(
                LAST_ITEM_POSITION
            )
        )
    }

    @Test
    fun should_show_toast_message_when_item_click() {
        ActivityScenario.launch(EspressoRecyclerVIewActivity::class.java)

        //범위내의 원하는 Item Position 값을 넣으면 된다.
        onView(ViewMatchers.withId(R.id.espresso_recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<EspressoRecyclerViewHolder>(
                LAST_ITEM_POSITION,
                ViewActions.click()
            )
        )
    }


}