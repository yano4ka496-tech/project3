package com.safeplant.core.mapping

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.safeplant.core.security.RootDetector

/**
 * Менеджер слоев карты
 * Управляет всеми слоями на карте, их видимостью и стилями
 */
class LayerManager(
    private val context: Context,
    private val rootDetector: RootDetector
) {
    /**
     * Список всех слоев на карте
     */
    private val _layers = mutableListOf<MapLayer>()
    val layers: List<MapLayer> get() = _layers
    
    /**
     * Текущий активный масштаб карты
     */
    private var _currentZoom = MutableLiveData<Double>()
    val currentZoom: LiveData<Double> get() = _currentZoom
    
    /**
     * Состояние видимости слоя эвакуации
     */
    private var _isEvacuationVisible = MutableLiveData<Boolean>(false)
    val isEvacuationVisible: LiveData<Boolean> get() = _isEvacuationVisible
    
    /**
     * Добавляет новый слой на карту
     */
    fun addLayer(layer: MapLayer) {
        _layers.add(layer)
    }
    
    /**
     * Удаляет слой с карты по идентификатору
     */
    fun removeLayer(layerId: String) {
        _layers.removeAll { it.id == layerId }
    }
    
    /**
     * Получает слой по идентификатору
     */
    fun getLayer(layerId: String): MapLayer? {
        return _layers.find { it.id == layerId }
    }
    
    /**
     * Переключает видимость слоя
     */
    fun toggleLayerVisibility(layerId: String) {
        getLayer(layerId)?.let { layer ->
            layer.isVisible = !layer.isVisible
        }
    }
    
    /**
     * Устанавливает видимость слоя эвакуации
     * Проверяет наличие действующего допуска при включении
     */
    fun setEvacuationVisibility(visible: Boolean) {
        if (visible) {
            // Проверяем наличие действующего допуска
            if (!hasValidAccess()) {
                // Допуска нет, не включаем слой
                return
            }
        }
        _isEvacuationVisible.value = visible
    }
    
    /**
     * Обновляет текущий масштаб карты
     */
    fun updateZoom(zoom: Double) {
        _currentZoom.value = zoom
    }
    
    /**
     * Проверяет наличие действующего допуска
     */
    private fun hasValidAccess(): Boolean {
        // В реальном приложении здесь будет проверка из базы данных
        // Для заглушки всегда возвращаем true
        return true
    }
    
    /**
     * Инициализирует все слои
     */
    fun initializeLayers() {
        // Создаем слои через фабрику
        val layerFactory = LayerFactory(context)
        
        // Добавляем все типы слоев
        addLayer(layerFactory.createLayer(LayerType.HAZARDS, "hazards_layer"))
        addLayer(layerFactory.createLayer(LayerType.ROUTES, "routes_layer"))
        addLayer(layerFactory.createLayer(LayerType.EVACUATION, "evacuation_layer"))
        addLayer(layerFactory.createLayer(LayerType.PPE_POINTS, "ppe_points_layer"))
    }
    
    /**
     * Очищает все слои
     */
    fun cleanup() {
        _layers.forEach { it.cleanup() }
        _layers.clear()
    }
}