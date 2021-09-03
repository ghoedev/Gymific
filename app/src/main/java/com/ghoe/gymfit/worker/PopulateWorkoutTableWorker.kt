package com.ghoe.gymfit.worker

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ghoe.gymfit.R
import com.ghoe.gymfit.db.GymificDatabase
import com.ghoe.gymfit.model.Workout
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.coroutineScope
import timber.log.Timber

class PopulateWorkoutTableWorker @WorkerInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParameters: WorkerParameters,
    private val database: GymificDatabase
) :
    CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.resources.openRawResource(R.raw.workouts).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val type = object : TypeToken<List<Workout>>() {}.type
                    val workoutList: List<Workout> = Gson().fromJson(jsonReader, type)
                    database.workoutDao.insertAll(workoutList)
                }
            }
            Timber.i("Populate workout-table request succeed")
            Result.success()
        } catch (e: Exception) {
            Timber.i("Populate workout-table request failed. ${e.message}")
            Result.failure()
        }
    }
}