package com.example.jet_ecommerce.ui.features.main.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.domain.features.category.model.Category
import com.example.domain.features.subCategories.model.SubCategory
import com.example.jet_ecommerce.R
import com.example.jet_ecommerce.ui.components.CustomAlertDialog
import com.example.jet_ecommerce.ui.components.CustomLoadingWidget
import com.example.jet_ecommerce.ui.components.CustomTopBar
import com.example.jet_ecommerce.ui.navigation_comp.screensNav.ECommerceScreens

@Composable
fun RenderViewState(vm: CategoriesViewModel, navController: NavHostController) {

    val states by vm.states.collectAsState()
    val events by vm.events.collectAsState()
    val subCategoriesList = vm.subCategoriesList.value

    when (states) {
        is CategoriesContract.State.Error -> {
            var showDialog by remember { mutableStateOf(true) }
            CustomAlertDialog(showDialog = showDialog,
                (states as CategoriesContract.State.Error).message,
                onConfirm = {
                    showDialog = false
                },
                onDismiss = {
                    showDialog = false
                })
        }

        is CategoriesContract.State.Loading -> CustomLoadingWidget()
        is CategoriesContract.State.Success -> CategoriesContent((states as CategoriesContract.State.Success).categories,
            subCategoriesList,
            onCategoryItemClick = {
                vm.invokeAction(CategoriesContract.Action.CategoryClick(it))
            },
            onSubCategoryItemClick = {
                vm.invokeAction(CategoriesContract.Action.SubCategoryItemClick(it))
            })
    }
    when (events) {
        is CategoriesContract.Event.Idle -> {}
        is CategoriesContract.Event.NavigateToProductsList -> {
            // navigate to product screen
            navController.navigate(
                "${ECommerceScreens.ProductsScreen.name}/{${(events as CategoriesContract.Event.NavigateToProductsList).categoryId}}"
            )
        }
    }

}


@Composable
fun CategoriesScreen(
    vm: CategoriesViewModel, navController: NavHostController
) {
    RenderViewState(vm, navController)
}

@Composable
fun CategoriesContent(
    categoriesList: List<Category?>?,
    subCategoriesList: List<SubCategory>,
    onCategoryItemClick: (category: Category) -> Unit,
    onSubCategoryItemClick: (categoryId: String) -> Unit
) {
    var category by remember { mutableStateOf(Category()) }

    Column(
        modifier = Modifier.padding(start = 8.dp), verticalArrangement = Arrangement.SpaceBetween
    ) {
        CustomTopBar()
        Row(
            Modifier
                .padding(top = 16.dp, bottom = 36.dp)
                .fillMaxSize()
        ) {
            Column(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.33F)
                    .background(
                        Color.Transparent, shape = RoundedCornerShape(10.dp)
                    )

            ) {
                CategoriesLazyColumn(categoriesList) {
                    category = it
                    onCategoryItemClick(category)
                }
            }
            Column(
                Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(Color.Transparent)
            ) {
                SubCategoriesLazyColumn(category, subCategoriesList) {
                    onSubCategoryItemClick(category.id!!)
                }
            }
        }

    }
}


@Composable
fun CategoriesLazyColumn(
    categoryList: List<Category?>?, onCategoryClick: (category: Category) -> Unit
) {
    val newList = categoryList?.toMutableList()
    newList?.removeAt(0)
    var selectedIndex by remember { mutableIntStateOf(0) }
    LazyColumn() {
        items(newList!!.size) { index ->
            val item = newList[index]
            if (selectedIndex == index) onCategoryClick(item!!)
            CategoryItem(category = item!!, modifier = Modifier.selectable(selectedIndex == index) {
                selectedIndex = index
            }, isSelected = selectedIndex == index)
        }
    }
}

@Composable
fun CategoryItem(category: Category, modifier: Modifier, isSelected: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = if (isSelected) Color.Transparent else colorResource(id = R.color.stroke_color_30op)
            )
    ) {
        if (isSelected) Box(
            Modifier
                .padding(5.dp)
                .width(7.dp)
                .height(72.dp)
                .background(color = Color(0xFF004182), shape = RoundedCornerShape(size = 20.dp))
        ) else Spacer(
            Modifier
                .padding(5.dp)
                .width(7.dp)
                .height(72.dp)
                .background(color = Color.Transparent, shape = RoundedCornerShape(size = 20.dp))

        )

        Text(
            text = category.name ?: "", style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF06004F),
                textAlign = TextAlign.Center,
            )
        )
    }


}

@Composable
fun SubCategoriesLazyColumn(
    category: Category,
    subCategoriesList: List<SubCategory>,
    onSubCategoryItemClick: (categoryId: String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = category.name ?: "Category",
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF06004F),
                textAlign = TextAlign.Center,
            )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(94.dp)
                .clickable {
                    onSubCategoryItemClick(category.id!!)
                }, contentAlignment = Alignment.TopStart
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = category.image,
                contentDescription = "Category image",
                placeholder = painterResource(id = R.drawable.ic_catageroy),
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier
                    .padding(start = 14.dp, top = 8.dp)
                    .width(80.dp),
                text = category.name ?: "category",
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF06004F)
                ),
                textAlign = TextAlign.Start
            )
            TextButton(modifier = Modifier
                .padding(
                    start = 14.dp, top = 6.dp, end = 24.dp, bottom = 6.dp
                )
                .background(color = Color(0xFF004182), shape = RoundedCornerShape(size = 10.dp))
                .width(97.dp)
                .height(30.dp)
                .align(Alignment.BottomStart), onClick = {
                onSubCategoryItemClick(category.id!!)
            }) {
                Text(
                    text = "Shop Now", style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center
                    )
                )
            }
        }

        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            items(subCategoriesList.size) {
                val item = subCategoriesList[it]
                SubCategoryItem(item) {
                    onSubCategoryItemClick(item.category!!)
                }
            }
        }

    }
}

@Composable
fun SubCategoryItem(
    item: SubCategory, onSubCategoryItemClick: (categoryId: String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(
                width = 1.dp, color = Color(0x4D004182), shape = RoundedCornerShape(size = 5.dp)

            )
            .width(70.dp)
            .height(70.dp)
            .background(Color(0xFFCAD7E5))
            .clickable {
                onSubCategoryItemClick(item.category!!)
            }, contentAlignment = Alignment.Center
    ) {
        Text(
            text = item.name!!, style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF06004F),
                textAlign = TextAlign.Center
            )
        )
    }
}







