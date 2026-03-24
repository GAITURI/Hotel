# 🍔 Hotel & Burger  App (Android)

![Android](https://img.shields.io/badge/platform-Android-brightgreen?logo=android)
![Kotlin](https://img.shields.io/badge/language-Kotlin-blueviolet?logo=kotlin)
![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack_Compose-orange?logo=jetpackcompose)
![Firebase](https://img.shields.io/badge/backend-Firebase-ffca28?logo=firebase)
![License](https://img.shields.io/badge/license-MIT-green)

This Android app demonstrates fetching and displaying data—initially focusing on burgers, with a scalable design for hotel features. It incorporates modern Android development best practices, including MVVM architecture, Retrofit for networking, Glide for image loading, Kotlin Coroutines for async operations, and Firebase for user and order data storage.

---

## 📂 Table of Contents

- [Project Overview](#project-overview)
- [Features Implemented (Burger Section)](#features-implemented-burger-section)
- [Planned Hotel Features](#planned-hotel-features)
- [Core Concepts & Technologies](#core-concepts--technologies)
  - [Architecture](#architecture)
  - [UI & UX](#ui--ux)
  - [Data Management](#data-management)
  - [Networking](#networking)
  - [Image Loading](#image-loading)
  - [Asynchronous Operations](#asynchronous-operations)
  - [Firebase Integration](#firebase-integration)
  - [Navigation](#navigation)
- [Screenshots](#screenshots)
- [Setup & Installation](#setup--installation)
- [API Details](#api-details)
- [Future Enhancements](#future-enhancements)

---

## 🔍 Project Overview

This project builds a functional Android application that communicates with a remote API to display dynamic content. The app currently showcases burger listings but is architected to support hotel discovery, room booking, and more complex features in the near future.

---

## ✅ Features Implemented (Burger Section)

- Fetch and display a list of burgers from an external API
- Show names, descriptions, and images for each burger
- Glide image loading with custom URL manipulation for optimized image size
- Clean and responsive UI using Jetpack Compose

---

## 🏨 Planned Hotel Features

- User Authentication (via Firebase)
- Hotel room listings (type, price, amenities, images)
- Date-based search and filtering
- Room booking and order placement
- Booking history and receipt viewing
- Admin dashboard for hotel managers

---

## 🧠 Core Concepts & Technologies

### 🏗️ Architecture

- MVVM (Model-View-ViewModel) pattern for separation of concerns
- Repository Pattern: The ViewModel interacts with a repository which abstracts network and database operations
- StateFlow / LiveData: Observing reactive UI state changes

### 🎨 UI & UX

- Jetpack Compose for declarative UI building
- Material 3 (Material You) for modern UI design
- Navigation Component (planned) for screen transitions

### 🧾 Data Management

- Kotlin Data Classes model remote data (Burger.kt, Hotel.kt)
- Clean encapsulation of network and UI logic
- Placeholder local caching with potential Room DB integration

### 🌐 Networking

- Retrofit: Type-safe HTTP client with:
  - API interface in Api.kt
  - Base URL configured in RetrofitClient.kt
  - JSON parsing via GsonConverterFactory
- OkHttp: Underlying HTTP client with optional interceptors

### 🖼️ Image Loading

- Glide: Efficient image fetching and caching
- Custom Model Loader: URL manipulation to request server-optimized image sizes (e.g., ?width=300&height=300)

### 🔄 Asynchronous Operations

- Kotlin Coroutines: Structured concurrency for non-blocking data fetching
- ViewModelScope: Ensures jobs cancel with lifecycle

### 🔥 Firebase Integration

- Firebase Realtime Database
  - Stores user profiles, authentication tokens, and order data
  - Database structure (sample):

```json
{
  "users": {
    "uid123": {
      "name": "John Doe",
      "email": "john@example.com",
      "orders": {
        "orderId1": {
          "item": "Double Cheese Burger",
          "quantity": 2,
          "status": "Pending"
        }
      }
    }
  }
}
```

- Firebase Auth (Planned)
  - Google Sign-In and Email/Password login
  - Used to authenticate and persist user sessions across devices

---

## 📸 Screenshots

(Add relevant app UI screenshots here)

---

## ⚙️ Setup & Installation

1. Clone the repo:
```bash
git clone https://github.com/yourusername/hotel-burger-app.git
```

2. Open in Android Studio  
3. Replace BASE_URL in RetrofitClient.kt with your API endpoint  
4. Configure Firebase:  
   - Add google-services.json to the /app directory  
   - Enable Realtime Database and Authentication in the Firebase console  
5. Run the app on emulator or physical device

---

## 📡 API Details

- Currently uses a sample burger API (e.g., via RapidAPI)
- Future integration with hotel and booking APIs
- RESTful principles with support for GET, POST, and PUT endpoints

---

## 🚀 Future Enhancements

- Firebase Auth integration
- Room booking with date selection
- Admin panel with add/edit room functionality
- Ratings and reviews
- Payment gateway integration
- Local Room DB caching for offline support
