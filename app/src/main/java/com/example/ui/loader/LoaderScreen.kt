package com.example.ui.loader

import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoaderScreen(
    onUnlockSuccess: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    // Activation Key states
    var activationKey by remember { mutableStateOf("VEX6-X-FREE-TAHIM") }
    var rememberKey by remember { mutableStateOf(true) }

    // High Tech validation states
    var isProcessing by remember { mutableStateOf(false) }
    var validationProgress by remember { mutableFloatStateOf(0f) }
    var currentProgressMessage by remember { mutableStateOf("Awaiting authentication handshake...") }
    var validationSucceeded by remember { mutableStateOf<Boolean?>(null) } // null = idle, true = success, false = failed

    // Custom terminal logger feed
    val terminalOutputList = remember { mutableStateListOf<String>() }
    var showTerminalPanel by remember { mutableStateOf(true) }

    // Interactive dialog triggers
    var showGetKeyDialog by remember { mutableStateOf(false) }
    var showTelegramDialog by remember { mutableStateOf(false) }

    // Key Generator simulation
    var isGeneratingKey by remember { mutableStateOf(false) }
    var generatedBypassKey by remember { mutableStateOf("") }

    // Pulsing animations for high-tech alerts
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseAlpha"
    )

    // Key Match validation sequence simulation
    fun checkKeyMatch() {
        if (isProcessing) return

        scope.launch {
            isProcessing = true
            validationSucceeded = null
            validationProgress = 0.0f
            terminalOutputList.clear()

            // Step 1: Bootstrap decrypter
            terminalOutputList.add("[•] Initializing decrypter daemon core...")
            currentProgressMessage = "Initializing Decrypter Daemon..."
            validationProgress = 0.15f
            delay(500)

            // Step 2: Establish Socket Gateway
            terminalOutputList.add("[•] Synchronizing with auth-gateway secure servers...")
            currentProgressMessage = "Connecting secure servers..."
            validationProgress = 0.35f
            delay(600)

            if (activationKey.trim().isEmpty()) {
                activationKey = "VEX_AUTO_" + (1000..9999).random().toString()
            }

            // Step 3: Fingerprint Verification
            terminalOutputList.add("[•] Analyzing device unique hardware registers...")
            currentProgressMessage = "Matching device signature..."
            validationProgress = 0.55f
            delay(500)

            // Step 4: Registry query
            terminalOutputList.add("[•] Querying activation database index for '$activationKey'...")
            currentProgressMessage = "Checking license registers..."
            validationProgress = 0.80f
            delay(700)

            // Evaluate success or failure
            val isExpiredMock = activationKey.trim() == "VEX6-EXPIRED"
            val isMatch = !isExpiredMock && activationKey.trim().isNotEmpty()

            terminalOutputList.add("[•] DB Integrity Hash verification package received.")
            validationProgress = 1.0f
            delay(400)

            if (isMatch) {
                terminalOutputList.add("[✓] ACCESS GRANTED! Verification signature verified successfully.")
                terminalOutputList.add("[🚀] Injecting VEX6 MODE dynamic cheat registers...")
                currentProgressMessage = "Security matches! Bootstrapping core..."
                validationSucceeded = true
                delay(1200)
                isProcessing = false
                Toast.makeText(context, "✅ Validation Successful! Menu Unlocked.", Toast.LENGTH_SHORT).show()
                onUnlockSuccess()
            } else {
                terminalOutputList.add("[❌] MISMATCH EXCEPTION! Invalid code payload structure.")
                terminalOutputList.add("[!] Access Denied. Check key validity in official channels.")
                currentProgressMessage = "Access Denied! Key code invalid."
                validationSucceeded = false
                delay(800)
                isProcessing = false
            }
        }
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
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState)
                .windowInsetsPadding(WindowInsets.safeDrawing),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Upper Logo / Header Block (Hacker styling)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .background(CyberGreyMedium, shape = RoundedCornerShape(100.dp))
                        .border(2.5.dp, CyberRed, RoundedCornerShape(100.dp))
                        .shadow(12.dp, shape = RoundedCornerShape(100.dp), spotColor = CyberRed),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_vex6_logo),
                        contentDescription = "VEX6 Premium Logo",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(100.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "VEX6 X TAHIM",
                    color = CyberWhite,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 2.5.sp
                )

                Text(
                    text = "VEX6 MODE",
                    color = NeonCyan,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 2.sp
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "VEX6 PAPA",
                    color = CyberWhite.copy(alpha = 0.6f),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.SansSerif,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Beautiful High-Tech Input Card
            Card(
                colors = CardDefaults.cardColors(containerColor = CyberGreyDark),
                border = BorderStroke(
                    width = 1.2.dp,
                    brush = Brush.horizontalGradient(
                        colors = if (validationSucceeded == false) {
                            listOf(CyberRed, CyberRed.copy(0.4f))
                        } else if (validationSucceeded == true) {
                            listOf(CyberGreen, CyberGreen.copy(0.4f))
                        } else if (isProcessing) {
                            listOf(CyberGold, CyberGold.copy(0.2f))
                        } else {
                            listOf(NeonCyan.copy(0.8f), Color.Transparent)
                        }
                    )
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(16.dp, spotColor = NeonCyan.copy(alpha = 0.3f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "HIGH-TECH ACTIVATION PORTAL",
                            color = NeonCyan,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                            letterSpacing = 1.2.sp
                        )

                        // Status pill
                        Box(
                            modifier = Modifier
                                .background(
                                    color = when (validationSucceeded) {
                                        true -> CyberGreen.copy(alpha = 0.15f)
                                        false -> CyberRed.copy(alpha = 0.15f)
                                        else -> if (isProcessing) CyberGold.copy(alpha = 0.15f) else CyberGreyMedium
                                    },
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = when (validationSucceeded) {
                                    true -> "AUTHORIZED"
                                    false -> "INVALID KEY"
                                    else -> if (isProcessing) "SYNCING" else "IDLE"
                                },
                                color = when (validationSucceeded) {
                                    true -> CyberGreen
                                    false -> CyberRed
                                    else -> if (isProcessing) CyberGold else CyberWhite.copy(0.6f)
                                },
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Input field styled with neon custom aesthetics
                    OutlinedTextField(
                        value = activationKey,
                        onValueChange = {
                            if (!isProcessing) {
                                activationKey = it
                                validationSucceeded = null // reset validation display to allow retrying
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = transparentTextFieldColors(),
                        singleLine = true,
                        placeholder = {
                            Text(
                                "Enter License Password...",
                                color = CyberWhite.copy(alpha = 0.3f),
                                fontFamily = FontFamily.Monospace,
                                fontSize = 13.sp
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Lock,
                                contentDescription = "Key Icon",
                                tint = if (validationSucceeded == false) CyberRed else NeonCyan,
                                modifier = Modifier.size(18.dp)
                            )
                        },
                        trailingIcon = {
                            if (activationKey.isNotEmpty() && !isProcessing) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "Clear Input",
                                    tint = CyberWhite.copy(0.4f),
                                    modifier = Modifier
                                        .size(18.dp)
                                        .clickable { activationKey = "" }
                                )
                            }
                        },
                        textStyle = LocalTextStyle.current.copy(
                            color = CyberWhite,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Shortcut Simulation Tags (Lets you choose VEX6-SECURE or VEX6-EXPIRED for immediate feedback testing!)
                    Text(
                        text = "🔬 SIMULATION TARGET PRESETS:",
                        color = CyberWhite.copy(0.4f),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Success Preset Button
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(6.dp))
                                .background(CyberGreyMedium)
                                .border(1.dp, CyberGreen.copy(alpha = 0.4f), RoundedCornerShape(6.dp))
                                .clickable {
                                    if (!isProcessing) {
                                        activationKey = "VEX6-X-FREE-TAHIM"
                                        validationSucceeded = null
                                    }
                                }
                                .padding(vertical = 5.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "🟢 VEX6-FREE (PASS)",
                                color = CyberGreen,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace
                            )
                        }

                        // Expired Preset Button
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(6.dp))
                                .background(CyberGreyMedium)
                                .border(1.dp, CyberRed.copy(alpha = 0.4f), RoundedCornerShape(6.dp))
                                .clickable {
                                    if (!isProcessing) {
                                        activationKey = "VEX6-EXPIRED"
                                        validationSucceeded = null
                                    }
                                }
                                .padding(vertical = 5.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "🔴 EXPIRED (FAIL)",
                                color = CyberRed,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Remember credentials configuration row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { if (!isProcessing) rememberKey = !rememberKey },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = rememberKey,
                            onCheckedChange = { if (!isProcessing) rememberKey = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = CyberGold,
                                uncheckedColor = CyberGreyLight,
                                checkmarkColor = Color.Black
                            ),
                            modifier = Modifier.scale(0.85f)
                        )
                        Text(
                            text = "Save Credentials to Cache",
                            color = CyberWhite.copy(alpha = 0.7f),
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // 1. HIGH-TECH ANIMATED PROGRESS BAR BLOCK (Renders when actively processing key)
            AnimatedVisibility(
                visible = isProcessing,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.Black),
                    border = BorderStroke(1.2.dp, CyberGold.copy(0.7f)),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
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
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CircularProgressIndicator(
                                    color = CyberGold,
                                    strokeWidth = 1.5.dp,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "LIVE HANDSHAKE DECRYPTION",
                                    color = CyberGold,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Monospace
                                )
                            }

                            // Percent Readout
                            Text(
                                text = "${(validationProgress * 100).toInt()}%",
                                color = CyberGold,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Real animated linear progress track
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .background(CyberGreyMedium, RoundedCornerShape(4.dp))
                        ) {
                            val animatedProgressWidth by animateFloatAsState(
                                targetValue = validationProgress,
                                animationSpec = tween(300, easing = LinearOutSlowInEasing),
                                label = "barProgress"
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(animatedProgressWidth)
                                    .background(
                                        Brush.horizontalGradient(
                                            colors = listOf(CyberGold, NeonCyan)
                                        ),
                                        shape = RoundedCornerShape(4.dp)
                                    )
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "► $currentProgressMessage",
                            color = CyberWhite.copy(0.8f),
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }

            // 2. SUCCESS FEEDBACK PANEL
            AnimatedVisibility(
                visible = validationSucceeded == true,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF071F11)),
                    border = BorderStroke(1.5.dp, CyberGreen),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.CheckCircle,
                                contentDescription = "Authorized Sign",
                                tint = CyberGreen,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "AUTH_GRANTED // SYSTEM SECURE",
                                color = CyberGreen,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace,
                                letterSpacing = 1.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "License key cryptographic signature is verified in high-performance registers. Bypass layers are successfully hooked to Free Fire. Launch game menu unlocked.",
                            color = CyberWhite.copy(0.85f),
                            fontSize = 11.sp,
                            lineHeight = 15.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Divider(color = CyberGreen.copy(0.2f), thickness = 1.dp)
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            Text(
                                text = "USER: VEX6_ELITE",
                                color = CyberGreen.copy(0.7f),
                                fontSize = 9.sp,
                                fontFamily = FontFamily.Monospace
                            )
                            Text(
                                text = "ROLE: PREMIUM ACCESS",
                                color = CyberGreen.copy(0.7f),
                                fontSize = 9.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }
            }

            // 3. ERROR FEEDBACK PANEL
            AnimatedVisibility(
                visible = validationSucceeded == false,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF260F13)),
                    border = BorderStroke(1.5.dp, CyberRed),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.Warning,
                                contentDescription = "Auth Mismatch Warning",
                                tint = CyberRed,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "AUTH_BLOCKED // INCORRECT INTEGRITY KEY",
                                color = CyberRed,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace,
                                letterSpacing = 1.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Error code #HW902: The activation credentials provided were not authenticated by our database or had reached their maximum expiry timestamp bounds.",
                            color = CyberWhite.copy(0.85f),
                            fontSize = 11.sp,
                            lineHeight = 15.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        DecoderHelperButton(
                            text = "GENERATE A FREE WORKING BYPASS PASS",
                            color = CyberGold,
                            onClick = { showGetKeyDialog = true }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Main UNLOCK button (glowing, reacts cleanly)
            Button(
                onClick = { checkKeyMatch() },
                enabled = !isProcessing,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = when {
                        validationSucceeded == false -> CyberRed
                        validationSucceeded == true -> CyberGreen
                        isProcessing -> CyberGold.copy(alpha = 0.2f)
                        else -> NeonCyan
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .border(
                        width = 1.2.dp,
                        color = when {
                            validationSucceeded == false -> CyberRed
                            validationSucceeded == true -> CyberGreen
                            isProcessing -> CyberGold
                            else -> NeonCyan.copy(alpha = 0.5f)
                        },
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (validationSucceeded == true) Icons.Filled.CheckCircle else Icons.Filled.PlayArrow,
                        contentDescription = "Unlock System Mod Menu",
                        tint = if (isProcessing) CyberGold else Color.Black,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = when {
                            validationSucceeded == true -> "GRANTED // UNLOCKING..."
                            validationSucceeded == false -> "RETRACT & RE-VERIFY KEY"
                            isProcessing -> "PERFORMING SOCKET QUERY..."
                            else -> "UNLOCK CLIENT MENU"
                        },
                        color = if (isProcessing) CyberGold else Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        letterSpacing = 1.2.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Clipboard Action Utility Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Paste Secure Key
                Button(
                    onClick = {
                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = clipboard.primaryClip
                        if (clip != null && clip.itemCount > 0) {
                            val text = clip.getItemAt(0).text
                            if (!text.isNullOrBlank()) {
                                activationKey = text.toString()
                                validationSucceeded = null
                                Toast.makeText(context, "Key loaded from clipboard!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "No text found in clipboard!", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            activationKey = "VEX6-X-FREE-TAHIM"
                            validationSucceeded = null
                            Toast.makeText(context, "Auto test pass loaded!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CyberGreyDark),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .border(1.dp, CyberGreyMedium, RoundedCornerShape(10.dp))
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Paste",
                        tint = CyberWhite.copy(0.8f),
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "PASTE Secure",
                        color = CyberWhite.copy(0.9f),
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }

                // Generate key helper dialog trigger
                Button(
                    onClick = { showGetKeyDialog = true },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CyberGreyDark),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .border(1.dp, CyberGreyMedium, RoundedCornerShape(10.dp))
                ) {
                    Icon(
                        imageVector = Icons.Filled.Build,
                        contentDescription = "Generate Credentials",
                        tint = CyberWhite.copy(0.8f),
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "GET FREE KEY",
                        color = CyberWhite.copy(0.9f),
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Primary Community Connection Panel
            Button(
                onClick = { showTelegramDialog = true },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = CyberGreyDark),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .border(1.dp, CyberBlue, RoundedCornerShape(12.dp))
            ) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = "Telegram Link Icon",
                    tint = CyberBlue,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "VISIT OFFICIAL TELEGRAM",
                    color = CyberBlue,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Real-Time Terminal Log Feed (High Tech scrollable diagnostics output)
            AnimatedVisibility(
                visible = showTerminalPanel && terminalOutputList.isNotEmpty(),
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.Black),
                    border = BorderStroke(1.dp, CyberGreyMedium),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
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
                            Text(
                                text = "CORE AUTH TERMINAL STREAM",
                                color = CyberGold,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace
                            )
                            Icon(
                                imageVector = Icons.Filled.Refresh,
                                contentDescription = "Clear logs",
                                tint = CyberWhite.copy(0.3f),
                                modifier = Modifier
                                    .size(16.dp)
                                    .clickable { terminalOutputList.clear() }
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        terminalOutputList.forEach { logLine ->
                            Text(
                                text = logLine,
                                color = if (logLine.startsWith("[❌]")) {
                                    CyberRed
                                } else if (logLine.contains("[✓]")) {
                                    CyberGreen
                                } else if (logLine.contains("[🚀]")) {
                                    CyberGold
                                } else {
                                    NeonCyan
                                },
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace,
                                modifier = Modifier.padding(vertical = 1.5.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Centralized Signature Footer
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                Text(
                    text = "VEX6 X TAHIM",
                    color = CyberGold.copy(alpha = 0.5f),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 2.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "VEX6 MODE",
                    color = NeonCyan.copy(alpha = 0.4f),
                    fontSize = 9.sp,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "VEX6 PAPA",
                    color = CyberWhite.copy(alpha = 0.3f),
                    fontSize = 8.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
        }
    }

    // Modal dialogs for fully interactive features
    if (showGetKeyDialog) {
        AlertDialog(
            onDismissRequest = { if (!isGeneratingKey) showGetKeyDialog = false },
            containerColor = CyberGreyDark,
            tonalElevation = 8.dp,
            modifier = Modifier.border(BorderStroke(1.5.dp, CyberGold), RoundedCornerShape(28.dp)),
            title = {
                Text(
                    text = "🔑 Cyber Key Generator",
                    color = CyberGold,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
            },
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "To bypass license guards, spawn a temporary 24-hour credential register matching your unique hardware hash.",
                        color = CyberWhite.copy(alpha = 0.8f),
                        fontSize = 12.sp,
                        lineHeight = 17.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    if (isGeneratingKey) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(
                                color = CyberGold,
                                strokeWidth = 2.5.dp,
                                modifier = Modifier.size(34.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Querypoint handshake tunneling active...",
                                color = NeonCyan,
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    } else if (generatedBypassKey.isNotEmpty()) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "OBTAINED PASSCODE:",
                                color = CyberWhite.copy(alpha = 0.6f),
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Card(
                                colors = CardDefaults.cardColors(containerColor = Color.Black),
                                border = BorderStroke(1.dp, NeonCyan),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = generatedBypassKey,
                                    color = CyberGreen,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Monospace,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Press below to load code immediately!",
                                color = CyberYellow,
                                fontSize = 9.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    } else {
                        Button(
                            onClick = {
                                scope.launch {
                                    isGeneratingKey = true
                                    delay(1600)
                                    isGeneratingKey = false
                                    generatedBypassKey = "VEX6-X-FREE-TAHIM"
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = CyberGold),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "GENERATE CODES",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            },
            confirmButton = {
                if (generatedBypassKey.isNotEmpty()) {
                    TextButton(
                        onClick = {
                            activationKey = generatedBypassKey
                            validationSucceeded = null
                            showGetKeyDialog = false
                            Toast.makeText(context, "Key auto-loaded!", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Text(
                            "USE KEY & RUN",
                            color = CyberGreen,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else {
                    TextButton(onClick = { showGetKeyDialog = false }) {
                        Text("CANCEL", color = CyberWhite, fontFamily = FontFamily.Monospace)
                    }
                }
            }
        )
    }

    if (showTelegramDialog) {
        AlertDialog(
            onDismissRequest = { showTelegramDialog = false },
            containerColor = CyberGreyDark,
            modifier = Modifier.border(BorderStroke(1.5.dp, CyberBlue), RoundedCornerShape(28.dp)),
            title = {
                Text(
                    text = "📢 Social Community Hub",
                    color = CyberBlue,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
            },
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Join our community group to retrieve direct keys, wallhacks scripts, FPS optimization configs, and dev updates.",
                        color = CyberWhite.copy(alpha = 0.8f),
                        fontSize = 12.sp,
                        lineHeight = 17.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Card(
                        colors = CardDefaults.cardColors(containerColor = CyberGreyMedium),
                        border = BorderStroke(1.dp, CyberGreyMedium)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "👤 Telegram: @Vex6TahimMod",
                                color = NeonCyan,
                                fontSize = 12.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "👥 Active Members: 12,450",
                                color = CyberWhite.copy(alpha = 0.7f),
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = android.content.ClipData.newPlainText("Telegram Link", "https://t.me/Vex6TahimMod")
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(context, "Link copied! Paste in browser to join.", Toast.LENGTH_LONG).show()
                        showTelegramDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = CyberBlue)
                ) {
                    Text("COPY RECRUIT LINK", color = Color.White, fontFamily = FontFamily.Monospace, fontSize = 11.sp)
                }
            },
            dismissButton = {
                TextButton(onClick = { showTelegramDialog = false }) {
                    Text("CLOSE", color = CyberWhite, fontFamily = FontFamily.Monospace)
                }
            }
        )
    }
}

// Decorative helper button for failed state redirection
@Composable
fun DecoderHelperButton(
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color.copy(alpha = 0.12f))
            .border(1.dp, color.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = color,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
    }
}

// Custom TextField styling helper
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun transparentTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = NeonCyan,
    unfocusedBorderColor = CyberGreyLight,
    disabledBorderColor = CyberGreyLight,
    errorBorderColor = CyberRed,
    focusedContainerColor = Color.Black,
    unfocusedContainerColor = Color.Black,
    disabledContainerColor = Color.Black,
    cursorColor = NeonCyan
)
