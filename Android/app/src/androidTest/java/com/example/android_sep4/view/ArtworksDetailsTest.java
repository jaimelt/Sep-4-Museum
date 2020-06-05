package com.example.android_sep4.view;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.android_sep4.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ArtworksDetailsTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @Test
    public void open_room_A1() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.room_a1), withText("A1"),
                        childAtPosition(
                                allOf(withId(R.id.museum_layout),
                                        childAtPosition(
                                                withId(R.id.frameLayout),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatTextView.perform(click());

        onView(withId(R.id.room_A1_activity)).check(matches(isDisplayed()));
    }

    @Test
    public void select_artwork_1() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.room_a1), withText("A1"),
                        childAtPosition(
                                allOf(withId(R.id.museum_layout),
                                        childAtPosition(
                                                withId(R.id.frameLayout),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatTextView.perform(click());

        onView(withId(R.id.room_A1_activity)).check(matches(isDisplayed()));

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.artwork_place_1), withText("1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatTextView2.perform(click());

    }

    @Test
    public void return_to_main_activity() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.room_a1), withText("A1"),
                        childAtPosition(
                                allOf(withId(R.id.museum_layout),
                                        childAtPosition(
                                                withId(R.id.frameLayout),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.artwork_place_1), withText("1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        pressBack();
    }
}
