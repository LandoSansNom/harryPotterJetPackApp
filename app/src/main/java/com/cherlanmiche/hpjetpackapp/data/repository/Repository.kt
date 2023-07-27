package com.cherlanmiche.hpjetpackapp.data.repository

import com.cherlanmiche.hpjetpackapp.data.model.CharacterModel
import retrofit2.http.Path

interface Repository {
    suspend fun getAllCharcters(): List<CharacterModel>
    suspend fun getCharacterById(@Path("id") id: String): CharacterModel
}