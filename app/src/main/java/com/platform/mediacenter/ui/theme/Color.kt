package com.platform.mediacenter.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable

val darkBlue = Color(0xFF003285)
val blackWindowLight = Color(0xFF131313)
val bottomifyPressedColorLight = Color(0xFFE6E6E6 )

val colorPrimary = Color(0xFFE58D23)
val colorAccent = Color(0xFFECAA5B)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val orange_dark = Color(0xff773A05)


val Colors.splashBg: Color
    @Composable
    get() = Color(0xFFE58D23)



val Colors.CursorColor : Color
    @Composable
    get() = Color(0xFF018577)


val Colors.drawerTopColor: Color
    @Composable
    get() = if (isLight) colorPrimary else Color(0xFF202020)

val Colors.drawerBgColor: Color
    @Composable
    get() = if (isLight) Color.White else Color(0xFF111111)

val Colors.selectedBottomBar: Color
    @Composable
    get() = if (isLight) Color(0xFF3E4145) else Color(0xFFCFD4DA)

val Colors.unSelectedBottomBar: Color
    @Composable
    get() = if (isLight) Color(0xFFA4A1A1) else Color(0xFF575A5E)


val Colors.bottomBar: Color
    @Composable
    get() = if (isLight) bottomifyPressedColorLight else blackWindowLight

val Colors.Gold : Color
    @Composable
    get() = Color(0xFFf9bc01)

val Colors.grayAlpha: Color
    @Composable
    get() = Color(0xFFc1c2c6)

val Colors.searchBarBg: Color
    @Composable
    get() = if (isLight) Color(0xFFF1F0EE) else Color(0xFF303235)


val Colors.darkText: Color
    @Composable
    get() = if (isLight) blackWindowLight else Color(0xFFD8D8D8)

val Colors.amber: Color
    @Composable
    get() =  Color(0xffFFBF00)


val Colors.grayCategory: Color
    @Composable
    get() = Color(0xFFF1F0EE)

val Colors.AppLightRed: Color
    @Composable
    get() = if (isLight) Color(0xffef4056) else Color(0xFF8D2633)

val Colors.colorPrimaryLight: Color
    @Composable
    get() = if (isLight) Color(0xffECAA5B) else Color(0xFFFFFFFF)

val Colors.AppRedText: Color
    @Composable
    get() = if (isLight) Color(0xFFE58D23) else Color(0xFFFFFFFF)

val Colors.colorPrimaryDark: Color
    @Composable
    get() = Color(0xFFB26212)

val Colors.primeryColor: Color
    @Composable
    get() = Color(0xFFE58D23)

val Colors.semiDarkText: Color
    @Composable
    get() = if (isLight) Color(0xFF5C5E61) else Color(0xFFD8D8D8)

val Colors.settingArrow: Color
    @Composable
    get() = if (isLight) Color(0xFF9E9FB1) else Color(0xFFD8D8D8)

val Colors.DarkCyan: Color
    @Composable
    get() = Color(0xFF0fabc6)

val Colors.LightCyan: Color
    @Composable
    get() = Color(0xFF17BFD3)

val Colors.Oranges: Color
    @Composable
    get() = Color(0xFFFF5722)



val Colors.AppLightGreen: Color
    @Composable
    get() = if (isLight) Color(0xff86bf3c) else Color(0xFF3A531A)


val Colors.Green : Color
    @Composable
    get() = Color(0xFF00A049)