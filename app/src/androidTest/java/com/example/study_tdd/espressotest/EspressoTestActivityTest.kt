package com.example.study_tdd.espressotest

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.study_tdd.R
import com.example.study_tdd.espressotest.basic.EspressoTestActivity
import org.hamcrest.Matchers.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EspressoTestActivityTest {


    @Test
    fun should_show_output1_when_input1() {

        ActivityScenario.launch(EspressoTestActivity::class.java)

        onView(withId(R.id.input1)).perform(typeText("aaaa"))


        onView(withId(R.id.output1)).check(matches(withText("aaaa")))

    }

    @Test
    fun should_show_output2_when_input2() {

        ActivityScenario.launch(EspressoTestActivity::class.java)

        onView(withId(R.id.input2)).perform(typeText("bbbb"))

        onView(withId(R.id.output2)).check(matches(withText("bbbb")))

    }

    @Test
    fun should_show_output3_when_input3() {

        ActivityScenario.launch(EspressoTestActivity::class.java)

        onView(withId(R.id.input3)).perform(typeText("cccc"))



        onView(withId(R.id.output3)).check(matches(withText("cccc")))

    }

    @Test
    fun should_show_output4_when_input4() {

        ActivityScenario.launch(EspressoTestActivity::class.java)

        onView(withId(R.id.input4)).perform(typeText("dddd"))



        onView(withId(R.id.output4)).check(matches(withText("dddd")))

    }

    @Test
    fun should_show_output_all_when_input_all() {

        ActivityScenario.launch(EspressoTestActivity::class.java)

        onView(withId(R.id.input4)).perform(typeText("d"))
        onView(withId(R.id.input3)).perform(typeText("c"))
        onView(withId(R.id.input2)).perform(typeText("b"))
        onView(withId(R.id.input1)).perform(typeText("a"))



        onView(withId(R.id.output4)).check(matches(withText("d")))
        onView(withId(R.id.output3)).check(matches(withText("c")))
        onView(withId(R.id.output2)).check(matches(withText("b")))
        onView(withId(R.id.output1)).check(matches(withText("a")))
    }


    @Test
    fun should_show_click_input1_when_click_button1() {

        ActivityScenario.launch(EspressoTestActivity::class.java)

        onView(withId(R.id.button1)).perform(click())



        onView((withId(R.id.click_output1))).check(matches(withText("button1")))
    }

    @Test
    fun should_show_click_input2_when_click_button2() {
        ActivityScenario.launch(EspressoTestActivity::class.java)

        onView(withId(R.id.button2)).perform(click())



        onView((withId(R.id.click_output2))).check(matches(withText("button2")))
    }

    @Test
    fun should_show_click_input3_when_click_button3() {
        ActivityScenario.launch(EspressoTestActivity::class.java)

        onView(withId(R.id.button3)).perform(click())



        onView((withId(R.id.click_output3))).check(matches(withText("button3")))
    }

    @Test
    fun should_show_enable_state_button_when_not_enable_state_and_click() {
        ActivityScenario.launch(EspressoTestActivity::class.java)

        onView(withId(R.id.enable_button)).perform(click())



        onView((withId(R.id.enable_button))).check(matches(isEnabled()))
    }


    @Test
    fun should_show_not_enable_state_button_when_enable_state_and_click() {
        ActivityScenario.launch(EspressoTestActivity::class.java)

        onView(withId(R.id.not_enable_button)).perform(click())



        onView((withId(R.id.not_enable_button))).check(matches(not(isEnabled())))
    }


    @Test
    fun should_show_ui_test_activity_when_click_start_ui_test_button() {
        ActivityScenario.launch(EspressoTestActivity::class.java)

        onView(withId(R.id.start_ui_test_button)).perform(click())

        onView(allOf(withText(containsString("click")))).check(matches(isDisplayed()))
    }

    @Test
    fun should_show_dialog_when_click_start_dialog_button() {
        ActivityScenario.launch(EspressoTestActivity::class.java)

        onView(withId(R.id.start_dialog_button)).perform(click())

        onView(allOf(withText(containsString("startDialogButton눌림")))).check(matches(isDisplayed()))
    }

    @Test
    fun should_show_new_activity_when_click_dialog_ok() {
        ActivityScenario.launch(EspressoTestActivity::class.java)

        onView(withId(R.id.start_dialog_and_next_activity)).perform(click())

        onView(allOf(withText(containsString("OK")))).check(matches(isDisplayed())).perform(click())

        onView(allOf(withText(containsString("click")))).check(matches(isDisplayed()))
    }

}