package com.example.todo.presentation.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo.R
import com.example.todo.presentation.viewmodels.TodayViewModel

@Composable
fun TodayScreen(modifier: Modifier, vm: TodayViewModel = hiltViewModel()) {
    if (vm.showDialog) {
        DialogWithTextFields()

    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    vm.showDialog = true
                }
            ) {
                Icon(Icons.Filled.Add, "")
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
                text = vm.getCurrentLocalDate(),
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
                            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                            .clickable { },
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            RadioButton(selected = vm.notesState[it].isDone, onClick = {
                                vm.changeStatus(vm.notesState[it])
                            })
                            Column {
                                Text(text = vm.notesState[it].title)
                                Text(
                                    text = vm.notesState[it].note,
                                    fontSize = 12.sp
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 8.dp),
                                contentAlignment = Alignment.TopEnd
                            ) {
                                Icon(
                                    modifier = Modifier.clickable {
                                        vm.deleteNote(vm.notesState[it])
                                    },
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete Icon"
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
fun DialogWithTextFields(vm: TodayViewModel = hiltViewModel()) {
    AlertDialog(
        modifier = Modifier.fillMaxWidth(),
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = true,
            dismissOnClickOutside = true,
            dismissOnBackPress = true
        ),
        shape = RoundedCornerShape(20.dp),
        onDismissRequest = {
            vm.showDialog = false
            vm.clearNote()
        },
        confirmButton = {
            TextButton(onClick = {
                vm.showDialog = false
                vm.addNewNote()
            }) {
                Text(text = "Add note")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                vm.showDialog = false
                vm.clearNote()
            }) {
                Text(text = "Cancel note")
            }
        },
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Add note", fontSize = 18.sp
                )
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = vm.titleState,
                    onValueChange = { vm.titleState = it },
                )
                TextField(
                    value = vm.noteState,
                    onValueChange = { vm.noteState = it })
            }
        })
}



