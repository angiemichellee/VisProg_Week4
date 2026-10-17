package com.example.vp_week4.Soal2

import android.os.Bundle
import com.example.vp_week4.R
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vp_week4.ui.theme.VP_Week4Theme


data class Hero(
    val id: Int,
    val name: String,
    val heroClass: String,
    val baseClass: String,
    val rarity: String,
    val level: Int,
    val cp: Int,
    val currentHp: Int,
    val maxHp: Int,
    val currentMp: Int,
    val maxMp: Int,
    val atk: Int,
    val def: Int,
    val crit: String,
    val spd: Int,
    val skillName: String,
    val skillDesc: String,
    val isInSquad: Boolean = false,
    val imageRes: Int
)

data class FilterChipData(
    val category: String,
    val label: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VP_Week4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PartyRosterScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun PartyRosterScreen(modifier: Modifier = Modifier) {
    val heroList = remember {
        listOf(
            Hero(
                id = 1,
                name = "Alistair",
                heroClass = "High Templar",
                baseClass = "Paladin",
                rarity = "MYTHIC",
                level = 50,
                cp = 5600,
                currentHp = 1650,
                maxHp = 1800,
                currentMp = 650,
                maxMp = 800,
                atk = 380,
                def = 540,
                crit = "22%",
                spd = 105,
                skillName = "Wrath of the Heavens",
                skillDesc = "Calls down a celestial hammer, stunning all enemies for 3 seconds and taunting survivors.",
                imageRes = R.drawable.paladin
            ),
            Hero(
                id = 2,
                name = "Theresa",
                heroClass = "Divine Oracle",
                baseClass = "Cleric",
                rarity = "MYTHIC",
                level = 49,
                cp = 5480,
                currentHp = 1100,
                maxHp = 1200,
                currentMp = 2100,
                maxMp = 2400,
                atk = 240,
                def = 310,
                crit = "15%",
                spd = 112,
                skillName = "Divine Benediction",
                skillDesc = "Restores immense vitality to the whole party and purges negative debuffs.",
                isInSquad = true,
                imageRes = R.drawable.cleric
            ),
            Hero(
                id = 3,
                name = "Vespera",
                heroClass = "Shadowblade",
                baseClass = "Rogue",
                rarity = "MYTHIC",
                level = 48,
                cp = 5350,
                currentHp = 890,
                maxHp = 950,
                currentMp = 410,
                maxMp = 600,
                atk = 610,
                def = 190,
                crit = "48%",
                spd = 145,
                skillName = "Eclipse Execution",
                skillDesc = "Steps into shadow, striking key targets with fatal pierce damage.",
                imageRes = R.drawable.rogue
            ),
            Hero(
                id = 4,
                name = "Elaria",
                heroClass = "Archmage",
                baseClass = "Archmage",
                rarity = "MYTHIC",
                level = 42,
                cp = 5120,
                currentHp = 450,
                maxHp = 600,
                currentMp = 1850,
                maxMp = 2000,
                atk = 540,
                def = 120,
                crit = "38%",
                spd = 128,
                skillName = "Supernova Arcana",
                skillDesc = "Channels cosmic energy into a cataclysmic blast incinerating entire enemy frontline.",
                isInSquad = true,
                imageRes = R.drawable.archmage
            ),
            Hero(
                id = 5,
                name = "Seraphina",
                heroClass = "Luminary Priest",
                baseClass = "Cleric",
                rarity = "LEGENDARY",
                level = 46,
                cp = 4950,
                currentHp = 920,
                maxHp = 1050,
                currentMp = 1600,
                maxMp = 1800,
                atk = 260,
                def = 290,
                crit = "20%",
                spd = 118,
                skillName = "Celestial Ward",
                skillDesc = "Envelopes lowest-health ally with invulnerability and generates +350 Mana.",
                imageRes = R.drawable.cleric
            ),
            Hero(
                id = 6,
                name = "Zephyr",
                heroClass = "Stormcaller",
                baseClass = "Archmage",
                rarity = "LEGENDARY",
                level = 45,
                cp = 4890,
                currentHp = 520,
                maxHp = 700,
                currentMp = 1920,
                maxMp = 2100,
                atk = 490,
                def = 160,
                crit = "31%",
                spd = 132,
                skillName = "Thunderstorm Gale",
                skillDesc = "Summons chained lightning surges across foes with high critical chance.",
                imageRes = R.drawable.archmage
            ),
            Hero(
                id = 7,
                name = "Grommash",
                heroClass = "Berserker",
                baseClass = "Berserker",
                rarity = "LEGENDARY",
                level = 47,
                cp = 4850,
                currentHp = 1200,
                maxHp = 1200,
                currentMp = 40,
                maxMp = 100,
                atk = 590,
                def = 360,
                crit = "28%",
                spd = 98,
                skillName = "Bloodfrenzy Roar",
                skillDesc = "Sacrifices defense to gain unmatched physical attack fury.",
                imageRes = R.drawable.berserker
            ),
            Hero(
                id = 8,
                name = "Thorne",
                heroClass = "Paladin",
                baseClass = "Paladin",
                rarity = "LEGENDARY",
                level = 44,
                cp = 4780,
                currentHp = 980,
                maxHp = 1500,
                currentMp = 300,
                maxMp = 500,
                atk = 330,
                def = 480,
                crit = "14%",
                spd = 102,
                skillName = "Aegis Shield",
                skillDesc = "Absorbs incoming fatal attacks for surrounding allies in position.",
                isInSquad = true,
                imageRes = R.drawable.paladin
            )
        )
    }

    val squadHeroes = heroList.filter { it.isInSquad }
    val totalSquadCp = squadHeroes.sumOf { it.cp }

    var selectedCategory by remember { mutableStateOf("ALL") }
    var expandedHeroId by remember { mutableStateOf<Int?>(null) }

    val displayedHeroes = remember(selectedCategory, heroList) {
        if (selectedCategory == "ALL") {
            heroList
        } else {
            heroList.filter { it.baseClass.equals(selectedCategory, ignoreCase = true) }
        }
    }

    val filterChips = remember(heroList) {
        listOf(
            FilterChipData("ALL", "All (${heroList.size})"),
            FilterChipData("Berserker", "Berserker (${heroList.count { it.baseClass.equals("Berserker", true) }})"),
            FilterChipData("Archmage", "Archmage (${heroList.count { it.baseClass.equals("Archmage", true) }})"),
            FilterChipData("Paladin", "Paladin (${heroList.count { it.baseClass.equals("Paladin", true) }})"),
            FilterChipData("Rogue", "Rogue (${heroList.count { it.baseClass.equals("Rogue", true) }})"),
            FilterChipData("Cleric", "Cleric (${heroList.count { it.baseClass.equals("Cleric", true) }})")
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0F1218))
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                RosterHeader(
                    squadHeroes = squadHeroes,
                    totalCp = totalSquadCp
                )
            }

            item {
                SearchAndFilterSection(
                    filterChips = filterChips,
                    selectedCategory = selectedCategory,
                    onSelectCategory = { newCategory ->
                        selectedCategory = newCategory
                    }
                )
            }

            items(displayedHeroes, key = { it.id }) { hero ->
                HeroCardItem(
                    hero = hero,
                    isExpanded = expandedHeroId == hero.id,
                    onCardClick = {
                        expandedHeroId = if (expandedHeroId == hero.id) null else hero.id
                    }
                )
            }
        }
    }
}

@Composable
fun RosterHeader(
    squadHeroes: List<Hero>,
    totalCp: Int,
    maxSquadSize: Int = 4
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Baris Atas: Judul Party Roster & Tombol Expedition
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Party Roster",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Guild Vanguard • 14 Heroes Available",
                    fontSize = 13.sp,
                    color = Color(0xFF9099A6)
                )
            }

            // Tombol Mini Expedition
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF261D11))
                    .border(1.dp, Color(0xFFD49B35), RoundedCornerShape(8.dp))
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFB800),
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "Expedition",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFB800)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Banner Expedition Squad
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF161922)),
            border = BorderStroke(1.dp, Color(0xFF2C3240))
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                // Header Squad: Label Status & Total CP
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF22C55E))
                        )
                        Text(
                            text = "EXPEDITION SQUAD (${squadHeroes.size}/$maxSquadSize)",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFE2E8F0)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = Color(0xFFFFB800),
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "$totalCp CP",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFB800)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Baris Avatar Slot & Tombol Deploy
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        // 1. Tampilkan hero yang ada di squad
                        squadHeroes.forEach { member ->
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.White) // Background putih seperti contoh demo
                                    .border(1.5.dp, Color(0xFFFF3366), RoundedCornerShape(8.dp)), // Border merah/oranye
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = member.imageRes),
                                    contentDescription = member.name,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier.size(38.dp)
                                )
                            }
                        }

                        // 2. Kotak kosong bertanda (+) untuk sisa kuota
                        val emptySlots = (maxSquadSize - squadHeroes.size).coerceAtLeast(0)
                        repeat(emptySlots) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFF1B1F2A))
                                    .border(1.dp, Color(0xFF2C3240), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "+",
                                    fontSize = 20.sp,
                                    color = Color(0xFF64748B)
                                )
                            }
                        }
                    }

                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB800)),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        Text(
                            text = "Deploy ⚡",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E1705),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun SearchAndFilterSection(
    filterChips: List<FilterChipData>,
    selectedCategory: String,
    onSelectCategory: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF161922))
                .border(1.dp, Color(0xFF272C38), RoundedCornerShape(12.dp))
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color(0xFF6B7280),
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = "Search by hero name, class, or rarity...",
                color = Color(0xFF6B7280),
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LazyRow(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filterChips) { chip ->
                    val isSelected = chip.category.equals(selectedCategory, ignoreCase = true)
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isSelected) Color(0xFF42280A) else Color(0xFF161922))
                            .border(
                                width = 1.dp,
                                color = if (isSelected) Color(0xFFFFB800) else Color(0xFF2B313E),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { onSelectCategory(chip.category) } // <-- Mengaktifkan filter saat diklik
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = chip.label,
                            color = if (isSelected) Color(0xFFFFB800) else Color(0xFF94A3B8),
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF161922))
                    .border(1.dp, Color(0xFF2B313E), RoundedCornerShape(8.dp))
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Sort: Power",
                    color = Color(0xFFFFB800),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun HeroCardItem(
    hero: Hero,
    isExpanded: Boolean,
    onCardClick: () -> Unit
) {

    val cardBorder = if (hero.isInSquad) {
        BorderStroke(1.dp, Color(0xFFFFB800))
    } else {
        BorderStroke(1.dp, Color(0xFF242A38))
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF161922)),
        border = cardBorder
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {

                    val (rarityBg, rarityColor) = when (hero.rarity) {
                        "MYTHIC" -> Pair(Color(0xFF381423), Color(0xFFFF3366))
                        "LEGENDARY" -> Pair(Color(0xFF3B250D), Color(0xFFFFB800))
                        else -> Pair(Color(0xFF281C3D), Color(0xFFA855F7))
                    }
                    Box(
                        modifier = Modifier
                            .background(rarityBg, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = hero.rarity,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = rarityColor
                        )
                    }

                    Box(
                        modifier = Modifier
                            .background(Color(0xFF152821), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = hero.heroClass,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF22C55E)
                        )
                    }
                }


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color(0xFFFFB800),
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = "${hero.cp} CP",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFFB800)
                    )

                    if (hero.isInSquad) {
                        Box(
                            modifier = Modifier
                                .border(1.dp, Color(0xFFFFB800), RoundedCornerShape(4.dp))
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "IN SQUAD",
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFFB800)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(68.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF222836))
                        .border(1.dp, Color(0xFF374151), RoundedCornerShape(12.dp))
                ) {
                    Image(
                        painter = painterResource(id = hero.imageRes),
                        contentDescription = hero.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 2.dp)
                            .background(Color(0xCC000000), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 1.dp)
                    ) {
                        Text(
                            text = "Lvl ${hero.level}",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = hero.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    StatBar(
                        label = "HP",
                        current = hero.currentHp,
                        max = hero.maxHp,
                        barColor = Color(0xFFEF4444)
                    )

                    StatBar(
                        label = "MP",
                        current = hero.currentMp,
                        max = hero.maxMp,
                        barColor = Color(0xFF3B82F6)
                    )
                }

                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color(0xFF64748B),
                    modifier = Modifier.size(22.dp)
                )
            }

            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(animationSpec = tween(300)),
                exit = shrinkVertically(animationSpec = tween(300))
            ) {
                Column(modifier = Modifier.padding(top = 16.dp)) {
                    HorizontalDivider(color = Color(0xFF262C3A), thickness = 1.dp)
                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        StatBox(label = "ATK", value = hero.atk.toString(), modifier = Modifier.weight(1f))
                        StatBox(label = "DEF", value = hero.def.toString(), modifier = Modifier.weight(1f))
                        StatBox(label = "CRIT", value = hero.crit, modifier = Modifier.weight(1f))
                        StatBox(label = "SPD", value = hero.spd.toString(), modifier = Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF11141C)),
                        border = BorderStroke(1.dp, Color(0xFF212735))
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = Color(0xFFFFB800),
                                    modifier = Modifier.size(14.dp)
                                )
                                Text(
                                    text = hero.skillName,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFFFB800)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = hero.skillDesc,
                                fontSize = 11.sp,
                                color = Color(0xFF94A3B8),
                                lineHeight = 16.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = {},
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, Color(0xFF1E3A2F)),
                            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color(0xFF13221C))
                        ) {
                            Text(
                                text = "🧪 Heal (+250 HP)",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF22C55E)
                            )
                        }

                        if (hero.isInSquad) {
                            OutlinedButton(
                                onClick = {},
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(1.dp, Color(0xFF4C1D24)),
                                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color(0xFF281318))
                            ) {
                                Text(
                                    text = "✕ Remove Squad",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFFF4D4D)
                                )
                            }
                        } else {
                            OutlinedButton(
                                onClick = {},
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(1.dp, Color(0xFF42280A)),
                                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color(0xFF221609))
                            ) {
                                Text(
                                    text = "✕ Add to Squad",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFFFB800)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun StatBar(
    label: String,
    current: Int,
    max: Int,
    barColor: Color
) {
    val progress = (current.toFloat() / max.toFloat()).coerceIn(0f, 1f)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF64748B),
            modifier = Modifier.width(20.dp)
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .height(6.dp)
                .clip(CircleShape)
                .background(Color(0xFF242B38))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction = progress)
                    .clip(CircleShape)
                    .background(barColor)
            )
        }

        Text(
            text = "$current / $max",
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF94A3B8)
        )
    }
}

@Composable
fun StatBox(label: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF10131A)),
        border = BorderStroke(1.dp, Color(0xFF212735))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF64748B)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = value,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PartyRosterPreview() {
    VP_Week4Theme {
        PartyRosterScreen()
    }
}