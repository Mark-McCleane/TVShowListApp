package com.example.tvshowapp

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.tvshowapp.MainActivity.TvShowViewModel
import model.TVShow
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TvShowInstrumentedTest {
    private lateinit var testTvShow: TVShow
    private lateinit var appContext: Context
    private lateinit var mTvShowViewModel: TvShowViewModel

    init {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        mTvShowViewModel = TvShowViewModel(ApplicationProvider.getApplicationContext())
        testTvShow = TVShow("Foo", "01,01,2022", 1, "Foo", "Foo",
            "Foo", "Foo", 1000.00, "Foo", 5.9, 100)
    }

    @Test
    fun Test1() {
//        Create Mock
        val tvShowsList = listOf<TVShow>(testTvShow)

//        Call function using moc
        mTvShowViewModel.addTvShow(testTvShow)

//         check result
        assertEquals(mTvShowViewModel.getTvShowListAsc(), tvShowsList)
    }
}