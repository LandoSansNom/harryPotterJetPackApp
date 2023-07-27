package com.cherlanmiche.hpjetpackapp.data.repository

import com.cherlanmiche.harrypotter.data.remote.harryPotterCall
import com.itc.kotlinknightsproject.data.repository.RepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class RepositoryImplTest{
  lateinit var repository: Repository

  @Mock
  lateinit var harryPotterCall: harryPotterCall

  @Before
  fun setup(){
   MockitoAnnotations.openMocks(this)
   repository = RepositoryImpl(harryPotterCall)
  }

 @Test
 fun getCharacterByIdTest() = runTest{

  val characterModel =  CharacterModelSample.getSampleCharacter()

  Mockito.`when`(harryPotterCall.getCharacterById("character_id_123")).thenReturn(characterModel)

  assertEquals(repository.getCharacterById("character_id_123"), characterModel)
 }

 }