package com.imajdroid.sollam.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.imajdroid.sollam.R
import com.imajdroid.sollam.ui.navigations.BottomNavGraph


data class BottomNavigationItem(
val title: String,
val selectedIcon: Painter,
val unSelectedIcon: Painter,
val destination: String
)


@Composable
fun MainScreen(){


val navController = rememberNavController()



    Scaffold(
    modifier = Modifier,
    topBar = {
        TopBar(navController = navController) },


    bottomBar = {
        BottomBar(navController)
    }
) { contentPadding ->
    Box(modifier = Modifier.padding(contentPadding)) {
        BottomNavGraph(navController = navController)
    }
}
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController){


    val screens = listOf(
        "main","home", "my_learning", "store", "profile"
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination


    val bottomBarDestination = screens.any{
        it == currentDestination?.route
    }

    if(navBackStackEntry?.destination?.route !=null
        &&!bottomBarDestination)
        return

    TopAppBar(
        modifier = Modifier
            .padding(
                horizontal = 10.dp,
                vertical = 5.dp
            )
            .clip(
                RoundedCornerShape(20.dp)
            ),
        title = {

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start){
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = "سُلم",
                    textAlign = TextAlign.Right)

            }
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Menu,
                    contentDescription = "Menu icon")
            }
        },
//        actions = {
//            IconButton(onClick = {}) {
//                Icon(imageVector = Icons.Outlined.FavoriteBorder,
//                    contentDescription = "Favorite icon")
//            }
//        },
        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = Color(0xFFe0a8a5)
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            titleContentColor = MaterialTheme.colorScheme.surfaceTint,
            navigationIconContentColor = MaterialTheme.colorScheme.surfaceTint,
        )

    )



}


@Composable
fun BottomBar(navController: NavController){


val items = listOf(
    BottomNavigationItem(
        title = "الرئيسية",
        selectedIcon = rememberVectorPainter(image = Icons.Filled.Home),
        unSelectedIcon = rememberVectorPainter(image = Icons.Outlined.Home),
        destination = "home"
    ),
    BottomNavigationItem(
        title = "دوراتي",
        selectedIcon = painterResource(id = R.drawable.baseline_video_library_24),
        unSelectedIcon = painterResource(id = R.drawable.outline_video_library_24),
        destination = "my_learning"

    ),
    BottomNavigationItem(
        title = "المتجر",
        selectedIcon = rememberVectorPainter(image = Icons.Filled.ShoppingCart),
        unSelectedIcon = rememberVectorPainter(image = Icons.Outlined.ShoppingCart),
        destination = "store"

    ),
    BottomNavigationItem(
        title = "الملف الشخصي",
        selectedIcon = rememberVectorPainter(image = Icons.Filled.Person),
        unSelectedIcon = rememberVectorPainter(image =  Icons.Outlined.Person),
        destination = "profile",

    )
)

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }


    val screens = listOf(
        "main","home", "my_learning", "store", "profile"
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination


    val bottomBarDestination = screens.any{
        it == currentDestination?.route
    }


    if(navBackStackEntry?.destination?.route !=null
        &&!bottomBarDestination)
        return

    val color = MaterialTheme.colorScheme.surfaceTint

    NavigationBar(
        modifier = Modifier.padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
       items.forEachIndexed{index, item->
           NavigationBarItem(
               selected = selectedItemIndex == index,
               onClick = {
                         selectedItemIndex = index
                        navController.navigate(item.destination)
                         },
               label = {
                       Text(text = item.title, color = color)
               },
               icon = {
                       Icon(
                           painter = if(index == selectedItemIndex){
                                   item.selectedIcon
                               }else item.unSelectedIcon
                           , contentDescription = item.title,
                           tint= color)

               })
       }
   }
}

