package com.example.myfirstapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myfirstapplication.ui.theme.MyFirstApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
            }
        }
    }




@Composable
fun UnitConverter(modifier: Modifier) {

    var inputValue by remember { mutableStateOf("")}
    var outputValue by remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Meters")}
    var outputUnit by remember { mutableStateOf("Meters")}
    var iExpand by remember { mutableStateOf(false)}
    var oExpand by remember { mutableStateOf(false)}
    val conversationFactor = remember { mutableStateOf(1.00)}
    val oConversationFactor = remember { mutableStateOf(1.00)}

    fun convertFactor (){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversationFactor.value * 100.0 / oConversationFactor.value) / 100
        outputValue = result.toString()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text( "Unit Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.padding(16.dp))
        OutlinedTextField(value = inputValue , onValueChange ={inputValue = it} ,
            label = { Text(text = "Enter Value .....")})
        Spacer(modifier = Modifier.padding(16.dp))
        Row {
            Box {
                Button(onClick = { iExpand = true }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "")
                }
                DropdownMenu(expanded = iExpand, onDismissRequest = { iExpand = false }) {
                    DropdownMenuItem(text = { Text(text = "CentiMeter")},
                        onClick = {
                            iExpand = false
                            inputUnit = "CentiMeter"
                            conversationFactor.value = 0.01
                            convertFactor()
                        })
                    DropdownMenuItem(text = { Text( "Meter")},
                        onClick = {
                            iExpand = false
                            inputUnit = "Meter"
                            conversationFactor.value = 1.0
                            convertFactor()
                        })
                    DropdownMenuItem(text = { Text( "Feet")},
                        onClick = {
                            iExpand = false
                            inputUnit = "Feet"
                            conversationFactor.value = 0.3048
                            convertFactor()
                        })
                    /*DropdownMenuItem(text = { Text( "MilliMeter")},
                        onClick = {
                            iExpand = false
                            inputUnit = "MilliMeter"
                            conversationFactor.value = 0.001
                            convertFactor()
                        })*/
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oExpand = true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "")
                }
                DropdownMenu(expanded = oExpand, onDismissRequest = { oExpand = false }) {
                    DropdownMenuItem(text = { Text( "CentiMeter")},
                        onClick = {
                            oExpand = false
                            outputUnit = "CentiMeter"
                            oConversationFactor.value = 0.01
                            convertFactor()
                        })
                    DropdownMenuItem(text = { Text( "Meter")},
                        onClick = {
                            oExpand = false
                            outputUnit = "Meter"
                            oConversationFactor.value = 1.0
                            convertFactor()
                        })
                    DropdownMenuItem(text = { Text( "Feet")},
                        onClick = {
                            oExpand = false
                            outputUnit = "Feet"
                            oConversationFactor.value = 0.3048
                            convertFactor()
                        })
                    /*DropdownMenuItem(text = { Text( "MilliMeter")},
                        onClick = {
                            oExpand = false
                            outputUnit = "MilliMeter"
                            conversationFactor.value = 0.001
                            convertFactor()
                        })*/
                }
            }
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Text("Result :${outputValue} (${outputUnit})",
            style = MaterialTheme.typography.headlineMedium)
    }
}

