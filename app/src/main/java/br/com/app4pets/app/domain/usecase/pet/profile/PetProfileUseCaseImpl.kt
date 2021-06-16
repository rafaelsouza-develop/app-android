package br.com.app4pets.app.domain.usecase.pet.profile

import br.com.app4pets.app.domain.repository.PetsRepository

class PetProfileUseCaseImpl(
    private val petsRepository: PetsRepository
) : PetProfileUseCase {

    override suspend fun getPetProfile() {

    }
}