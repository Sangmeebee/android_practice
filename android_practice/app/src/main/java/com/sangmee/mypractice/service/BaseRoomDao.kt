package com.sangmee.mypractice.service

import androidx.room.*

@Dao
interface BaseRoomDao<ET> {

    @Dao
    interface BaseDao<ET> {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(entity: ET)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(entity: Array<out ET>)

        @Update
        fun update(entity: ET)

        @Delete
        fun delete(entity: ET)

    }
}
