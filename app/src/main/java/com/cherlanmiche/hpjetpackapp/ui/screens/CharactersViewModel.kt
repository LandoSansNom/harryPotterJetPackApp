package com.cherlanmiche.hpjetpackapp.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherlanmiche.hpjetpackapp.data.model.CharacterModel
import com.cherlanmiche.hpjetpackapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    private val _characters = MutableLiveData<List<CharacterModel>>()
    val characters : LiveData<List<CharacterModel>> = _characters

    init {
        getAllCharacters()
    }

    fun getAllCharacters() = viewModelScope.launch {
        try {
            var result = repository.getAllCharcters()
            _characters.value = result
        } catch (e: Exception) {
            e.printStackTrace()
            _characters.value = listOf()
        }
    }
}