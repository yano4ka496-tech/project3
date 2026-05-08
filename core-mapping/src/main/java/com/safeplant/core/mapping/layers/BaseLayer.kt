package com.safeplant.core.mapping.layers

import com.safeplant.core.mapping.LayerStyle
import com.safeplant.core.mapping.MapLayer

/**
 * Базовый класс для конкретных реализаций слоев
 * Наследуется от MapLayer и добавляет общую функциональность
 */
abstract class BaseLayer(
    id: String,
    style: LayerStyle
) : MapLayer(
    type = determineLayerType(),
    style = style,
    id = id
) {
    
    /**
     * Определяет тип слоя для конкретной реализации
     */
    abstract fun determineLayerType(): com.safeplant.core.mapping.LayerType
    
    /**
     * Загружает данные слоя из assets
     */
    protected fun loadFromAssets(fileName: String): String {
        // В реальном приложении здесь будет загрузка из assets
        // Для заглушки возвращаем пустую строку
        return ""
    }
    
    /**
     * Проверяет корректность данных слоя
     */
    protected fun validateData(data: String): Boolean {
        // В реальном приложении здесь будет валидация GeoJSON
        // Для заглушки всегда возвращаем true
        return true
    }
}