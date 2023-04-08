package com.example.tvshowapp.MainActivity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tvshowapp.MainActivity.utils.Status
import com.example.tvshowapp.MainActivity.model.TVShow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TvShowViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setup() {
        viewModel = TvShowViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun `addTvShow item with empty fields returns error`() {
        var tvShow: TVShow = TVShow("", "", 0,"","",
            "","",0.0,"",0.0,0)
        viewModel.addTvShow(tvShow)
        val value = viewModel.insertTvShowStatus.getOrAwaitValueTest()
        assert(value.getContentIfNotHandled()?.status == Status.ERROR)
    }

}