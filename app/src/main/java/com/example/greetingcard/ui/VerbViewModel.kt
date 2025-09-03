package com.example.greetingcard.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.greetingcard.data.VerbRepository

class VerbViewModel(private val verbRepository: VerbRepository = VerbRepository()) : ViewModel() {

    var verb by mutableStateOf("")
        private set

    var result by mutableStateOf("")
        private set

    val conjugationOptions = listOf("Plain Form (Present)", "Masu Form (Present)", "Te Form", "Nai Form (Negative)")

    var selectedConjugationType by mutableStateOf(conjugationOptions[0])
        private set

    var isDropdownExpanded by mutableStateOf(false)
        private set

    fun onVerbChange(newVerb: String) {
        verb = newVerb
    }

    fun onConjugationTypeChange(newConjugationType: String) {
        selectedConjugationType = newConjugationType
        isDropdownExpanded = false
    }

    fun onDropdownDismiss() {
        isDropdownExpanded = false
    }

    fun onDropdownExpand() {
        isDropdownExpanded = true
    }

    fun conjugate() {
        if (verb.isNotBlank()) {
            result = verbRepository.conjugate(verb, selectedConjugationType)
        } else {
            result = "Please enter a verb."
        }
    }
}
