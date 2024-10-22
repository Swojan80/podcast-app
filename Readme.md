# PodcastShow Android Application
This is a simple podcast app that displays trending episodes and new releases using `RecyclerView` and provides detailed views of each episode.

## Features
- Display a list of trending podcast episodes.
- Display new releases.
- Navigate to detailed views of selected episodes with information like name, culture, domain, symbol, parentage, and more.

## Requirements
- Android Studio Ladybug 2024.2.1
- Gradle 8.9+
- Android SDK version 29 (or higher)

## Dependencies
This project uses the following external libraries:
- [OkHttp](https://square.github.io/okhttp/): For making HTTP requests.
- [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview): For displaying lists efficiently.
- [CardView](https://developer.android.com/jetpack/androidx/releases/cardview): For displaying attractive list items with shadows and rounded corners.

## How to Build and Run

### 1. Clone the repository
```bash
git clone https://github.com/Swojan80/podcast-app.git
cd podcastshow
```

### 2. Open in Android Studio
Open Android Studio.
Select File > Open and choose the project directory.
Let Android Studio configure and sync the Gradle files.

### 3. Build the Project
From Android Studio, click on Build > Make Project or press Ctrl + F9.

### 4. Run the Project
Connect an Android device or start an Android Emulator.
Click the Run button (Shift + F10) in Android Studio or select Run > Run 'app'.

## Project Structure
```bash
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/podcastshow/
│   │   │   │   ├── adapter/EntityAdapter.kt       # RecyclerView adapter for displaying episode items
│   │   │   │   ├── model/Entity.kt                # Data model for episodes
│   │   │   │   ├── ui/LoginActivity.kt            # Login screen
│   │   │   │   ├── ui/HomeActivity.kt             # Home screen with list of trending and new releases
│   │   │   │   ├── ui/DetailActivity.kt           # Detailed view for each episode
│   │   ├── res/
│   │   │   ├── layout/                            # XML layout files for UI
│   │   │   └── drawable/                          # Images and vector drawables
├── .gitignore
└── README.md
```