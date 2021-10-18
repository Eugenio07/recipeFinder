package use

import com.example.data.repository.CountriesRepository
import com.example.domain.Either
import com.example.use.CountryUseCases
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import mokedDomain.mockedCountry
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CountryUseCaseTest {
    @Mock
    private lateinit var countriesRepository: CountriesRepository
    private lateinit var countryUseCases: CountryUseCases

    @Before
    fun setUp(){
        countryUseCases = CountryUseCases(countriesRepository)
    }

    @Test
    fun `get all countries`(){
        runBlocking {
            val countries = Either.Right(listOf(mockedCountry))
            whenever(countriesRepository.getAllCountries()).thenReturn(countries)

            val result = countryUseCases.getAllCountries()

            assertEquals(countries, result)
        }
    }


}