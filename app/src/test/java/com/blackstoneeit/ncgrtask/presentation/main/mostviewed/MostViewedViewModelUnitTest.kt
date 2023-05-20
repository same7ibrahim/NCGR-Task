package com.blackstoneeit.ncgrtask.presentation.main.mostviewed

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.blackstoneeit.ncgrtask.data.model.response.MostViewedResponse
import com.blackstoneeit.ncgrtask.data.model.response.Results
import com.blackstoneeit.ncgrtask.domain.MostPopularUseCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner
import retrofit2.Response
import java.util.concurrent.CountDownLatch

@RunWith(RobolectricTestRunner::class)
open class MostViewedViewModelUnitTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockRepository: MostPopularUseCase
    private lateinit var mostViewedViewModel: MostViewedViewModel

    @Before
    fun setup() {
        mockRepository = mock()
        mostViewedViewModel = MostViewedViewModel(mockRepository)
    }

    @Test
    fun testGetMostPopularCalled_WhenViewModelInitialize(): Unit = runBlocking {

        val apiKey = "3OXaIED4UHr0oyy0ufuAXTBQGrVlRCpZ"
        val period = "1"

        verify(
            mockRepository,
            atLeastOnce()
        ).getMostPopular(period, apiKey)
    }

    @Test
    fun testVerifyArticlesListIsEmpty_OnSuccessResponseEmpty(): Unit = runBlocking {

        val apiKey = "3OXaIED4UHr0oyy0ufuAXTBQGrVlRCpZ"
        val period = "1"

        val mockResponse = Response.success(MostViewedResponse(results = arrayListOf()))
        `when`(mockRepository.getMostPopular(period, apiKey))
            .thenReturn(flow {
                emit(mockResponse)
            })

        mostViewedViewModel.articlesList.assertLiveData {
            assert(it.isEmpty())
        }
    }

    @Test
    fun testArticlesListValueIsEqualResponseFromUseCase_OnSuccessResponseWithData(): Unit = runBlocking {

        val apiKey = "3OXaIED4UHr0oyy0ufuAXTBQGrVlRCpZ"
        val period = "1"

        val dummyTestData = Results(
            uri = "dummy_uri",
            url = "dummy_url",
            id = "dummy_id",
            assetId = "dummy_asset_id",
            source = "dummy_source",
            publishedDate = "dummy_published_date",
            updated = "dummy_updated",
            section = "dummy_section",
            subsection = "dummy_subsection",
            nytdsection = "dummy_nytdsection",
            adxKeywords = "dummy_adx_keywords",
            column = "dummy_column",
            byline = "dummy_byline",
            type = "dummy_type",
            title = "dummy_title",
            abstract = "dummy_abstract",
            desFacet = arrayListOf("facet1", "facet2"),
            orgFacet = arrayListOf("org1", "org2"),
            perFacet = arrayListOf("person1", "person2"),
            geoFacet = arrayListOf("location1", "location2"),
            media = arrayListOf(),
            etaId = 123
        )
        val mockResponse = Response.success(
            MostViewedResponse(
                results = arrayListOf(
                    dummyTestData
                )
            )
        )
        `when`(mockRepository.getMostPopular(period, apiKey))
            .thenReturn(flow {
                emit(mockResponse)
            })

        mostViewedViewModel.articlesList.assertLiveData {
            assert(it[0].equals(
                dummyTestData
            ))
        }
    }

}

@Throws(InterruptedException::class)
fun <T> LiveData<T>.assertLiveData(onDataReceived: (T) -> Unit) {
    val liveData: LiveData<T> = this
    val data = arrayOfNulls<Any>(1)
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            if (o != null) {
                data[0] = o
                latch.countDown()
                liveData.removeObserver(this)
                onDataReceived(o)
            }
        }
    }
}