package com.imajdroid.sollam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.StackedLineChart
import androidx.compose.material.icons.rounded.Person
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.imajdroid.sollam.R
import com.imajdroid.sollam.Vals
import com.imajdroid.sollam.ui.navigations.BottomNavGraph


data class BottomNavigationItem(
val title: String,
val selectedIcon: Painter,
val unSelectedIcon: Painter,
val destination: String
)


@Composable
fun MainScreen(){


//    LaunchedEffect(key1 = Unit) {
//        FirebaseFirestore.getInstance().collection("coursesContent")
//            .document("english2s")
//            .get().addOnSuccessListener {
//                FirebaseFirestore.getInstance().collection("courses")
//                    .document("english2s")
//                    .set(it.data!!)
//            }
//    }


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
        "main",
//        "home",
        "my_learning", "store", "profile"
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
                horizontal = 0.dp,
            ),


//            .clip(
//                RoundedCornerShape(20.dp)
//            )

        title = {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center){

                Text(
                    modifier = Modifier
                        .padding(horizontal = 2.dp),
                    text = "سُلم",
                    fontFamily = Vals.tajwal,
                    )
                Icon(imageVector = Icons.Outlined.StackedLineChart, contentDescription = null)

            }
        },
        navigationIcon = {
            IconButton(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(200.dp)
                    ),
                onClick = {}) {
//                Icon(imageVector = Icons.Default.Menu,
//                    contentDescription = "Menu icon")


                if(FirebaseAuth.getInstance().currentUser!!.photoUrl.toString().isBlank()) {
                    AsyncImage(

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        model = Vals.student?.profilePictureUrl,
                        contentScale = ContentScale.Crop,
                        contentDescription = ""

                    )
                }else{

                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.default_profile_picture),

                        contentDescription = null)
//                    Box(modifier = Modifier
//                        .width(250.dp)
//                        .height(250.dp)
//                        .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape),
//                        contentAlignment = Alignment.Center
//                        ) {
//                        Icon(
////                            modifier = Modifier.padding(4.dp),
//                            imageVector = Icons.Filled.Person, contentDescription = null,
//                            tint = MaterialTheme.colorScheme.primary)
//                    }
                }
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
            titleContentColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
        )

    )



}


@Composable
fun BottomBar(navController: NavController){


val items = listOf(
//    BottomNavigationItem(
//        title = "الرئيسية",
//        selectedIcon = rememberVectorPainter(image = Icons.Filled.Home),
//        unSelectedIcon = rememberVectorPainter(image = Icons.Outlined.Home),
//        destination = "home"
//    ),
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
        "main",
//        "home",
        "my_learning", "store", "profile"
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
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
    ) {
       items.forEachIndexed{index, item->
           NavigationBarItem(
               selected = selectedItemIndex == index,
               onClick = {
                   selectedItemIndex = index
                   navController.navigate(item.destination){
                       popUpTo(navController.currentBackStackEntry?.destination?.route!!) {
                            inclusive = true
                            }
                        }
                         },
               label = {
                       Text(text = item.title,
                           fontFamily = Vals.tajwal,
                           color = color
                       )
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

