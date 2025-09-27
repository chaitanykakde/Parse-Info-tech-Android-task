# 📱 ChaitanysTask - Course Management App

A modern Android application built with Kotlin for managing online courses. Features user authentication, course browsing, detailed course information, and a personal watchlist.

## 🚀 Features

- **User Authentication**: Secure login and registration system
- **Course Management**: Browse and view detailed course information
- **Watchlist**: Save courses for later viewing
- **Modern UI**: Clean and intuitive Material Design interface
- **Navigation**: Drawer navigation with multiple sections
- **Data Persistence**: Local storage for user preferences and watchlist

## 📸 Screenshots

### Login & Registration
![Login Screen](Screenshot_20250927-173145_ChaitanysTask.jpg)
*User login interface with clean design*

![Registration Screen](Screenshot_20250927-173149_ChaitanysTask.jpg)
*User registration form*

### Main App Interface
![Home Screen](Screenshot_20250927-173153_ChaitanysTask.jpg)
*Main dashboard with course listings*

![Course Details](Screenshot_20250927-173158_ChaitanysTask.jpg)
*Detailed course information view*

![Watchlist](Screenshot_20250927-173202_ChaitanysTask.jpg)
*Personal watchlist management*

## 📥 Download APK

### Latest Release
**Download the APK directly:** [app-debug.apk](app/build/intermediates/apk/debug/app-debug.apk)

> **Note**: This is a debug build. For production use, please build a release version.

## 🛠️ Technical Details

- **Language**: Kotlin
- **Architecture**: MVVM with Navigation Component
- **UI**: Material Design 3
- **Networking**: Retrofit with Gson
- **Image Loading**: Glide
- **Data Storage**: SharedPreferences
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 36 (Android 14)

## 📋 Prerequisites

- Android Studio Arctic Fox or later
- Android SDK 24+
- Kotlin 1.8+

## 🔧 Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/ChaitanysTask.git
   cd ChaitanysTask
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned directory

3. **Build the project**
   ```bash
   ./gradlew assembleDebug
   ```

4. **Install on device**
   ```bash
   ./gradlew installDebug
   ```

## 🏗️ Project Structure

```
app/
├── src/main/
│   ├── java/com/chaitany/chaitanystask/
│   │   ├── SplashActivity.kt          # App splash screen
│   │   ├── LoginActivity.kt           # User authentication
│   │   ├── RegistrationActivity.kt    # User registration
│   │   ├── HomeActivity.kt            # Main dashboard
│   │   ├── CourseDetailActivity.kt    # Course details
│   │   ├── WatchlistActivity.kt       # User watchlist
│   │   └── utils/
│   │       └── PreferenceManager.kt   # Data persistence
│   ├── res/                           # Resources (layouts, drawables, etc.)
│   └── AndroidManifest.xml
├── build.gradle.kts                   # App-level dependencies
└── courses_data.json                  # Sample course data
```

## 🎯 Key Features Implementation

### Authentication System
- Secure login/registration flow
- Session management with SharedPreferences
- Automatic login state checking

### Course Management
- JSON-based course data
- RecyclerView for course listings
- Detailed course information display
- Image loading with Glide

### Navigation
- Drawer navigation implementation
- Fragment-based navigation
- Toolbar with action items

### Watchlist
- Add/remove courses from watchlist
- Persistent storage
- Dedicated watchlist activity

## 🔄 API Integration

The app uses local JSON data (`courses_data.json`) for course information. The structure includes:
- Course details (title, description, price, rating)
- Instructor information
- Course duration and category
- High-quality course images

## 🎨 UI/UX Features

- **Material Design 3**: Modern Android design guidelines
- **Responsive Layout**: Works on different screen sizes
- **Smooth Animations**: Transitions between activities
- **Intuitive Navigation**: Easy-to-use drawer navigation
- **Visual Feedback**: Toast messages and loading states

## 🚀 Getting Started

1. **Launch the app** - You'll see the splash screen
2. **Register/Login** - Create an account or login
3. **Browse Courses** - View available courses on the home screen
4. **View Details** - Tap any course to see detailed information
5. **Add to Watchlist** - Save interesting courses for later
6. **Manage Watchlist** - Access your saved courses from the menu

## 📱 Compatibility

- **Android Version**: 7.0 (API 24) and above
- **Screen Sizes**: Phone and tablet optimized
- **Orientations**: Portrait and landscape support

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Developer

**Chaitany Skakde**
- GitHub: [@chaitanyskakde](https://github.com/chaitanyskakde)
- Email: [your-email@example.com]

## 🙏 Acknowledgments

- Material Design for UI components
- Android Jetpack for modern development practices
- Unsplash for course images
- Parse Info Tech for the project requirements

---

**⭐ If you found this project helpful, please give it a star!**
