package com.example.jet_ecommerce.ui.features.main.home

import androidx.lifecycle.LiveData
import com.example.domain.features.category.model.Category
import kotlinx.coroutines.flow.StateFlow

sealed class HomeContract {
    interface ViewModel{
        val categoriesStates : StateFlow<State>
        val categoriesEvents : LiveData<Event>
        fun invokeCategoriesAction(action : Action)
    }

    sealed class State{
        object Idle : State()
        class Error(val message:String) : State()
        class CategorySuccess(val categories:List<Category?>?) : State()
        object Loading : State()
    }

    sealed class  Action{
        object LoadCategories : Action()
        class CategoryClick(val category: Category) : Action()
    }

    sealed class Event{
        class NavigateToSubCategories(category : Category) : Event()
    }
}