package uz.kozimjon.paginationapp.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.kozimjon.paginationapp.R
import uz.kozimjon.paginationapp.databinding.AdapterResultBinding
import uz.kozimjon.paginationapp.model.CharacterResponse
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ResultAdapter : PagingDataAdapter<CharacterResponse.ResultData, ResultAdapter.MyViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = AdapterResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(inflater)
    }


    // ViewHolder
    class MyViewHolder(private val binding: AdapterResultBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SimpleDateFormat")
        fun bind(data: CharacterResponse.ResultData) {
            binding.tvName.text = data.name

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val parsedDate = LocalDateTime.parse(data.created, DateTimeFormatter.ISO_DATE_TIME)
                val formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                binding.tvCreated.text = formattedDate.toString()

            } else {
                val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val formattedDate = formatter.format(parser.parse(data.created))
                binding.tvCreated.text = formattedDate
            }
            Glide.with(itemView.context).load(data.image).centerCrop().placeholder(R.drawable.ic_launcher_background).into(binding.ivImage)
        }
    }

    // DiffUtil
    class DiffUtilCallBack : DiffUtil.ItemCallback<CharacterResponse.ResultData>() {
        override fun areItemsTheSame(oldItem: CharacterResponse.ResultData, newItem: CharacterResponse.ResultData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CharacterResponse.ResultData, newItem: CharacterResponse.ResultData): Boolean {
            return oldItem.name == newItem.name && oldItem.species == newItem.species
        }

    }
}