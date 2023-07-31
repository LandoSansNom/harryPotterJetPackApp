package com.cherlanmiche.hpjetpackapp.data.repository

import com.cherlanmiche.harrypotter.data.remote.harryPotterCall
import com.cherlanmiche.hpjetpackapp.data.model.CharacterModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val harryPotterCall: harryPotterCall,
) : Repository {

    override suspend fun getAllCharcters(): List<CharacterModel> {
        return harryPotterCall.getAllCharcters()

    }

    override suspend fun getCharacterById(id: String): CharacterModel {
        return harryPotterCall.getCharacterById(id)
    }

}