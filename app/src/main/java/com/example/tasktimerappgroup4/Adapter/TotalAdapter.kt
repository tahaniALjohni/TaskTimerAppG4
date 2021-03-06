package com.example.tasktimerappgroup4.Adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerappgroup4.Activity.TotalActivity
import com.example.tasktimerappgroup4.Model.Tasks
import com.example.tasktimerappgroup4.databinding.TotalRowBinding
import java.util.concurrent.TimeUnit

class TotalAdapter(private val activity: TotalActivity) :
    RecyclerView.Adapter<TotalAdapter.ItemViewHolder>() {

    var tasksList = emptyList<Tasks>()

    class ItemViewHolder(val binding: TotalRowBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            TotalRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val task = tasksList[position]


        holder.binding.apply {

            val title = "${task.title}"
            val timer = "${task.taskTime}"

            tvTotalTitle.text = title

            tvTotalTime.text = timeCoverter(task.pauseOffset)

        }
    }


    fun timeCoverter(total: Long): String {
        val newTotal2 = String.format(
            "%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(total),
            TimeUnit.MILLISECONDS.toMinutes(total) -
                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(total)),
            TimeUnit.MILLISECONDS.toSeconds(total) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(total))
        )
        return newTotal2
    }


    override fun getItemCount(): Int {
        return tasksList.size
    }

    fun updateData(notes: List<Tasks>) {
        this.tasksList = notes
        notifyDataSetChanged()
    }

}