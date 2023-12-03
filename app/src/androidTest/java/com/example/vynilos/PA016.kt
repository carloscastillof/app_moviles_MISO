package com.example.vynilos


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class PA016 {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun pA016() {
        val materialButton = onView(
            allOf(
                withId(R.id.btn_coleccionista), withText("Coleccionista"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        materialButton.perform(scrollTo(), click())

        val recyclerView = onView(
            allOf(
                withId(R.id.rvAlbums),
                childAtPosition(
                    withClassName(`is`("android.widget.LinearLayout")),
                    2
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        val materialButton2 = onView(
            allOf(
                withId(R.id.btn_tie_track_to_album), withText("Asociar canción al álbum"),
                childAtPosition(
                    allOf(
                        withId(R.id.tracksContent),
                        childAtPosition(
                            withId(R.id.tabContent),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val textInputEditText = onView(
            allOf(
                withId(R.id.txt_name),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("com.google.android.material.textfield.TextInputLayout")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("Cancion de prueba"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.txt_duration),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("com.google.android.material.textfield.TextInputLayout")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(replaceText("3:00"), closeSoftKeyboard())

        val button = onView(
            allOf(
                withId(R.id.btn_create_tie_track_to_album), withText("ASOCIAR CANCIÓN"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val materialButton3 = onView(
            allOf(
                withId(R.id.btn_create_tie_track_to_album), withText("Asociar Canción"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
