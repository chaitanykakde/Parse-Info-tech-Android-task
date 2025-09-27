package com.chaitany.chaitanystask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chaitany.chaitanystask.R
import com.chaitany.chaitanystask.data.Course
import com.chaitany.chaitanystask.databinding.ItemCourseBinding

class CourseAdapter(
    private val onCourseClick: (Course) -> Unit
) : ListAdapter<Course, CourseAdapter.CourseViewHolder>(CourseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CourseViewHolder(
        private val binding: ItemCourseBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(course: Course) {
            binding.apply {
                textViewTitle.text = course.title
                textViewDescription.text = course.description
                textViewInstructor.text = "By ${course.instructor}"
                textViewDuration.text = course.duration
                textViewPrice.text = "$${String.format("%.2f", course.price)}"
                textViewRating.text = course.rating.toString()
                textViewCategory.text = course.category

                // Load image using Glide
                Glide.with(imageViewCourse.context)
                    .load(course.imageUrl)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(imageViewCourse)

                // Set click listener
                root.setOnClickListener {
                    onCourseClick(course)
                }
            }
        }
    }

    class CourseDiffCallback : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }
    }
}
