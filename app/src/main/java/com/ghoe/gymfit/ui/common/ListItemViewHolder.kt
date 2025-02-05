package com.ghoe.gymfit.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ghoe.gymfit.R
import com.ghoe.gymfit.databinding.WorkoutViewItemBinding
import com.ghoe.gymfit.model.Workout

class ListItemViewHolder(private val binding: WorkoutViewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.setClickListener { view ->
            binding.workout?.let { workout ->
                navigateToDetail(view, workout)
            }
        }
    }

    private fun navigateToDetail(view: View, workout: Workout) {
        val args = Bundle()
        args.putParcelable("workout", workout)
        view.findNavController().navigate(R.id.navigation_detail, args)
    }

    fun bind(item: Workout) {
        binding.apply {
            workout = item
            executePendingBindings()
        }
    }

    companion object {
        fun from(parent: ViewGroup): ListItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = WorkoutViewItemBinding.inflate(layoutInflater, parent, false)
            return ListItemViewHolder(binding)
        }
    }
}