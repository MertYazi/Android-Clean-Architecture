package com.aliasadi.clean.ui.adapter.movie.loadstate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aliasadi.clean.databinding.ItemLoadStateFooterBinding
import com.aliasadi.clean.ui.adapter.movie.loadstate.MovieLoadStateAdapter.LoadStateViewHolder

/**
 * @author by Ali Asadi on 31/01/2023
 */
class MovieLoadStateAdapter(
    private val onRetryClicked: () -> Unit
) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder =
        LoadStateViewHolder(parent, onRetryClicked)


    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class LoadStateViewHolder(
        parent: ViewGroup,
        onRetryClicked: () -> Unit
    ) : RecyclerView.ViewHolder(
        ItemLoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
    ) {

        private val binding = ItemLoadStateFooterBinding.bind(itemView)

        init {
            binding.buttonRetry.setOnClickListener { onRetryClicked() }
        }

        fun bind(loadState: LoadState) = with(binding) {
            progressBar.isVisible = loadState is LoadState.Loading
            buttonRetry.isVisible = loadState !is LoadState.Loading
        }
    }
}