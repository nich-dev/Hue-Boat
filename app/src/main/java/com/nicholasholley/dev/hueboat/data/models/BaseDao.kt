package com.nicholasholley.dev.hueboat.data.models

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    /**
     * Insert an object or array of objects in the database.
     *
     * @param obj the objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj: T): LongArray

    /**
     * Update an object or array of objects from the database.
     *
     * @param obj the object to be updated
     */
    @Update
    fun update(vararg obj: T)

    /**
     * Delete an object or array of objects from the database
     *
     * @param obj the object to be deleted
     */
    @Delete
    fun delete(vararg obj: T)
}
