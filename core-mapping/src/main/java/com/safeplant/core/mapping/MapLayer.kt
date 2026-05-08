package com.safeplant.core.mapping

/**
 * Базовый класс для всех слоев карты
 * Определяет общие методы и свойства для всех типов слоев
 */
abstract class MapLayer(
    /**
     * Тип слоя
     */
    val type: LayerType,
    
    /**
     * Стиль слоя
     */
    val style: LayerStyle,
    
    /**
     * Идентификатор слоя
     */
    val id: String
) {
    /**
     * Видимость слоя
     */
    var isVisible: Boolean = true
    
    /**
     * Данные слоя (GeoJSON или другие)
     */
    var data: String? = null
    
    /**
     * Проверяет, должен ли слой отображаться на текущем масштабе
     */
    fun shouldShowAtZoom(currentZoom: Double): Boolean {
        return currentZoom >= style.minZoom && currentZoom <= style.maxZoom
    }
    
    /**
     * Обновляет данные слоя
     */
    abstract fun updateData(newData: String)
    
    /**
     * Рендерит слой на карте
     */
    abstract fun render()
    
    /**
     * Очищает ресурсы слоя
     */
    abstract fun cleanup()
}