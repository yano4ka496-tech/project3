package com.safeplant.core.mapping.layers

/**
 * Класс для информации всплывающих окон
 * Содержит заголовок, описание и тип объекта
 */
data class PopupInfo(
    /**
     * Заголовок всплывающего окна
     */
    val title: String,
    
    /**
     * Описание объекта
     */
    val description: String,
    
    /**
     * Тип объекта (например, "Опасная зона", "Маршрут", "Точка СИЗ")
     */
    val type: String
)