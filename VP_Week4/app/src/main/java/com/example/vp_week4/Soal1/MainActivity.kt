package com.example.vp_week4.Soal1

import com.example.vp_week4.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
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

// 1. Model Data Mobil
data class Car(
    val id: Int,
    val name: String,
    val city: String,
    val price: String,
    val year: String,
    val mileage: String,
    val transmission: String,
    val fuelType: String,
    val description: String,
    val isFavorite: Boolean = false,
    val imageRes: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VP_Week4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CarApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CarApp(modifier: Modifier = Modifier) {
    var carList by remember {
        mutableStateOf(
            listOf(
                Car(1, "Tiggo 9 CSH", "Jakarta", "Rp 450.000.000", "2024", "5,000 km", "Auto", "PHEV", "Chery Tiggo 9 CSH combines luxurious comfort with state-of-the-art plug-in hybrid technology.", true, R.drawable.tiggo),
                Car(2, "Honda CRV", "Bandung", "Rp 210.000.000", "2013", "15,200 km", "Auto", "PETROL", "Reliable family SUV with spacious cabin and solid durability across long distances.", false, R.drawable.crv),
                Car(3, "Toyota Yaris Cross", "Surabaya", "Rp 398.000.000", "2024", "30,500 km", "Auto", "Hybrid", "Toyota's stylish crossover pairs a 1.5L hybrid system with a bold design that turns heads on any road. The Yaris Cross features Toyota Safety Sense, an 8\" touchscreen, wireless Apple CarPlay, and an efficient self-charging hybrid drivetrain - perfect for urban commuters.", true, R.drawable.yariscross),
                Car(4, "Daihatsu Xenia", "Surabaya", "Rp 185.000.000", "2022", "28,000 km", "Manual", "PETROL", "Efficient and affordable 7-seater MPV built for comfort and low fuel consumption.", false, R.drawable.xenia),
                Car(5, "Toyota Yaris", "Semarang", "Rp 245.000.000", "2021", "52,400 km", "Auto", "Petrol", "The Toyota Yaris G AT is a sporty compact hatchback that punches above its weight class. Equipped with a 1.5L dual VVT-i engine, 7 airbags, pre-collision system, and a floating head unit, it offers premium features at a very accessible price point.", true, R.drawable.yaris),
                Car(6, "Honda HRV", "Jakarta", "Rp 320.000.000", "2022", "18,900 km", "Auto", "PETROL", "Sleek compact crossover offering smart connectivity and dynamic driving feel.", false, R.drawable.hrv),
                Car(7, "Daihatsu Ayla", "Surabaya", "Rp 115.000.000", "2020", "45,000 km", "Manual", "PETROL", "Agile city car ideal for daily city commuting with excellent fuel economy.", false, R.drawable.ayla),
                Car(8, "Jaecoo J5", "Tangerang", "Rp 380.000.000", "2025", "1,200 km", "Auto", "ELECTRIC", "Next-gen compact electric SUV offering impressive range and futuristic tech cabin.", true, R.drawable.j5)
            )
        )
    }

    // State untuk navigasi antar halaman
    var selectedCarId by remember { mutableStateOf<Int?>(null) }
    val currentSelectedCar = carList.find { it.id == selectedCarId }

    // Toggle fungsi favorit
    val onToggleFavorite: (Int) -> Unit = { id ->
        carList = carList.map { car ->
            if (car.id == id) car.copy(isFavorite = !car.isFavorite) else car
        }
    }

    // Tombol Back fisik HP kembali ke halaman list
    BackHandler(enabled = selectedCarId != null) {
        selectedCarId = null
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF9F7F2))
    ) {
        if (currentSelectedCar != null) {
            // Halaman Detail Mobil (Bonus)
            CarDetailScreen(
                car = currentSelectedCar,
                onBack = { selectedCarId = null },
                onToggleFavorite = { onToggleFavorite(currentSelectedCar.id) }
            )
        } else {
            // Halaman Utama List Mobil
            CarListScreen(
                cars = carList,
                onCarClick = { car -> selectedCarId = car.id },
                onToggleFavorite = onToggleFavorite
            )
        }
    }
}

// ==========================================
// 1. HALAMAN LIST MOBIL (ON SALE)
// ==========================================
@Composable
fun CarListScreen(
    cars: List<Car>,
    onCarClick: (Car) -> Unit,
    onToggleFavorite: (Int) -> Unit
) {
    val totalSaved = cars.count { it.isFavorite }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Indikator Garis Oranye
            Box(
                modifier = Modifier
                    .width(42.dp)
                    .height(4.dp)
                    .background(Color(0xFFFF5A36), RoundedCornerShape(50))
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Baris Judul & Badge "X saved"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "On Sale",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF101828)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Find your perfect ride",
                        fontSize = 14.sp,
                        color = Color(0xFF8E95A3)
                    )
                }

                // Badge "X saved"
                Row(
                    modifier = Modifier
                        .border(1.dp, Color(0xFFFFD1D1), RoundedCornerShape(50))
                        .background(Color(0xFFFFF4F2), RoundedCornerShape(50))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Saved",
                        tint = Color(0xFFFF5A36),
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "$totalSaved saved",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF5A36)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        // List Mobil menggunakan CARD
        items(cars, key = { it.id }) { car ->
            CarListItemCard(
                car = car,
                onClick = { onCarClick(car) },
                onToggleFavorite = { onToggleFavorite(car.id) }
            )
        }
    }
}

@Composable
fun CarListItemCard(
    car: Car,
    onClick: () -> Unit,
    onToggleFavorite: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clickable { onClick() }, // Klik pada bodi kartu membuka detail
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Gambar Mobil + Badge Fuel
            Box(
                modifier = Modifier
                    .size(106.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFF2F4F7))
            ) {
                Image(
                    painter = painterResource(id = car.imageRes),
                    contentDescription = car.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Label Fuel Type di sudut kiri bawah
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(6.dp)
                        .background(
                            when (car.fuelType.uppercase()) {
                                "HYBRID" -> Color(0xFFE4F4EC)
                                "PHEV", "ELECTRIC" -> Color(0xFFEDE8FD)
                                else -> Color(0xFFFFF2DC)
                            },
                            RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = car.fuelType.uppercase(),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = when (car.fuelType.uppercase()) {
                            "HYBRID" -> Color(0xFF288C5E)
                            "PHEV", "ELECTRIC" -> Color(0xFF674AE2)
                            else -> Color(0xFFC77800)
                        }
                    )
                }
            }

            // Info Mobil
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Baris Nama Mobil & Tombol Love
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = car.name,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1D2939),
                        modifier = Modifier.weight(1f)
                    )

                    // Tombol Love Interaktif (Box dengan clickable tersendiri)
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .clickable { onToggleFavorite() }, // Toggle status favorit
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (car.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (car.isFavorite) Color(0xFFFF5A36) else Color(0xFFB5BAC3),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                // Tag Transmission
                Box(
                    modifier = Modifier
                        .background(Color(0xFFF2F4F7), RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = car.transmission,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF667085)
                    )
                }

                // Tahun & Mileage
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(5.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFF5A36))
                        )
                        Text(
                            text = car.year,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF1D2939)
                        )
                    }

                    Text(
                        text = car.mileage,
                        fontSize = 12.sp,
                        color = Color(0xFF667085)
                    )
                }
            }
        }
    }
}


@Composable
fun CarDetailScreen(
    car: Car,
    onBack: () -> Unit,
    onToggleFavorite: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F7F2))
            .verticalScroll(scrollState)
    ) {
        // Bagian Header Gambar + Tombol Back
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Image(
                painter = painterResource(id = car.imageRes),
                contentDescription = car.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Tombol Back bulat
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .padding(start = 16.dp, top = 20.dp)
                    .size(40.dp)
                    .background(Color.White, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }

            // Badge Fuel Type di kiri bawah foto
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
                    .background(Color.White.copy(alpha = 0.9f), RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = car.fuelType.uppercase(),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF288C5E)
                )
            }
        }

        // Konten Informasi Mobil
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Nama Mobil & Tombol Like
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = car.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF101828)
                )

                IconButton(onClick = onToggleFavorite) {
                    Icon(
                        imageVector = if (car.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (car.isFavorite) Color(0xFFFF5A36) else Color(0xFFB5BAC3),
                        modifier = Modifier.size(26.dp)
                    )
                }
            }

            // Lokasi Kota
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFF5A36))
                )
                Text(
                    text = car.city,
                    fontSize = 14.sp,
                    color = Color(0xFF667085)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Badge Harga
            Box(
                modifier = Modifier
                    .border(1.dp, Color(0xFFFFE0D9), RoundedCornerShape(8.dp))
                    .background(Color(0xFFFFF7F5), RoundedCornerShape(8.dp))
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            ) {
                Text(
                    text = car.price,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF5A36)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Section Specifications
            Text(
                text = "SPECIFICATIONS",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF98A2B3),
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 4 Grid Spesifikasi Mobil
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SpecBox(label = "YEAR", value = car.year, modifier = Modifier.weight(1f))
                SpecBox(label = "MILEAGE", value = car.mileage, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SpecBox(label = "TRANSMISSION", value = car.transmission, modifier = Modifier.weight(1f))
                SpecBox(label = "FUEL TYPE", value = car.fuelType, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Section About This Car
            Text(
                text = "ABOUT THIS CAR",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF98A2B3),
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = car.description,
                fontSize = 13.sp,
                color = Color(0xFF667085),
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Bottom Action Bar: Tombol Hati & Tombol Contact Seller
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tombol Love kecil di kiri
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .border(1.dp, Color(0xFFFFD1D1), RoundedCornerShape(12.dp))
                        .background(Color(0xFFFFF4F2), RoundedCornerShape(12.dp))
                        .clickable { onToggleFavorite() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (car.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Save",
                        tint = Color(0xFFFF5A36),
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Tombol Contact Seller
                Button(
                    onClick = { /* Aksi kontak penjual */ },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5A36))
                ) {
                    Text(
                        text = "Contact Seller",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

// Komponen Kotak Spesifikasi
@Composable
fun SpecBox(label: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(72.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = label,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF98A2B3)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1D2939)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CarAppPreview() {
    VP_Week4Theme {
        CarApp()
    }
}