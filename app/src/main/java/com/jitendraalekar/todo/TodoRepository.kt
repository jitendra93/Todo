package com.jitendraalekar.todo

class ToDoRepository {
    var items = listOf(
        ToDoModel(
            description = "Ludovico Einaudi",
            isCompleted = true,
        ),
        ToDoModel(
            description = "Nuvole Bianche"
        ),
        ToDoModel(
            description = "Write an app for somebody in my community",
            notes = "Talk to some people at non-profit organizations to see what they need!"
        )
    )
}