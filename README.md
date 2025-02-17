
# FilmRailCompose

FilmRail is an advanced film, series, and TV streaming application built using modern Android development tools. This app is designed to deliver a seamless user experience with features such as video playback, favorites, search, and detailed information about content.
=======
# MediaCenter

**MediaCenter** is an advanced film, series, and TV streaming application built using modern Android development tools. This app is designed to deliver a seamless user experience with features such as video playback, favorites, search, and detailed information about content.

---

## Features

- **Video Playback**: Play videos with ExoPlayer, supporting DASH, HLS, RTMP, and more.
- **Modern UI**: Built with Jetpack Compose for a responsive and dynamic interface.
- **Search Functionality**: Quickly find your favorite movies and series.
- **Favorites Management**: Add content to your favorites list for quick access.
- **Multi-Screen Support**: Optimized for both mobile devices and Android TV.
- **Offline Storage**: Save data locally with Room Database.
- **Secure API Integration**: Load API keys securely using Gradle's `buildConfigField`.

---

## Getting Started

### Prerequisites

To build and run this project, you'll need:

- **Android Studio**: Version Arctic Fox (2020.3.1) or higher.
- **JDK**: Version 11 or higher.
- **Gradle**: Version 7.0 or higher.

---

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/bingbong0098/media_center.git
    cd mediacenter
    ```

2. Create a `key.properties` file in the root directory with the following content:

3. Open the project in Android Studio.

4. Sync Gradle to download dependencies.

5. Run the application on your preferred emulator or device.

---

## Architecture

The project follows the **MVVM (Model-View-ViewModel)** architecture:

- **Model**: Manages data and business logic.
- **View**: Displays data and interacts with the user (Jetpack Compose).
- **ViewModel**: Bridges the View and Model layers, handling UI state and business logic.

---

## Key Technologies

- **Jetpack Compose**: For building modern, declarative UIs.
- **ExoPlayer**: For smooth and reliable media playback.
- **Retrofit**: For API communication.
- **Room**: For local data persistence.
- **Hilt**: For dependency injection.
- **DataStore**: For lightweight and secure key-value storage.
- **Accompanist Libraries**: For swipe refresh, paging, and system UI management.

---

## Project Modules

- **UI Components**: Compose-based reusable UI elements.
- **Data Layer**: Handles network and database operations.
- **Navigation**: Manage app navigation using Compose Navigation.

---

## Contact

For any inquiries or support, feel free to contact the project owner:

- **Email**: bingbong0098@gmail.com
- **Telegram**: [gohari_dev](https://t.me/gohari_dev)
- **GitHub**: [bingbong0098](https://github.com/bingbong0098)
