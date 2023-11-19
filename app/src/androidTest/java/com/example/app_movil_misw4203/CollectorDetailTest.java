package com.example.app_movil_misw4203;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static java.util.regex.Pattern.matches;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;

public class CollectorDetailTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testCollectorDetail() {
        // Abre el icono de navegación de la izquierda
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());

        // Encontrar y hacer clikc en el elemento que corresponde a los coleccionistas ID nav_slideshow.
        ViewInteraction navSlideshowItem = onView(withId(R.id.nav_slideshow))
                .check(ViewAssertions.matches(isDisplayed()))
                .perform(click());

        // Haga click en la primera imagen del RecyclerView de Coleccionistas
        onView(withId(R.id.collectionRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,
                        ViewActions.click()));
    }
}
