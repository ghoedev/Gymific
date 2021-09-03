package com.ghoe.gymfit.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ghoe.gymfit.model.Workout
import com.ghoe.gymfit.model.WorkoutCategory

@Database(entities = [Workout::class, WorkoutCategory::class], version = 1)
abstract class GymificDatabase : RoomDatabase() {
    abstract val workoutDao: WorkoutDao
    abstract val categoryDao: WorkoutCategoryDao
}