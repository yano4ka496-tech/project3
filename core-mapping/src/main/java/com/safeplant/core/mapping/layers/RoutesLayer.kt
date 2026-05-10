package com.safeplant.core.mapping.layers

import android.content.Context
import com.safeplant.core.mapping.LayerStyle
import com.safeplant.core.mapping.MapLayer
import com.safeplant.core.storage.AssetManager
import org.json.JSONArray
import org.json.JSONObject

/**
 * Слой предустановленных маршрутов (синие линии)
 * Отображает предустановленные маршруты на карте в виде синих линий
 */
class RoutesLayer(
    id: String,
    style: LayerStyle = LayerStyle.ROUTES
) : MapLayer(
    type = com.safeplant.core.mapping.LayerType.ROUTES,
    style = style,
    id = id
) {
    
    private val context: Context? = null // В реальном приложении будет инжектирован
    private val assetManager = AssetManager(context)
    
    /**
     * Менеджер для работы с маршрутами
     */
    private val routeManager = RouteManager(context)
    
    /**
     * Слой для отображения выбранного маршрута
     */
    private val routeLayer = RouteLayer("${id}_selected")
    
    /**
     * Список маршрутов
     */
    private var routes = mutableListOf<RouteModel>()
    
    /**
     * Информация для всплывающих окон
     */
    private var popupInfo = mutableMapOf<String, PopupInfo>()
    
    /**
     * ID выбранного маршрута
     */
    private var selectedRouteId: String? = null
    
    /**
     * Загружает данные слоя из assets
     */
    override fun loadData() {
        try {
            // Используем RouteManager для загрузки маршрутов
            if (routeManager.loadRoutes()) {
                routes = routeManager.getRoutes().toMutableList()
                
                // Создаем информацию для всплывающих окон
                popupInfo.clear()
                for (route in routes) {
                    popupInfo[route.id] = PopupInfo(
                        title = route.name,
                        description = route.description,
                        type = "Маршрут"
                    )
                }
            }
        } catch (e: Exception) {
            // Обработка ошибки загрузки данных
            // В реальном приложении здесь будет логирование и уведомление пользователя
        }
    }
    
    /**
     * Отрисовывает слой на карте
     */
    override fun render() {
        // Отрисовываем слой маршрутов
        routeLayer.render()
        
        // В реальном приложении здесь будет код отрисовки линий на карте
        // Для заглушки просто логируем
        println("Отрисовка ${routes.size} маршрутов")
    }
    
    /**
     * Очищает ресурсы слоя
     */
    override fun cleanup() {
        routes.clear()
        popupInfo.clear()
        routeManager.clearRoutes()
        routeLayer.cleanup()
    }
    
    /**
     * Проверяет, виден ли слой на текущем масштабе
     */
    override fun isVisible(zoom: Double): Boolean {
        return zoom >= style.minZoom && zoom <= style.maxZoom
    }
    
    /**
     * Обрабатывает нажатие на элемент слоя
     */
    override fun onFeatureClick(featureId: String): PopupInfo? {
        return popupInfo[featureId]
    }
    
    /**
     * Возвращает список маршрутов
     */
    fun getRoutes(): List<RouteModel> = routes
    
    /**
     * Возвращает информацию для всплывающих окон
     */
    fun getPopupInfo(): Map<String, PopupInfo> = popupInfo
    
    /**
     * Возвращает маршрут по ID
     */
    fun getRouteById(id: String): RouteModel? {
        return routeManager.getRouteById(id)
    }
    
    /**
     * Возвращает маршрут по имени
     */
    fun getRouteByName(name: String): RouteModel? {
        return routeManager.getRouteByName(name)
    }
    
    /**
     * Проверяет, существует ли маршрут с указанным ID
     */
    fun hasRoute(id: String): Boolean {
        return routeManager.hasRoute(id)
    }
    
    /**
     * Устанавливает выбранный маршрут
     */
    fun setSelectedRoute(routeId: String) {
        selectedRouteId = routeId
        val route = getRouteById(routeId)
        routeLayer.setRoute(route)
    }
    
    /**
     * Очищает выбранный маршрут
     */
    fun clearSelectedRoute() {
        selectedRouteId = null
        routeLayer.setRoute(null)
    }
    
    /**
     * Возвращает ID выбранного маршрута
     */
    fun getSelectedRouteId(): String? = selectedRouteId
    
    /**
     * Проверяет, заблокирован ли выбранный маршрут
     */
    fun isRouteBlocked(): Boolean {
        return routeLayer.isBlocked()
    }
}