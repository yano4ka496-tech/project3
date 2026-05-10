package com.safeplant.core.mapping.layers

/**
 * Модель маршрута для работы с данными из GeoJSON
 * Содержит всю необходимую информацию о маршруте
 */
data class RouteModel(
    /**
     * Уникальный идентификатор маршрута
     */
    val id: String,
    
    /**
     * Название маршрута
     */
    val name: String,
    
    /**
     * Описание маршрута
     */
    val description: String,
    
    /**
     * Список координат маршрута в формате [lon1, lat1, lon2, lat2, ...]
     */
    val coordinates: List<Double>
) {
    /**
     * Проверяет, что маршрут содержит корректные координаты
     */
    fun isValid(): Boolean {
        return coordinates.isNotEmpty() && coordinates.size % 2 == 0
    }
    
    /**
     * Возвращает количество точек в маршруте
     */
    fun pointCount(): Int = coordinates.size / 2
    
    /**
     * Возвращает первую точку маршрута
     */
    fun startPoint(): Pair<Double, Double>? {
        return if (coordinates.size >= 2) {
            Pair(coordinates[1], coordinates[0])
        } else {
            null
        }
    }
    
    /**
     * Возвращает последнюю точку маршрута
     */
    fun endPoint(): Pair<Double, Double>? {
        return if (coordinates.size >= 2) {
            val lastIndex = coordinates.size - 2
            Pair(coordinates[lastIndex + 1], coordinates[lastIndex])
        } else {
            null
        }
    }
}