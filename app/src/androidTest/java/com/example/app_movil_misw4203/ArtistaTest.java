package com.example.app_movil_misw4203;

import static androidx.test.espresso.Espresso.onView;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;

public class ArtistaTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testCollectorMenuItem() {
        // Abre el icono de navegación de la izquierda
        onView(ViewMatchers.withId(R.id.drawer_layout)).perform(DrawerActions.open());

        // Encontrar y hacer clikc en el elemento que corresponde a los artistas ID nav_gallery.
        ViewInteraction navSlideshowItem = onView(ViewMatchers.withId(R.id.nav_gallery))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.click());
    }
}
