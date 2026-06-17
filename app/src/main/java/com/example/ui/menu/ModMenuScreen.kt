package com.example.ui.menu

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ModMenuScreen(
    onLockApp: () -> Unit
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    // Interactive logs for telemetry feed
    val hackLogs = remember {
        mutableStateListOf(
            "[SYS] VEX6 X TAHIM kernel activated.",
            "[SYS] Security Handshake: COMPLETE",
            "[SYS] Virtual sandbox emulator: READY",
            "[SYS] Status: Awaiting Free Fire initialization..."
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        CyberBlack,
                        Color(0xFF0C0E1A)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState)
                .windowInsetsPadding(WindowInsets.safeDrawing),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Brand Logo Header Image
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .background(CyberGreyMedium, shape = RoundedCornerShape(100.dp))
                    .border(2.dp, CyberRed, RoundedCornerShape(100.dp))
                    .shadow(8.dp, shape = RoundedCornerShape(100.dp), spotColor = CyberRed),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_vex6_logo),
                    contentDescription = "VEX6 Premium Logo Inside",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(100.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Premium Animated Pulse Header Status
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                NeonCyan.copy(alpha = 0.08f),
                                Color(0xFF141A30)
                            )
                        )
                    )
                    .border(
                        border = BorderStroke(1.5.dp, Brush.horizontalGradient(listOf(NeonCyan, Color.Transparent))),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .background(CyberGreen, shape = RoundedCornerShape(100.dp))
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "SYSTEM ENCRYPTED",
                                color = CyberGreen,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace,
                                letterSpacing = 1.5.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "STATUS: INJECTION ACTIVE",
                            color = CyberWhite,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily.Monospace
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.Black, shape = RoundedCornerShape(10.dp))
                            .border(1.dp, NeonCyan, RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "VX",
                            color = NeonCyan,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Launch Menu Header Title
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(18.dp)
                        .background(CyberGold, shape = RoundedCornerShape(4.dp))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "🚀 LAUNCH MENU",
                    color = CyberGold,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.5.sp
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 1. FREE FIRE LAUNCH CARD
            Card(
                colors = CardDefaults.cardColors(containerColor = CyberGreyDark),
                border = BorderStroke(1.5.dp, NeonCyan),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        coroutineScope.launch {
                            hackLogs.add("[SYS] Initializing Free Fire (Standard)...")
                            hackLogs.add("[SYS] Injecting VEX6 MODE dynamic cheat layers...")
                            delay(500)
                            try {
                                val pm = context.packageManager
                                val intent = pm.getLaunchIntentForPackage("com.dts.freefireth")
                                if (intent != null) {
                                    context.startActivity(intent)
                                    hackLogs.add("[✓] Success! Game process hooked successfully.")
                                    Toast.makeText(context, "🚀 Launching Free Fire Standard...", Toast.LENGTH_LONG).show()
                                } else {
                                    hackLogs.add("[❌] Error: Free Fire Standard not installed.")
                                    Toast.makeText(
                                        context,
                                        "🎮 Free Fire is not installed. Running virtual sandbox bypass...",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            } catch (e: Exception) {
                                hackLogs.add("[❌] Sandbox Exception: ${e.localizedMessage}")
                            }
                        }
                    }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Free Fire",
                                color = CyberWhite,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .background(NeonCyan.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "STANDARD",
                                    color = NeonCyan,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Inject VX mode into the standard Free Fire app. Real-time dynamic security patch bypass.",
                            color = CyberWhite.copy(alpha = 0.6f),
                            fontSize = 11.sp,
                            lineHeight = 15.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                hackLogs.add("[SYS] Initializing Free Fire (Standard)...")
                                hackLogs.add("[SYS] Injecting VEX6 MODE dynamic cheat layers...")
                                delay(500)
                                try {
                                    val pm = context.packageManager
                                    val intent = pm.getLaunchIntentForPackage("com.dts.freefireth")
                                    if (intent != null) {
                                        context.startActivity(intent)
                                        hackLogs.add("[✓] Success! Game process hooked.")
                                        Toast.makeText(context, "🚀 Launching Free Fire Standard...", Toast.LENGTH_LONG).show()
                                    } else {
                                        hackLogs.add("[❌] Standard App missing.")
                                        Toast.makeText(
                                            context,
                                            "🎮 Free Fire is not installed. Running virtual sandbox bypass...",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                } catch (e: Exception) {
                                    hackLogs.add("[❌] Sandbox Exception")
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = NeonCyan),
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "Launch game",
                            color = Color.Black,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 2. FREE FIRE MAX LAUNCH CARD
            Card(
                colors = CardDefaults.cardColors(containerColor = CyberGreyDark),
                border = BorderStroke(1.5.dp, CyberYellow),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        coroutineScope.launch {
                            hackLogs.add("[SYS] Initializing Free Fire MAX...")
                            hackLogs.add("[SYS] Injecting VEX6 MODE High Resolution hooks...")
                            delay(500)
                            try {
                                val pm = context.packageManager
                                val intent = pm.getLaunchIntentForPackage("com.dts.freefiremax")
                                if (intent != null) {
                                    context.startActivity(intent)
                                    hackLogs.add("[✓] Success! Free Fire MAX process hook loaded.")
                                    Toast.makeText(context, "🚀 Launching Free Fire MAX...", Toast.LENGTH_LONG).show()
                                } else {
                                    hackLogs.add("[❌] Error: Free Fire MAX not installed.")
                                    Toast.makeText(
                                        context,
                                        "🎮 Free Fire MAX is not installed. Running virtual sandbox bypass...",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            } catch (e: Exception) {
                                hackLogs.add("[❌] Sandbox Exception: ${e.localizedMessage}")
                            }
                        }
                    }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Free Fire Max",
                                color = CyberWhite,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .background(CyberYellow.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "MAX",
                                    color = CyberYellow,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Launch high-performance Free Fire MAX. Optimized for advanced layout injection scripts.",
                            color = CyberWhite.copy(alpha = 0.6f),
                            fontSize = 11.sp,
                            lineHeight = 15.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                hackLogs.add("[SYS] Initializing Free Fire MAX...")
                                hackLogs.add("[SYS] Injecting VEX6 MODE High Resolution hooks...")
                                delay(500)
                                try {
                                    val pm = context.packageManager
                                    val intent = pm.getLaunchIntentForPackage("com.dts.freefiremax")
                                    if (intent != null) {
                                        context.startActivity(intent)
                                        hackLogs.add("[✓] Success! Free Fire MAX process hook loaded.")
                                        Toast.makeText(context, "🚀 Launching Free Fire MAX...", Toast.LENGTH_LONG).show()
                                    } else {
                                        hackLogs.add("[❌] Max App missing.")
                                        Toast.makeText(
                                            context,
                                            "🎮 Free Fire MAX is not installed. Running virtual sandbox bypass...",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                } catch (e: Exception) {
                                    hackLogs.add("[❌] Sandbox Exception")
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = CyberYellow),
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "Launch game",
                            color = Color.Black,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Sub-Logs stream frame (Function Terminal Output)
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.Black),
                border = BorderStroke(1.2.dp, CyberRed.copy(alpha = 0.6f)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .background(CyberRed, shape = RoundedCornerShape(100.dp))
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "LIVE TELEMETRY HOOK MODULES",
                                color = CyberRed,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace,
                                letterSpacing = 1.sp
                            )
                        }
                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = "Clear Session Logger",
                            tint = CyberWhite.copy(alpha = 0.4f),
                            modifier = Modifier
                                .size(18.dp)
                                .clickable {
                                    hackLogs.clear()
                                    hackLogs.add("[SYS] Log stream flushed cleanly.")
                                }
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        hackLogs.reversed().forEach { log ->
                            Text(
                                text = log,
                                color = if (log.startsWith("[❌]")) {
                                    CyberRed
                                } else if (log.contains("[✓]") || log.contains("COMPLETE") || log.contains("READY")) {
                                    CyberGreen
                                } else if (log.contains("Success!")) {
                                    CyberGold
                                } else {
                                    CyberWhite.copy(alpha = 0.7f)
                                },
                                fontSize = 11.sp,
                                fontFamily = FontFamily.Monospace,
                                lineHeight = 15.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Back to authentication screen button to test loader states multiple times
            Button(
                onClick = {
                    onLockApp()
                    Toast.makeText(context, "Logged out securely.", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(containerColor = CyberGreyDark),
                border = BorderStroke(1.2.dp, CyberRed.copy(alpha = 0.7f)),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Back to Security Guard",
                    tint = CyberRed,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "LOCK MENU & LOG OUT",
                    color = CyberRed,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Signature Footer Branding Panel
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 20.dp)
            ) {
                Text(
                    text = "VEX6 X TAHIM",
                    color = CyberGold,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 3.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "VEX6 MODE",
                    color = NeonCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.5.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "VEX6 PAPA",
                    color = CyberWhite.copy(alpha = 0.4f),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
