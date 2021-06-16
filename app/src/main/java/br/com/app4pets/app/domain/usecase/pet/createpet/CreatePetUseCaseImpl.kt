package br.com.app4pets.app.domain.usecase.pet.createpet

import br.com.app4pets.app.domain.repository.PetsRepository

class CreatePetUseCaseImpl(
    private val petsRepository: PetsRepository
): CreatePetUseCase {
    override suspend fun createPet() {

    }
}