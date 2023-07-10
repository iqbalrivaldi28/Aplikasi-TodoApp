package com.dicoding.todoapp.ui.list

import androidx.core.content.MimeTypeFilter.matches
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.add.AddTaskActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

//TODO 16 : Write UI test to validate when user tap Add Task (+), the AddTaskActivity displayed
@RunWith(AndroidJUnit4ClassRunner::class)
class TaskActivityTest {

    @Before
    fun setup(){
        ActivityScenario.launch(TaskActivity::class.java)
    }

    @Test
    fun assertGetCircumference() {
        Espresso.onView(ViewMatchers.withId(R.id.fab))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.add_ed_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


//    @Before
//    fun start() {
//        ActivityScenario.launch(TaskActivity::class.java)
//        Intents.init()
//    }
//
//    @Test
//    fun validateTapAddOnTaskActivity() {
//        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click())
//        Intents.intended(IntentMatchers.hasComponent(AddTaskActivity::class.java.name))
//    }

}