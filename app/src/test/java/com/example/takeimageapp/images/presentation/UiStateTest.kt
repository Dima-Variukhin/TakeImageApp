package com.example.takeimageapp.images.presentation

import junit.framework.TestCase.assertEquals
import org.junit.Test

class UiStateTest {

    @Test
    fun test_success() {
        val success = UiState.Success
        val inputLayout = FakeInputLayout()

        success.apply(inputLayout)

        assertEquals(true, inputLayout.changeIsHintEnabledCalled)
        assertEquals(false, inputLayout.changeIsHintEnabledArgument)
        assertEquals(true, inputLayout.changeErrorEnabledCalled)
        assertEquals(false, inputLayout.changeErrorEnableArgument)
    }

    @Test
    fun test_show_error() {
        val showError = UiState.ShowError("some error")
        val inputLayout = FakeInputLayout()

        showError.apply(inputLayout)

        assertEquals(false, inputLayout.changeIsHintEnabledCalled)
        assertEquals(true, inputLayout.changeErrorEnabledCalled)
        assertEquals(true, inputLayout.changeErrorEnableArgument)
        assertEquals(true, inputLayout.showErrorCalled)
        assertEquals("some error", inputLayout.showErrorArgument)
    }

    @Test
    fun test_clear_error() {
        val clearError = UiState.ClearError()
        val inputLayout = FakeInputLayout()

        clearError.apply(inputLayout)

        assertEquals(false, inputLayout.changeIsHintEnabledCalled)
        assertEquals(true, inputLayout.changeErrorEnabledCalled)
        assertEquals(false, inputLayout.changeErrorEnableArgument)
        assertEquals(true, inputLayout.showErrorCalled)
        assertEquals("", inputLayout.showErrorArgument)
    }


    private class FakeInputLayout : CustomTextInputLayout {
        var changeErrorEnabledCalled = false
        var changeErrorEnableArgument = false

        var changeIsHintEnabledCalled = false
        var changeIsHintEnabledArgument = false

        var showErrorCalled = false
        var showErrorArgument: String = ""

        override fun changeErrorEnabled(value: Boolean) {
            changeErrorEnabledCalled = true
            changeErrorEnableArgument = value
        }

        override fun changeIsHintEnabled(value: Boolean) {
            changeIsHintEnabledCalled = true
            changeIsHintEnabledArgument = value
        }

        override fun showError(errorMessage: String) {
            showErrorCalled = true
            showErrorArgument = errorMessage
        }
    }
}