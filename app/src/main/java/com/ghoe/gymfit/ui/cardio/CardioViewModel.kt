package com.ghoe.gymfit.ui.cardio

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ghoe.gymfit.model.Workout
import com.ghoe.gymfit.repository.WorkoutRepository
import com.ghoe.gymfit.util.CARDIO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class CardioViewModel @ViewModelInject constructor(repository: WorkoutRepository) : ViewModel() {

    init {
        Timber.i("CardioViewModel init")
    }

    private var cardioUiModelFlow: Flow<CardioUiModel> = repository.getWorkouts().map { list ->
        val cardio = list.filter { workout ->
            workout.category.contains(CARDIO, true)
        }
        CardioUiModel(cardio)
    }

    val cardioUiModel: LiveData<CardioUiModel> = cardioUiModelFlow.asLiveData()

    /**
     * Wraps the list of workouts that needs to be displayed in the UI.
     */
    data class CardioUiModel(val workouts: List<Workout>)
}
