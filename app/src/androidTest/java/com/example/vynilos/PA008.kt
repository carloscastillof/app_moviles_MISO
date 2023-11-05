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
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class PA008 {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun pM008() {
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
        Thread.sleep(500)
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
        Thread.sleep(500)
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val tabView = onView(
            allOf(
                withContentDescription("Tracks"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        Thread.sleep(500)
        tabView.perform(click())

        val tabView2 = onView(
            allOf(
                withContentDescription("Comentarios"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabLayout),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        Thread.sleep(500)
        tabView2.perform(click())

        val tabView3 = onView(
            allOf(
                withContentDescription("Tracks"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        Thread.sleep(500)
        tabView3.perform(click())

        val appCompatImageView = onView(
            allOf(
                withId(R.id.left_icon),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.toolbar),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        Thread.sleep(500)
        appCompatImageView.perform(click())

        val appCompatImageView2 = onView(
            allOf(
                withId(R.id.left_icon), withContentDescription("GOBACKROWIMAGE"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.toolbar),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        Thread.sleep(500)
        appCompatImageView2.perform(click())

        val materialButton2 = onView(
            allOf(
                withId(R.id.btn_usuario), withText("Usuario"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        materialButton2.perform(scrollTo(), click())

        val recyclerView2 = onView(
            allOf(
                withId(R.id.rvAlbums),
                childAtPosition(
                    withClassName(`is`("android.widget.LinearLayout")),
                    2
                )
            )
        )
        Thread.sleep(500)
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        val tabView4 = onView(
            allOf(
                withContentDescription("Comentarios"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabLayout),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        Thread.sleep(500)
        tabView4.perform(click())

        val tabView5 = onView(
            allOf(
                withContentDescription("Tracks"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        Thread.sleep(500)
        tabView5.perform(click())

        val textView = onView(
            allOf(
                withText("TRACKS"),
                withParent(
                    allOf(
                        withContentDescription("Tracks"),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        Thread.sleep(500)
        textView.check(matches(withText("TRACKS")))
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
