package com.example.study_tdd.espressotest.interfacetdd

class InterfaceExam {

    private lateinit var petService: PetService

    fun getPetList(petService: PetService) : String{

        var getAllList = ""

        this.petService = petService
        for (pet in petService.listPets()) {
            getAllList+=pet.getSort()
        }

        return getAllList
    }
}


interface Pet {
    fun getSort(): String
}

class Dog(private val age: Int, private val sort: String) : Pet {
    override fun getSort() = sort
}

interface PetService {

    fun listPets(): List<Pet>
}