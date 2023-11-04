package com.example.vynilos

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith


import org.junit.Assert.*

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.core.app.ActivityScenario
import org.junit.Rule

@RunWith(AndroidJUnit4::class)

class AlbumsTest {
    @Test
    fun testButtonClick() {

        val scenario = ActivityScenario.launch(MainActivity::class.java)

        // Obtén el contexto de la aplicación bajo prueba.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        // Verifica que el nombre del paquete sea el correcto.
        assertEquals("com.example.vynilos", appContext.packageName)

        // Realiza la prueba de presionar un botón.
        Espresso.onView(ViewMatchers.withId(R.id.btn_coleccionista))
            .perform(ViewActions.click())

//        // Realiza la prueba de presionar el segundo botón.
//        Espresso.onView(ViewMatchers.withId(R.id.button2))
//            .perform(ViewActions.click())
        // Finaliza la actividad al final de la prueba
        scenario.close()
    }
}