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
    
    // Экраны ошибок
    object ZoneNotFound {
        const val route = "zone_not_found"
    }
    
    object AccessDenied {
        const val route = "access_denied"
    }
}