package com.example.staywhere

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.staywhere.booked.presentation.Booked
import com.example.staywhere.favourite.presentation.Favourite
import com.example.staywhere.upload.presentation.UploadPropScreen
import com.example.staywhere.profile.presentation.LoginScreen
import com.example.staywhere.profile.presentation.Profile
import com.example.staywhere.profile.presentation.SignupScreen
import com.example.staywhere.properties.representation.Address
import com.example.staywhere.properties.representation.Explore
import com.example.staywhere.properties.representation.Property
import com.example.staywhere.properties.representation.PropertyDetailsScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.serialization.Serializable
import kotlin.collections.listOf

sealed class NavDestination {
    @Serializable object Explore : NavDestination()
    @Serializable object Favourite : NavDestination()
    @Serializable object Booked : NavDestination()
    @Serializable object UploadProp : NavDestination()
    @Serializable object Profile : NavDestination()
    @Serializable object Login: NavDestination()
    @Serializable object Signup: NavDestination()
    @Serializable data class PropertyDetails(val images: List<String>): NavDestination()
}


data class NavItem(
    val icon: ImageVector,
    val title: String,
    val dest: NavDestination
)

val navItemsList = listOf(
    NavItem(
        icon = Icons.Outlined.Search,
        title = "Explore",
        dest = NavDestination.Explore
    ),
    NavItem(
        icon = Icons.Outlined.FavoriteBorder,
        title = "Favourite",
        dest = NavDestination.Favourite
    ),
    NavItem(
        icon = Icons.Outlined.Check,
        title = "Booked",
        dest = NavDestination.Booked
    ),
    NavItem(
        icon = Icons.AutoMirrored.Outlined.Send,
        title = "Upload",
        dest = NavDestination.UploadProp
    ),
    NavItem(
        icon = Icons.Outlined.AccountCircle,
        title =  "Profile",
        dest = NavDestination.Profile
    )
)

@Composable
fun NavItemsLoader(navController: NavHostController, icon: ImageVector, title: String, dest: Any){
    Column(
        modifier = Modifier
            .clickable { navController.navigate(dest) }
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier.size(28.dp),
        )
        Text(text = title, fontSize = 12.sp)
    }
}

@Composable
fun StayWhereAppBar(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    BottomAppBar(
        content = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly, // Evenly spaces icons
                verticalAlignment = Alignment.CenterVertically
            ) {

                navItemsList.forEach { item ->
                    NavItemsLoader(navController = navController, icon = item.icon, title = item.title, dest = item.dest)
                }

            }
        },
        containerColor = Color.White,
    )

}

@Composable
fun StayWhereApp(
    navController: NavHostController = rememberNavController()
) {
//    val backStackEntry by navController.currentBackStackEntryAsState()

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(Color.White, darkIcons = true)
    }
    Scaffold(
        bottomBar = {
            StayWhereAppBar(
                navController = navController,
                modifier = Modifier.background(Color.White)

            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavDestination.Explore,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            composable<NavDestination.Explore> {
                Explore(navController = navController)
            }
            composable<NavDestination.PropertyDetails> {
                val args = it.toRoute<NavDestination.PropertyDetails>()
                PropertyDetailsScreen(images = args.images, property = Property(
                    description = "Beautiful house at Zakir Nagar with modern amenities.",
                    address = Address(
                        houseno = "B/29",
                        street = "Street no. 6",
                        city = "New Delhi",
                        state = "Delhi",
                        zip = "110025"
                    ),
                    price = 1250000.0,
                    bedrooms = 4,
                    bathrooms = 3,
                    area = 2800.0,
                    imageUrl = ""
                ))
            }
            composable<NavDestination.Favourite> {
                Favourite()
            }
            composable<NavDestination.Booked> {
                Booked()
            }
            composable<NavDestination.UploadProp> {
                UploadPropScreen()
            }
            composable<NavDestination.Profile> {
                Profile(navController = navController)
            }
            composable<NavDestination.Signup> {
                SignupScreen(navController = navController)
            }
            composable<NavDestination.Login> {
                LoginScreen(navController = navController)
            }

        }

    }
}
