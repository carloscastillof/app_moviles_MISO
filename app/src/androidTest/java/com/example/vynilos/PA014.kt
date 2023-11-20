package com.example.vynilos


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
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
class PA014 {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun pA014() {
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

        val materialButton2 = onView(
            allOf(
                withId(R.id.agregaralbumbtn), withText("Agregar un álbum"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val materialButton3 = onView(
            allOf(
                withId(R.id.agregaralbumbtn), withText("Agregar un álbum"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())

        val textInputEditText = onView(
            allOf(
                withId(R.id.etTitulo),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.etTituloLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("Prueba"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.etDescripcion),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.etDescripcionLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(replaceText("Prueba"), closeSoftKeyboard())

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.etFechaLanzamiento),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.etFechaLanzamientoLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(replaceText("2020-01-01"), closeSoftKeyboard())

        val textInputEditText4 = onView(
            allOf(
                withId(R.id.etGenero),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.etGeneroLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText4.perform(replaceText("Salsa"), closeSoftKeyboard())

        val textInputEditText5 = onView(
            allOf(
                withId(R.id.etSelloDiscografico),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.etSelloDiscograficoLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText5.perform(replaceText("EMI"), closeSoftKeyboard())

        val textInputEditText6 = onView(
            allOf(
                withId(R.id.etCoverImageUrl),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.etCoverImageUrlLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText6.perform(replaceText("url"), closeSoftKeyboard())

        val materialButton4 = onView(
            allOf(
                withId(R.id.btnGuardar), withText("Guardar"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        7
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton4.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.title), withText("Prueba"),
                withParent(
                    allOf(
                        withId(R.id.principal),
                        withParent(IsInstanceOf.instanceOf(android.widget.RelativeLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Prueba")))
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
