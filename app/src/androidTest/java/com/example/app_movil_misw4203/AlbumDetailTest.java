package com.example.app_movil_misw4203;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class AlbumDetailTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAlbumDetail() {
        // Abre el icono de navegación de la izquierda
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());

        // Encontrar y hacer clikc en el elemento que corresponde a los albumes ID nav_home.
        ViewInteraction navSlideshowItem = onView(ViewMatchers.withId(R.id.nav_home))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.click());

        // Haga click en la primera imagen del RecyclerView de Albumes
        onView(withId(R.id.albumRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,
                        ViewActions.click()));
    }
}
