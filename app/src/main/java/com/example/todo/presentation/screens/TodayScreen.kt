package com.example.todo.presentation.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo.R
import com.example.todo.presentation.viewmodels.TodayViewModel

@Composable
fun TodayScreen(modifier: Modifier, vm: TodayViewModel = viewModel()) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {}
            ) {
                Icon(Icons.Filled.Add,"")
            }
        }
    ) { innerPadding ->
        Column(
            Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(start = 15.dp, top = 40.dp),
                    text = stringResource(R.string.today),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )
                //todo
            }
            Text(
                modifier = Modifier.padding(start = 15.dp, top = 15.dp, bottom = 15.dp),
                text = "14 march · Friday",
                fontWeight = FontWeight.SemiBold
            )
            HorizontalDivider(color = Color.Gray, thickness = 1.dp)

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(vm.notesState.size) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            RadioButton(selected = false, onClick = {})
                            Column {
                                Text(text = vm.notesState[it].title)
                                Text(
                                    text =  vm.notesState[it].note,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}



