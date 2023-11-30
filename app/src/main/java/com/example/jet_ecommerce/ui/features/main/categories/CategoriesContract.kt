package com.example.jet_ecommerce.ui.features.main.categories

import com.example.domain.features.category.model.Category
import kotlinx.coroutines.flow.StateFlow

// Events- > When ViewModel Wants to send data to Fragment/Activity
// States- > When ViewModel Wants to send data to Fragment/Activity

// Actions -> When Activity/Fragment wants to send Action To ViewModel

class CategoriesContract {
    interface ViewModel {
        val states: StateFlow<State>
        val events: StateFlow<Event>
        fun invokeAction(action: Action)
    }

    sealed interface State {
        data class Error(val message: String) : State
        data class Success(val categories: List<Category?>?) : State
        data object Loading : State
    }

    sealed interface Action {
        data object LoadCategories : Action
        data class CategoryClick(val categoryId: String) : Action
        data class SubCategoryItemClick(val categoryId: String) : Action
    }

    sealed interface Event {
        data object Idle : Event
        data class NavigateToProductsList(val categoryId: String) : Event
    }
}