package com.example.todo.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todo.presentation.viewmodels.SearchViewModel

@Composable
fun SearchScreen(modifier: Modifier, vm: SearchViewModel = hiltViewModel()) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Search")
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = vm.searchState.value,
                onValueChange = {
                    vm.searchTodo(it)
                    vm.searchState.value = it

                }
            )
            LazyColumn {
                items(vm.resultSearchState.value.size) {
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
                            RadioButton(selected = vm.resultSearchState.value[it].isDone, onClick = {
                                vm.updateToDo(vm.resultSearchState.value[it])
                            })
                            Column {
                                Text(text = vm.resultSearchState.value[it].title)
                                Text(
                                    text =  vm.resultSearchState.value[it].note,
                                    fontSize = 12.sp
                                )
                                Text(text =  vm.resultSearchState.value[it].date.toString())
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 8.dp),
                                contentAlignment = Alignment.TopEnd
                            ) {
                                Icon(
                                    modifier = Modifier.clickable {
                                        vm.deleteToDo(vm.resultSearchState.value[it])
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
