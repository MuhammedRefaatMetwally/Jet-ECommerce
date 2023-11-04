package com.example.jet_ecommerce.ui.features.categories.subCategories

import com.example.domain.features.category.model.Category
import com.example.domain.features.subCategories.model.SubCategory
import kotlinx.coroutines.flow.StateFlow

class SubCategoriesContract {
    interface ViewModel {
        val states: StateFlow<State>
        val events: StateFlow<Event>
        fun invokeAction(action: Action)
    }

    sealed class State() {
        object Loading : State()
        class Error(val message: String) : State()
        class Success(val subCategoriesList: List<SubCategory?>?) : State()
    }

    sealed class Action() {
        object LoadSubCategories : Action()
        class CategoryClick(category: Category) : Action()
        class SubCategoryClick(subCategory: SubCategory) : Action()
    }

    sealed class Event() {
        class NavigateToProductsOfCategory(category: Category) : Event()
        class NavigateToProductsOfSubCategory(subCategory: SubCategory) : Event()
    }
}