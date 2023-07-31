package com.cherlanmiche.hpjetpackapp.ui.screens

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cherlanmiche.hpjetpackapp.data.model.CharacterModel
import com.cherlanmiche.hpjetpackapp.data.repository.Repository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: Repository

    private lateinit var viewModel: CharactersViewModel

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined) // Set the main dispatcher to a TestCoroutineDispatcher
        repository = mockk()
        viewModel = CharactersViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the main dispatcher after the test
    }

    @Test
    fun testGetAllCharactersSuccess() {
        // Given
        val characterList = listOf(
            CharacterModel(name = "Character 1"),
            CharacterModel(name = "Character 2")
        )
        val repository = mockk<Repository>()
        coEvery { repository.getAllCharcters() } returns characterList
        val viewModel = CharactersViewModel(repository)

        val observer = mockk<Observer<List<CharacterModel>>>(relaxed = true)
        viewModel.characters.observeForever(observer)

        // When
        viewModel.getAllCharacters()

        // Then
        verify { observer.onChanged(characterList) }
    }
}
