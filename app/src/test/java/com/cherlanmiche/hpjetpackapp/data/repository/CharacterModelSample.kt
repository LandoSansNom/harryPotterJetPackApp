package com.cherlanmiche.hpjetpackapp.data.repository

import com.cherlanmiche.hpjetpackapp.data.model.CharacterModel

object CharacterModelSample {

            val character1 = CharacterModel(
                actor = "Emma Watson",
                alive = true,
                alternateActors = listOf("Other Actor 1", "Other Actor 2"),
                alternateNames = listOf("Alternate Name 1", "Alternate Name 2"),
                ancestry = "Pure-blood",
                dateOfBirth = "15 April 1990",
                eyeColour = "Brown",
                gender = "Female",
                hairColour = "Brown",
                hogwartsStaff = false,
                hogwartsStudent = true,
                house = "Gryffindor",
                id = "character_id_123",
                image = "https://example.com/image.jpg",
                name = "Hermione Granger",
                patronus = "Otter",
                species = "Human",
                wizard = true,
                yearOfBirth = 1979
            )

    val character2 = CharacterModel(
        actor = "Cherlan-Miche Philippe",
        alive = true,
        alternateActors = listOf("Other Actor 1", "Other Actor 2"),
        alternateNames = listOf("Alternate Name 1", "Alternate Name 2"),
        ancestry = "Pure-blood",
        dateOfBirth = "15 April 1990",
        eyeColour = "Brown",
        gender = "Male",
        hairColour = "Brown",
        hogwartsStaff = false,
        hogwartsStudent = true,
        house = "Gryffindor",
        id = "character_id_124",
    )

    val character3 = CharacterModel(
        actor = "Pierre Guirand",
        alive = true,
        alternateActors = listOf("Other Actor 1", "Other Actor 2"),
        alternateNames = listOf("Alternate Name 1", "Alternate Name 2"),
        ancestry = "Pure-blood",
        dateOfBirth = "15 April 1990",
        eyeColour = "Brown",
        gender = "Male",
        hairColour = "Brown",
        hogwartsStaff = false,
        hogwartsStudent = true,
        house = "Gryffindor",
        id = "character_id_125",
    )

    val allCharacters: List<CharacterModel> = listOf(character1, character2,
        character3)


}