package com.example.study_tdd.espressotest.interfacetdd

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class InterfaceExamTest {


    @Mock
    lateinit var petService: PetService

    @Before
    fun setUp() {
        petService = Mockito.mock(PetService::class.java)
        Mockito.`when`(petService.listPets()).thenReturn(getPetList())
    }


    @Test
    fun `말티즈블독믹스푸들 이어야 한다`() {

        val interfaceExam = InterfaceExam()

        print(interfaceExam.getPetList(petService))

        assertEquals(interfaceExam.getPetList(petService), "말티즈불독믹스푸들")

    }

    private fun getPetList(): List<Pet> {
        return listOf(
            Dog(1, "말티즈"),
            Dog(2, "불독"),
            Dog(3, "믹스"),
            Dog(3, "푸들")
        )
    }
}