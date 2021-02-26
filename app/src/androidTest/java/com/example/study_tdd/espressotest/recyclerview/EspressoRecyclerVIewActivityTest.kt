package com.example.study_tdd.espressotest.recyclerview

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EspressoRecyclerVIewActivityTest {

    companion object{

        private const val LAST_ITEM = "박덕성 : 30 : programmer : 50"

    }

    @Test
    fun should_not_show_last_item_when_not_scrolled_down() {

        ActivityScenario.launch(EspressoRecyclerVIewActivity::class.java)

        onView(ViewMatchers.withText(LAST_ITEM)).check(ViewAssertions.doesNotExist())
    }

}