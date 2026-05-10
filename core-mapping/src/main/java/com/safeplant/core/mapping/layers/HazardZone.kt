package com.safeplant.core.mapping.layers

/**
 * Модель опасной зоны для работы с данными из GeoJSON
 * Содержит всю необходимую информацию об опасной зоне
 */
data class HazardZone(
    /**
     * Уникальный идентификатор опасной зоны
     */
    val id: String,
    
    /**
     * Название опасной зоны
     */
    val name: String,
    
    /**
     * Описание опасной зоны
     */
    val description: String,
    
    /**
     * Широта центра опасной зоны
     */
    val centerLat: Double,
    
    /**
     * Долгота центра опасной зоны
     */
    val centerLon: Double,
    
    /**
     * Список координат полигона в формате [[lon1, lat1, lon2, lat2, ...], ...]
     */
    val coordinates: List<List<Double>>
) {
    /**
     * Проверяет, что опасная зона содержит корректные координаты
     */
    fun isValid(): Boolean {
        return coordinates.isNotEmpty() && coordinates.all { ring -> ring.isNotEmpty() && ring.size % 2 == 0 }
    }
    
    /**
     * Возвращает количество колец в полигоне
     */
    fun ringCount(): Int = coordinates.size
    
    /**
     * Возвращает центр опасной зоны
     */
    fun center(): Pair<Double, Double> = Pair(centerLon, centerLat)
}