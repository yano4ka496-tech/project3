plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
//     id("kotlin-kapt")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.compose")   // ← ДОБАВИТЬ
}

android {
    namespace = "com.safeplant.core.navigation"
    compileSdk = 34

    defaultConfig {
        minSdk = 29
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true   // ← ОСТАВИТЬ (или вернуть)
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")

    // Compose
    implementation(platform("androidx.compose:compose-bom:2024.11.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.8.1")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.8.1")

    // Hilt

    // (тесты — по желанию, но лучше оставить без Compose-зависимостей в тестах)
}
