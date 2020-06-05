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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ArtworksTabTest {

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
    public void artworksTabTest() {
        ViewInteraction tabView = onView(
                allOf(withContentDescription("Artworks"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabLayout),
                                        0),
                                1),
                        isDisplayed()));
        tabView.perform(click());

        ViewInteraction actionBar$Tab = onView(
                allOf(withContentDescription("Museum"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabLayout),
                                        0),
                                0),
                        isDisplayed()));
        actionBar$Tab.check(matches(isDisplayed()));

        ViewInteraction actionBar$Tab2 = onView(
                allOf(withContentDescription("Rooms"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabLayout),
                                        0),
                                2),
                        isDisplayed()));
        actionBar$Tab2.check(matches(isDisplayed()));

        ViewInteraction imageView = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.main_toolbar),
                                        2),
                                1),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.search), withContentDescription("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.main_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frameLayout),
                                        1),
                                0),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));
    }
}
