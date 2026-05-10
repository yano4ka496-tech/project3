package com.safeplant.core.navigation

object NavigationDestinations {
    object Profile {
        const val route = "profile"
    }
    
    object Quiz {
        const val route = "quiz"
    }
    
    object Training {
        const val route = "training"
    }
    
    object Map {
        const val route = "map"
        const val zoneIdParam = "zoneId"
    }
    
    object QR {
        const val route = "qr"
    }
    
    // Экраны обучающего контента
    object TrainingCenter {
        const val route = "training_center"
    }
    
    object Section {
        const val route = "section"
        const val sectionIdParam = "sectionId"
    }
    
    object Content {
        const val route = "content"
        const val contentIdParam = "contentId"
    }
    
    object Bookmarks {
        const val route = "bookmarks"
    }
    
    // Экраны ошибок
    object ZoneNotFound {
        const val route = "zone_not_found"
    }
    
    object AccessDenied {
        const val route = "access_denied"
    }
}