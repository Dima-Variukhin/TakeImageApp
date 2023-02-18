package com.example.takeimageapp.images.presentation

sealed class UiState {
    abstract fun apply(
        textInputLayout: CustomTextInputLayout,
    )

    object Success : UiState() {
        override fun apply(textInputLayout: CustomTextInputLayout) {
            with(textInputLayout) {
                changeIsHintEnabled(false)
                changeErrorEnabled(false)
            }
        }
    }

    abstract class AbstractError(
        private val errorMessage: String,
        private val errorEnabled: Boolean
    ) : UiState() {
        override fun apply(textInputLayout: CustomTextInputLayout) =
            with(textInputLayout) {
                changeErrorEnabled(errorEnabled)
                showError(errorMessage)
            }
    }

    data class ShowError(private val text: String) : AbstractError(text, true)
    class ClearError : AbstractError("", false)
}