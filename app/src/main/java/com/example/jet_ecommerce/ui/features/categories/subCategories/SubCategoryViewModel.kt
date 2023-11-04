package com.example.jet_ecommerce.ui.features.categories.subCategories

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.domain.features.category.model.Category
import com.example.domain.features.subCategories.usecase.GetSubCategoriesOnCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SubCategoryViewModel @Inject constructor(
    val getSubCategoriesOnCategoryUseCase: GetSubCategoriesOnCategoryUseCase,
    val savedStateHandle: SavedStateHandle
) : ViewModel(), SubCategoriesContract.ViewModel {
    private val _states =
        MutableStateFlow<SubCategoriesContract.State>(SubCategoriesContract.State.Loading)
    private val _events = MutableStateFlow<SubCategoriesContract.Event>(
        SubCategoriesContract.Event.NavigateToProductsOfCategory(Category())
    )
    override val states: StateFlow<SubCategoriesContract.State> = _states
    override val events: StateFlow<SubCategoriesContract.Event> = _events

    override fun invokeAction(action: SubCategoriesContract.Action) {
        when (action) {
            is SubCategoriesContract.Action.LoadSubCategories -> loadSubCategories()
            is SubCategoriesContract.Action.CategoryClick -> TODO()
            is SubCategoriesContract.Action.SubCategoryClick -> TODO()
        }
    }
//    val categoryId = savedStateHandle.get<Int>("category_id") ?: 0

    private fun loadSubCategories() {
        TODO("Not yet implemented")
    }

}