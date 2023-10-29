package com.example.jet_ecommerce.ui.features.categories

import androidx.lifecycle.LiveData
import com.example.domain.features.category.model.Category
import kotlinx.coroutines.flow.StateFlow
// Events- > When ViewModel Wants to send data to Fragment/Activity
// States- > When ViewModel Wants to send data to Fragment/Activity

// Actions -> When Activity/Fragment wants to send Action To ViewModel
class CategoriesContract {
    interface ViewModel{
        val states : StateFlow<State>
        val events : LiveData<Event>
        fun invokeAction(action : Action)
    }

    sealed class State{
        class Error(val message:String) : State()
        class Success(val categories:List<Category?>?) : State()
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