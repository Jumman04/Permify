package com.aylax.permify.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aylax.library.model.Application
import com.aylax.permify.databinding.ItemApplicationBinding
import com.google.android.material.elevation.SurfaceColors

class MainAdapter(
    private val application: List<Application>, private val listener: OnClickListener
) : RecyclerView.Adapter<MainAdapter.AppViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val binding =
            ItemApplicationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AppViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return application.size
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.binding.apply {
            title.text = application[position].appName
            imageView.setImageDrawable(application[position].appIcon)
            description.text = "Granted ${
                application[position].permissions.count {
                    it.isGranted == true
                }
            }, Denied ${application[position].permissions.count { it.isGranted == false }}"

            background.setCardBackgroundColor(SurfaceColors.SURFACE_1.getColor(title.context))
            background.setOnClickListener {
                listener.onItemClicked(application[position])
            }
        }
    }

    class AppViewHolder(val binding: ItemApplicationBinding) : ViewHolder(binding.root)

    interface OnClickListener {
        fun onItemClicked(app: Application)
    }
}