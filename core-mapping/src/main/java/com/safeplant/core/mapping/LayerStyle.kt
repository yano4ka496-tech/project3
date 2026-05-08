package com.safeplant.core.mapping

/**
 * Стили для отображения слоев карты
 * Определяет визуальные параметры каждого типа слоя
 */
data class LayerStyle(
    /**
     * Цвет слоя в формате ARGB (альфа, красный, зеленый, синий)
     */
    val color: Int,
    
    /**
     * Толщина линии в пикселях (для линейных слоев)
     */
    val lineWidth: Float = 1.0f,
    
    /**
     * Прозрачность слоя (0.0 - полностью прозрачный, 1.0 - полностью непрозрачный)
     */
    val opacity: Float = 1.0f,
    
    /**
     * Минимальный масштаб для отображения слоя
     */
    val minZoom: Double = 0.0,
    
    /**
     * Максимальный масштаб для отображения слоя
     */
    val maxZoom: Double = 20.0
) {
    companion object {
        /**
         * Стиль для слоя опасных зон (красные полигоны)
         */
        val HAZARDS = LayerStyle(
            color = 0x80FF0000, // Красный с 50% прозрачностью
            opacity = 0.5f,
            minZoom = 10.0,
            maxZoom = 20.0
        )
        
        /**
         * Стиль для слоя предустановленных маршрутов (синие линии)
         */
        val ROUTES = LayerStyle(
            color = 0xFF0000FF, // Синий
            lineWidth = 3.0f,
            minZoom = 12.0,
            maxZoom = 20.0
        )
        
        /**
         * Стиль для слоя эвакуации (зеленые линии)
         */
        val EVACUATION = LayerStyle(
            color = 0xFF00FF00, // Зеленый
            lineWidth = 4.0f,
            minZoom = 12.0,
            maxZoom = 20.0
        )
        
        /**
         * Стиль для слоя точек СИЗ (желтые иконки)
         */
        val PPE_POINTS = LayerStyle(
            color = 0xFFFFFF00, // Желтый
            minZoom = 15.0,
            maxZoom = 20.0
        )
    }
}