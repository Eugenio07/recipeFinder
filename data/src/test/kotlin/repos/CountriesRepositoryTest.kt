package repos

import com.example.data.repository.CountriesRepository
import com.example.data.repository.PermissionCheck
import com.example.data.repository.PermissionCheck.Permission.*
import com.example.data.source.CountriesDataSource
import com.example.data.source.LocalDataSource
import com.example.domain.Either
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import mockedDomain.mockedCountry
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CountriesRepositoryTest {
    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var countriesDataSource: CountriesDataSource

    @Mock
    lateinit var permissionChecker: PermissionCheck

    private lateinit var countriesRepository: CountriesRepository

    @Before
    fun setUp() {
        countriesRepository =
            CountriesRepository(localDataSource, countriesDataSource, permissionChecker)
    }

    @Test
    fun `get all countries from DB`() {
        runBlocking {
            val countries = listOf(mockedCountry)
            whenever(localDataSource.countryListIsEmpty()).thenReturn(false)
            whenever(localDataSource.getCountryList()).thenReturn(countries)

            val result = countriesRepository.getAllCountries() as Either.Right

            Assert.assertEquals(countries, result.r)
        }
    }

    @Test
    fun `get all countries from remote and failed connection`() {
        runBlocking {
            whenever(localDataSource.countryListIsEmpty()).thenReturn(true)
            whenever(countriesDataSource.getAllCountries()).thenReturn(Either.Left("Connection failure"))

            val result = countriesRepository.getAllCountries() as Either.Left

            Assert.assertEquals("Connection failure", result.l)
        }
    }

    @Test
    fun `get all countries from remote`() {
        runBlocking {
            val countries = listOf(mockedCountry)
            whenever(localDataSource.countryListIsEmpty()).thenReturn(true)
            whenever(countriesDataSource.getAllCountries()).thenReturn(Either.Right(countries))
            whenever(localDataSource.getCountryList()).thenReturn(countries)

            val result = countriesRepository.getAllCountries() as Either.Right

            verify(localDataSource).saveCountryList(countries)
            Assert.assertEquals(countries, result.r)
        }
    }

    @Test
    fun `get location`() {
        runBlocking {
            val country = mockedCountry.strArea
            whenever(
                permissionChecker.request(
                    arrayListOf(
                        COARSE_LOCATION,
                        FINE_LOCATION
                    )
                )
            ).thenReturn(Pair(true, second = false))
            whenever(countriesDataSource.getLocation()).thenReturn(country)
            whenever(localDataSource.isInRecipeList(country!!)).thenReturn(true)

            val result = countriesRepository.getLocation()

            Assert.assertEquals(country, result)
        }
    }

    @Test
    fun `get location permission denied`() {
        runBlocking {
            whenever(
                permissionChecker.request(
                    arrayListOf(
                        COARSE_LOCATION,
                        FINE_LOCATION
                    )
                )
            ).thenReturn(Pair(false, second = false))

            val result = countriesRepository.getLocation()

            Assert.assertEquals("Unknown", result)
        }
    }

    @Test
    fun `get location country is not in recipe list`() {
        runBlocking {
            val country = mockedCountry.strArea
            whenever(
                permissionChecker.request(
                    arrayListOf(
                        COARSE_LOCATION,
                        FINE_LOCATION
                    )
                )
            ).thenReturn(Pair(true, second = false))
            whenever(countriesDataSource.getLocation()).thenReturn(country)
            whenever(localDataSource.isInRecipeList(country!!)).thenReturn(false)

            val result = countriesRepository.getLocation()

            Assert.assertEquals("Unknown", result)
        }
    }
}