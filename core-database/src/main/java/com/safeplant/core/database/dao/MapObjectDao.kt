package com.safeplant.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.safeplant.core.database.entity.MapObject
import kotlinx.coroutines.flow.Flow

@Dao
interface MapObjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMapObject(mapObject: MapObject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMapObjects(mapObjects: List<MapObject>)

    @Query("SELECT * FROM map_object WHERE objectId = :objectId")
    suspend fun getMapObjectById(objectId: String): MapObject?

    @Query("SELECT * FROM map_object ORDER BY name ASC")
    fun getAllMapObjects(): Flow<List<MapObject>>

    @Query("SELECT * FROM map_object WHERE name LIKE :searchQuery OR objectId LIKE :searchQuery")
    fun searchMapObjects(searchQuery: String): Flow<List<MapObject>>

    @Query("DELETE FROM map_object")
    suspend fun deleteAllMapObjects()
}
