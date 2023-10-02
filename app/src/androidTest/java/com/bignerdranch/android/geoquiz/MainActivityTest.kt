package com.bignerdranch.android.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = launch(MainActivity::class.java)
    }

    @After
    fun teardown() {
        scenario.close()
    }

    @Test
    fun showsFirstQuestionOnLaunch() {
        onView(withId(R.id.question_text_view))
            .check(matches(withText(R.string.question_australia)))
    }

    @Test
    fun showsSecondQuestionAfterNextPress() {
        onView(withId(R.id.next_button)).perform(click())
        onView(withId(R.id.question_text_view)).check(matches(withText(R.string.question_oceans)))
    }

    @Test
    fun handlesActivityRecreation() {
        onView(withId(R.id.next_button)).perform(click())
        scenario.recreate()
        onView(withId(R.id.question_text_view)).check(matches(withText(R.string.question_oceans)))
    }
    @Test
    fun handlesTest1() {
        val savedStateHandle = SavedStateHandle(mapOf(CURRENT_INDEX_KEY to 5))
        val quizViewModel = QuizViewModel(savedStateHandle)
        onView(withId(R.id.cheat_button)).perform(click())
        onView(withId(R.id.show_answer_button)).perform(click())
        pressBack()

        onView(withId(R.id.next_button)).perform(click())
        scenario.recreate()
        onView(withId(R.id.true_button)).perform(click())
        onView(withText(R.string.judgment_toast)).check(doesNotExist())
    }
}
