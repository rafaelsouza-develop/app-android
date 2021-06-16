package br.com.app4pets.app.domain.usecase.pet.listpet

import br.com.app4pets.app.domain.repository.PetsRepository

class ListPetUseCaseImpl(
    private val petsRepository: PetsRepository
) : ListPetUseCase {

    override suspend fun listPet() {

    }
}