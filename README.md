# To-Do List App

This is a simple To-Do List application developed in Kotlin using Android Studio. The app allows users to add, edit, and delete tasks, which are stored persistently using SharedPreferences.

## Features

- Add tasks: Users can add new tasks to the list by entering task titles and clicking the "Add" button.
- Edit tasks: Clicking on a task in the list opens a dialog for editing the task title.
- Delete tasks: Long Click on a task to delete it from the list.

## UI Design

The app's UI is designed with a RecyclerView to display the list of tasks. Each task item in the list includes a delete button for easy deletion, and clicking on a task opens a dialog for editing.

## Implementation Details

- The main activity (`MainActivity.kt`) handles task management functionality, including adding, editing, and deleting tasks.
- A custom `TaskAdapter` class is used for the RecyclerView, providing item click and long click listeners for edit and delete operations.
- Task data is serialized to JSON format and stored in SharedPreferences, ensuring retention between app sessions.

## Getting Started

To run the app, follow these steps:

1. Clone or download the repository.
2. Open the project in Android Studio.
3. Build and run the app on an Android device or emulator.

## Dependencies

The app uses the following dependencies:

- `implementation 'com.google.code.gson:gson:2.8.9'`: Gson library for JSON serialization/deserialization.
- Other dependencies as specified in the `build.gradle` file.
