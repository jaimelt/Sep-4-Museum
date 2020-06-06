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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MuseumTabTest {

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
    public void museumTabTest() {
        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.appbar),
                        childAtPosition(
                                allOf(withId(R.id.main_content),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.main_toolbar),
                        childAtPosition(
                                allOf(withContentDescription("Brukenthal museum"),
                                        childAtPosition(
                                                withId(R.id.appbar),
                                                0)),
                                1),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));

        ViewInteraction linearLayoutCompat = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.main_toolbar),
                                childAtPosition(
                                        withContentDescription("Brukenthal museum"),
                                        1)),
                        2),
                        isDisplayed()));
        linearLayoutCompat.check(matches(isDisplayed()));

        ViewInteraction actionBar$Tab = onView(
                allOf(withContentDescription("Museum"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tab_layout),
                                        0),
                                0),
                        isDisplayed()));
        actionBar$Tab.check(matches(isDisplayed()));

        ViewInteraction actionBar$Tab2 = onView(
                allOf(withContentDescription("Artworks"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tab_layout),
                                        0),
                                1),
                        isDisplayed()));
        actionBar$Tab2.check(matches(isDisplayed()));

        ViewInteraction actionBar$Tab3 = onView(
                allOf(withContentDescription("Rooms"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tab_layout),
                                        0),
                                2),
                        isDisplayed()));
        actionBar$Tab3.check(matches(isDisplayed()));

        ViewInteraction viewPager = onView(
                allOf(withId(R.id.view_pager),
                        childAtPosition(
                                allOf(withId(R.id.frame_container),
                                        childAtPosition(
                                                withId(R.id.main_content),
                                                1)),
                                0),
                        isDisplayed()));
        viewPager.check(matches(isDisplayed()));

        ViewInteraction viewPager2 = onView(
                allOf(withId(R.id.view_pager),
                        childAtPosition(
                                allOf(withId(R.id.frame_container),
                                        childAtPosition(
                                                withId(R.id.main_content),
                                                1)),
                                0),
                        isDisplayed()));
        viewPager2.check(matches(isDisplayed()));
    }
}
