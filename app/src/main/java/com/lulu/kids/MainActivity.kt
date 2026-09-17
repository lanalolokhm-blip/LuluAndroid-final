package com.lulu.kids

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Cream = Color(0xFFFFF9F2)
private val Ink = Color(0xFF493C36)
private val Gold = Color(0xFFC9A66B)
private val Pink = Color(0xFFF9B7C6)
private val Mint = Color(0xFFB9D9C5)
private val Blue = Color(0xFFBFD4E8)
private val Lavender = Color(0xFFD8C8E8)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { LuluApp() }
    }
}

@Composable
fun LuluApp() {
    var page by remember { mutableStateOf("home") }
    var stars by remember { mutableIntStateOf(12) }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Surface(modifier = Modifier.fillMaxSize(), color = Cream) {
            when (page) {
                "home" -> Home(stars) { page = it }
                "letters" -> Letters({ page = "home" }, { stars += 1 })
                "colors" -> Colors({ page = "home" }, { stars += 1 })
                "numbers" -> Numbers({ page = "home" }, { stars += 1 })
                "parent" -> Parent(stars) { page = "home" }
                "stories" -> Stories { page = "home" }
                else -> Home(stars) { page = it }
            }
        }
    }
}

@Composable
fun Home(stars: Int, go: (String) -> Unit) {
    Column(Modifier.fillMaxSize().padding(18.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("⭐ $stars", color = Gold, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Column(horizontalAlignment = Alignment.End) {
                Text("لولو ✨", color = Gold, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Text("مرحباً بك في عالم لولو", color = Ink, fontSize = 14.sp)
            }
        }
        Spacer(Modifier.height(16.dp))
        Card(shape = RoundedCornerShape(28.dp), colors = CardDefaults.cardColors(containerColor = Color.White), modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("🐰 👧", fontSize = 54.sp)
                Text("لنبدأ رحلة التعلم", color = Ink, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("نتعلم • نكتشف • نكبر", color = Ink, fontSize = 15.sp)
                Spacer(Modifier.height(12.dp))
                Button(onClick = { go("letters") }, colors = ButtonDefaults.buttonColors(containerColor = Gold)) { Text("ابدأ الآن") }
            }
        }
        Spacer(Modifier.height(18.dp))
        Text("أقسام التعلم", color = Ink, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Right)
        Spacer(Modifier.height(10.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Tile("🔤", "الحروف", Pink) { go("letters") }
            Tile("🎨", "الألوان", Mint) { go("colors") }
        }
        Spacer(Modifier.height(10.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Tile("🔢", "الأرقام", Blue) { go("numbers") }
            Tile("📖", "القصص", Lavender) { go("stories") }
        }
        Spacer(Modifier.height(10.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Tile("⭐", "المكافآت", Color(0xFFFFE3A8)) { go("parent") }
            Tile("👨‍👩‍👧", "للأهل", Color(0xFFFFD8C8)) { go("parent") }
        }
    }
}

@Composable
fun RowScope.Tile(icon: String, title: String, bg: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier.weight(1f).clickable(onClick = onClick),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = bg)
    ) {
        Column(Modifier.padding(18.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(icon, fontSize = 32.sp)
            Text(title, color = Ink, fontSize = 17.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun Top(title: String, back: () -> Unit) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Text("‹", fontSize = 36.sp, color = Gold, modifier = Modifier.clickable(onClick = back))
        Text(title, color = Ink, fontSize = 25.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun Letters(back: () -> Unit, reward: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Top("الحروف", back)
        Spacer(Modifier.height(22.dp))
        Text("3 / 28", color = Gold, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(14.dp))
        Card(shape = RoundedCornerShape(28.dp), colors = CardDefaults.cardColors(containerColor = Color.White), modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("أ", fontSize = 90.sp, color = Gold, fontWeight = FontWeight.Bold)
                Text("أرنب 🐰", fontSize = 25.sp, color = Ink)
            }
        }
        Spacer(Modifier.height(18.dp))
        Button(onClick = reward, colors = ButtonDefaults.buttonColors(containerColor = Gold)) { Text("أحسنت! ⭐") }
    }
}

@Composable
fun Colors(back: () -> Unit, reward: () -> Unit) {
    val choices = listOf("أحمر 🔴", "أزرق 🔵", "أصفر 🟡", "أخضر 🟢")
    Column(Modifier.fillMaxSize().padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Top("الألوان", back)
        Spacer(Modifier.height(25.dp))
        Text("اختر اللون الصحيح", color = Ink, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(18.dp))
        Text("🍎", fontSize = 80.sp)
        Spacer(Modifier.height(15.dp))
        choices.forEach { choice ->
            Card(Modifier.fillMaxWidth().padding(vertical = 5.dp).clickable { if (choice.startsWith("أحمر")) reward() }, shape = RoundedCornerShape(18.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                Text(choice, Modifier.padding(16.dp).fillMaxWidth(), color = Ink, fontSize = 19.sp, textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun Numbers(back: () -> Unit, reward: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Top("الأرقام", back)
        Spacer(Modifier.height(25.dp))
        Text("عدّ معي", color = Ink, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(18.dp))
        Text("1  2  3  4  5", color = Gold, fontSize = 38.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(22.dp))
        Button(onClick = reward, colors = ButtonDefaults.buttonColors(containerColor = Gold)) { Text("أحسنت! ⭐") }
    }
}

@Composable
fun Stories(back: () -> Unit) {
    val stories = listOf("رحلة لولو إلى الغابة 🌳", "النجمة الصغيرة ⭐", "البيت الجميل 🏠")
    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Top("القصص", back)
        Spacer(Modifier.height(20.dp))
        stories.forEach { story ->
            Card(Modifier.fillMaxWidth().padding(vertical = 6.dp), shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                Column(Modifier.padding(18.dp)) {
                    Text(story, color = Ink, fontSize = 19.sp, fontWeight = FontWeight.Bold)
                    Text("قصة ممتعة نتعلم منها شيئاً جديداً كل يوم.", color = Ink, fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
fun Parent(stars: Int, back: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Top("لوحة الأهل", back)
        Spacer(Modifier.height(24.dp))
        Card(shape = RoundedCornerShape(24.dp), colors = CardDefaults.cardColors(containerColor = Color.White), modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(22.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("⭐ $stars", color = Gold, fontSize = 35.sp, fontWeight = FontWeight.Bold)
                Text("نجوم لولو", color = Ink, fontSize = 19.sp)
                Spacer(Modifier.height(14.dp))
                Text("وقت التعلم اليومي: 10 دقائق", color = Ink, fontSize = 16.sp)
            }
        }
    }
}
