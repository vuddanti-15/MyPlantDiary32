package app.plantdiary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.plantdiary.dto.Plant
import app.plantdiary.service.PlantService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class PlantTests {

    @get:Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var plantService: PlantService
    private var allPlants: List<Plant> = emptyList()

    @Test
    fun `Given plant data are available when I search for Redbud then I should receive Cercis canadensis`() = runBlocking {
        givenPlantServiceIsInitialized()
        whenPlantDataAreReadAndParsed()
        thenThePlantCollectionShouldContainCercisCanadensis()
    }

    private fun givenPlantServiceIsInitialized() {
        plantService = PlantService()
    }

    private suspend fun whenPlantDataAreReadAndParsed() {
        allPlants = plantService.fetchPlants()
    }

    private fun thenThePlantCollectionShouldContainCercisCanadensis() {
        assertNotNull(allPlants)
        assertTrue(allPlants.isNotEmpty())
        val containsCercisCanadensis = allPlants.any { it.genus.equals("Cercis", ignoreCase = true) && it.species.equals("canadensis", ignoreCase = true) }
        assertTrue(containsCercisCanadensis)
    }
}
