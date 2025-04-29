package com.imajdroid.eschool.ui.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imajdroid.eschool.Vals
import com.imajdroid.eschool.pojo.City
import com.imajdroid.eschool.pojo.School
import com.imajdroid.eschool.repository.city.CityViewModel
import com.imajdroid.eschool.viewmodels.AddStudentDataViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AddStudentDataScreen(onNavToMain: () -> Unit){





    val vm = viewModel<AddStudentDataViewModel>()

    val state = vm.studentDataState

    val citiesViewModel = viewModel<CityViewModel>()
    //Query all the cities when this screen loads.
    LaunchedEffect(key1 = Unit){
        vm.setLoadingState(true)
        citiesViewModel.getCities()
        vm.setLoadingState(false)

    }


    if(vm.success){
        onNavToMain()
    }

    val cities = citiesViewModel.cities


    val alpha = arrayOf('ا','ب','ت','ث','ج','ح','خ','د','ذ','ر','ز','س','ش','ص','ض','ط','ظ','ع','غ','ف','ق','ك','ل','م','ن','ه','و','ي','أ','إ',)

    data class CityCategory(
        val name: String,
        val items: List<City>
    )
    data class SchoolCharCategory(
        val name: String,
        val items: List<School>
    )
    @Composable
     fun Title(text: String){
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = text,
            fontSize = 35.sp,
            textAlign = TextAlign.Right,
            fontFamily = Vals.tajwal,
            color = MaterialTheme.colorScheme.primary
        )
    }



    @Composable
    fun ColumnRadioItem(
        modifier: Modifier = Modifier,
        text: String,
        itemId: String,
        selected: Boolean,
        onItemClick: (String)-> Unit

    ){
        Row(  modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(0.dp, 16.dp)
            .clickable { onItemClick(itemId) },
            horizontalArrangement = Arrangement.SpaceBetween

        ) {

            Text(
                modifier = Modifier
                    .padding(16.dp),
                text = text,
                fontFamily = Vals.tajwal,
                fontSize = 25.sp


            )

            RadioButton(
                selected = selected,
                onClick = { onItemClick(itemId) }

            )
        }

    }







    @Composable
    fun GradeLazyColumn(
        modifier: Modifier = Modifier
    ){
        val schoolType = remember { mutableStateOf("") }

        LazyColumn(modifier = modifier
            .padding(0.dp, 0.dp,  0.dp, 60.dp)){

            val seventh = "7"
            val eighth = "8"
            val ninth = "9"

            val tenthArt = "10a"
            val tenthScience = "10s"

            val eleventhArt = "11a"
            val eleventhScience = "11s"

            val twelfthArt = "12a"
            val twelfthScience = "12s"


            item {
                ColumnRadioItem(text = "السابع",
                    itemId = seventh,
                    selected = state.gradeId == seventh,

                    onItemClick = {vm.onGradeIdChange(seventh, "السابع") })

                ColumnRadioItem(text = "الثامن",
                    itemId =  eighth,
                    selected = state.gradeId == eighth,
                    onItemClick = {vm.onGradeIdChange(eighth, "الثامن")})

                ColumnRadioItem(text = "التاسع",
                    itemId =  ninth,
                    selected = state.gradeId == ninth,
                    onItemClick = {vm.onGradeIdChange(ninth, "التاسع")})

                ColumnRadioItem(text = "الأول ثانوي - أدبي",
                    itemId =  tenthArt,
                    selected = state.gradeId == tenthArt,
                    onItemClick = {vm.onGradeIdChange(tenthArt, "الأول ثانوي - أدبي")})

                ColumnRadioItem(text = "الأول ثانوي - علمي",
                    itemId =  tenthScience,
                    selected = state.gradeId == tenthScience,
                    onItemClick = { vm.onGradeIdChange(tenthScience, "الأول ثانوي - علمي") })


                ColumnRadioItem(text = "الثاني ثانوي - أدبي",
                    itemId =  eleventhArt,
                    selected = state.gradeId == eleventhArt,
                    onItemClick = {vm.onGradeIdChange(eleventhArt, "الثاني ثانوي - أدبي")})


                ColumnRadioItem(text = "الثاني ثانوي - علمي",
                    itemId =  eleventhScience,
                    selected = state.gradeId == eleventhScience,
                    onItemClick = {vm.onGradeIdChange(eleventhScience, "الثاني ثانوي - علمي")})


                ColumnRadioItem(text = "الثالث ثانوي - أدبي",
                    itemId =  twelfthArt,
                    selected = state.gradeId == twelfthArt,
                    onItemClick = {vm.onGradeIdChange(twelfthArt, "الثالث ثانوي - أدبي")})

                ColumnRadioItem(text = "الثالث ثانوي - علمي",
                    itemId =  twelfthScience,
                    selected = state.gradeId == twelfthScience,
                    onItemClick = {vm.onGradeIdChange(twelfthScience, "الثالث ثانوي - علمي")})
            }
        }
    }



    @Composable
    fun CitiesLazyColumn(){

    }

    @Composable
     fun CategoryHeader(
        text: String,
        modifier: Modifier = Modifier


    ){
        Text(

            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(16.dp),

            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Vals.tajwal


            )
    }



    @OptIn(ExperimentalFoundationApi::class)
    @Composable
     fun CitiesLazyColumn(
        categories: List<CityCategory>,
        modifier: Modifier = Modifier
    ){

        LazyColumn(modifier.padding(0.dp, 0.dp, 0.dp, 60.dp)){
            categories.forEach{ category->
                stickyHeader {
                    CategoryHeader(text = category.name,)
                }
                items(category.items){city->
                    ColumnRadioItem(text = city.cityName,

                        itemId =city.cityId,
                        selected = state.cityId == city.cityId){
                        vm.onCityIdChange(it, cityName=city.cityName)
                    }
                }
            }

        }
    }




//    @Composable
//    fun SchoolLazyColumn(categories: List<SchoolCharCategory>) {
//        LazyColumn(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 60.dp)) {
//            categories.forEach { category ->
//                stickyHeader {
//                    CategoryHeader(text = category.name,)
//                }
//                items(category.items) { school ->
//                    ColumnRadioItem(
//                        text = school.schoolName,
//                        itemId = school.schoolId,
//                        selected = state.schoolId == school.schoolId
//                    ) {
//                        vm.onSchoolIdChange(it, schoolName = school.schoolName)
//                    }
//                }
//            }
//        }
//    }




    @Composable
    fun NamesPage(){
        Column(
            modifier = Modifier.fillMaxSize(),

        ) {
            Title(text = "الأسم الرباعي")
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    modifier = Modifier.padding(0.dp, 8.dp),
                    singleLine = true,
                    value = state.firstName,
                    leadingIcon = {
                                  Icon(Icons.Outlined.AccountCircle, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    },
                    onValueChange = {
                        if(it.length <= 15)
                            vm.onFirstNameChange(it)
                                    },
                    label = {
                        Text(text = "الإسم الأول" ,
                            fontFamily = Vals.tajwal,
                        )
                    },

                    isError = state.namesError
                )

                OutlinedTextField(
                    modifier = Modifier.padding(8.dp),
                    value = state.secondName,
                    singleLine = true,
                    leadingIcon = {
                        Icon(Icons.Outlined.AccountCircle, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    },
                    onValueChange = {
                        if(it.length <= 15)
                            vm.onSecondNameChange(it)},
                    label = {
                        Text(text = "إسم الأب",
                            fontFamily = Vals.tajwal,
                        )
                    },
                    isError = state.namesError
                )

                OutlinedTextField(
                    modifier = Modifier.padding(8.dp),
                    value = state.thirdName,
                    singleLine = true,
                    leadingIcon = {
                        Icon(Icons.Outlined.AccountCircle, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    },
                    onValueChange = {
                        if(it.length <= 15)
                            vm.onThirdNameChange(it)},
                    label = {
                        Text(text = "إسم الجد",
                            fontFamily = Vals.tajwal,
                        )
                    },
                    isError = state.namesError
                )

                OutlinedTextField(
                    modifier = Modifier.padding(8.dp),
                    value = state.surname,
                    singleLine = true,
                    leadingIcon = {
                        Icon(Icons.Outlined.AccountCircle, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    },
                    onValueChange = {
                        if(it.length <= 15)
                            vm.onSurnameChange(it)},
                    label = {
                        Text(text = "اللقب",
                            fontFamily = Vals.tajwal,
                        )
                    },
                    isError = state.namesError

                )
            }
        }
    }



    @Composable
    fun CityPage(cities: List<City>){

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Title(text = "المدينة")},
                    actions = {
                        IconButton(
                            onClick = {}) {
                            Icon(Icons.Filled.Add, "Add")
                        }

                        IconButton(onClick = {}
                        ) {
                            Icon(Icons.Filled.Search, "Search")
                        }
                    }
                )
            },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                val categories = ArrayList<CityCategory>()


                for (a in alpha) {
                    val abCities = ArrayList<City>()
                    for (city in cities) {
                        if (city.cityName.startsWith(a)) {
                            abCities.add(city)
                        }
                    }
                    if (abCities.isNotEmpty())
                        categories.add(CityCategory(a.toString(), abCities))
                }
                CitiesLazyColumn(categories = categories)
            }
        }
    }





    @Composable
    fun SchoolPage() {

        Scaffold(
            topBar = {
                     TopAppBar(
                         title = { Title(text = "أين تدرس؟")},

                     )
            },

            content = { it ->


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    LazyColumn(){

                        val privateSchool = "private"
                        val publicSchool = "public"

                        item {
                            ColumnRadioItem(text = "في مدرسة خاصة",
                                itemId = privateSchool,
                                selected = state.isPrivate,
                                onItemClick = {vm.onPrivateStateChange(true)})


                            ColumnRadioItem(text = "في مدرسة عامة",
                                itemId =  publicSchool,
                                selected = !state.isPrivate,
                                onItemClick = {vm.onPrivateStateChange(false)})
                        }
                    }


                    OutlinedTextField(
                        
                        value = state.schoolName,
                        label = {
                            Text(text = "اسم المدرسة")
                        },
                        leadingIcon = {
                            Icon(Icons.Outlined.School, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        },
                        isError = state.schoolError,
                        singleLine = true,
                        onValueChange = {vm.onSchoolNameChange(schoolName= it)})
                }
            }
        )




    }



    @Composable
    fun GradePage(){

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Title(text = "الصف")
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
            GradeLazyColumn()
        }
    }


    @Composable
    fun SummaryText(text: String){
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = text,
            fontSize = 20.sp,

            color = MaterialTheme.colorScheme.secondary
        )
    }
    @Composable
    fun SummaryPage(){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Title(text = "مراجعة البيانات")
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
            SummaryText(text = "الاسم: ${state.firstName} ${state.secondName} ${state.thirdName} ${state.surname}")
            SummaryText(text = "المدينة: ${state.cityName}")
            SummaryText(text = "المدرسة: ${state.schoolName}")
            SummaryText(text = "الصف: ${state.gradeName}")
            SummaryText(text = "رقم الهاتف: ${state.phoneNumber}")

        }
    }


    @Composable
    fun ContactPage(){ //Phone number and Profile picture
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {

            Text(text = "I still need to add profile picture here!")

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),

                label = {
                    Text(text = "رقم الهاتف",
                        fontFamily = Vals.tajwal,
                    )
                },


                leadingIcon = {
                    Icon(Icons.Outlined.Phone, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                },
                value = state.phoneNumber,
                singleLine = true,
                isError = state.phoneNumberError,
                onValueChange = {
                    if(it.length <= 10)
                        vm.onPhoneNumberChange(it)
                },)


        }
    }

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {7}
    )



    fun post() {
        Log.i("test","inside from the screen")
        vm.postNewStudentData()
    }


    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        Scaffold(
            bottomBar = {
                BottomAppBar {
                    Row( modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    )

                    {

                        val coroutineScope = rememberCoroutineScope()

                        TextButton(
                            modifier = Modifier
                                .height(60.dp)
                                .width(180.dp),
                            onClick = {
                                coroutineScope.launch {
                                    if(pagerState.currentPage > 0)
                                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                            },) {
                            Text(text = "رجوع",
                                fontFamily = Vals.tajwal,
                            )
                        }

                        Button(
                            modifier = Modifier
                                .height(60.dp)
                                .width(180.dp),

                            onClick = {
                                coroutineScope.launch {

                                    when(pagerState.currentPage){


                                        0->{
                                            if(vm.validateNames())
                                                pagerState.animateScrollToPage(1)
                                            else{
                                                vm.setNamesError(true)
                                            }

                                        }
                                        1->{
                                            if(vm.validateCity())
                                                pagerState.animateScrollToPage(2)
                                            else{
                                                vm.setCityError(true)
                                            }
                                        }
                                        2->{
                                            if(vm.validateSchool())
                                                pagerState.animateScrollToPage(3)
                                            else{
                                                vm.setSchoolError(true)

                                            }                                        }
                                        3->{
                                            if(vm.validateGrade()){
                                                pagerState.animateScrollToPage(4)

                                            }else{
                                                vm.setGradeError(true)

                                            }
                                        }
                                        4->{
                                            if (vm.validatePhoneNumber()) {
                                                pagerState.animateScrollToPage(5)
                                            } else {
                                                vm.setPhoneNumberError(true)
                                            }
                                        }

                                        5->{
                                            post()
                                        }

                                    }
                                }
                            }
                            ,) {
                            Text(text = "التالي",
                                fontFamily = Vals.tajwal,
                            )
                        }
                    }



                }
            }
        ) {innerPadding ->
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                    userScrollEnabled = false,

                state = pagerState) { page ->

                if(state.isLoading){
                    Box(modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }else {
                    when (page) {
                        0 -> NamesPage()
                        1 -> CityPage(cities)
                        2 ->SchoolPage()
                        3 -> GradePage()
                        4 -> ContactPage()
                        5 -> SummaryPage()
                    }
                }
            }


        }
    }

}



@Preview
@Composable
fun AddStudentDataScreenPrev(){
//    AddStudentDataScreen()
}