package com.surya.androidjetpackpro.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.surya.androidjetpackpro.BuildConfig
import com.surya.androidjetpackpro.data.models.TVShow
import com.surya.androidjetpackpro.databinding.TvShowItemBinding
import com.surya.androidjetpackpro.ui.movie.detail.MovieDetailActivity
import com.surya.androidjetpackpro.ui.tvshow.detail.TvShowDetailActivity
import com.surya.androidjetpackpro.utils.Constants
import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * Created by suryamudti on 22/09/2019.
 */
class TvShowPagedListAdapter : PagedListAdapter<TVShow,TvShowPagedListAdapter.ViewHolder>(COMPARATOR){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val bind = TvShowItemBinding.inflate(inflater,parent,false)
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.apply {
            item?.let {tvShow ->
                bind(tvShow)
                itemView.setOnClickListener {
                    val intent = Intent(it.context, TvShowDetailActivity::class.java)
                    intent.putExtra(Constants.TV_SHOW_ID_EXTRAS,tvShow)
                    it.context.startActivity(intent)
                }
            }
        }
    }


    class ViewHolder(
        private val bind : TvShowItemBinding
    ): RecyclerView.ViewHolder(bind.root){

        fun bind(item: TVShow){
            bind.apply {
                tvShow = item

                executePendingBindings()
            }
            val getDate : String? = item.first_air_date

            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            try {
                val date = simpleDateFormat.parse(getDate)
                val newFormat = SimpleDateFormat("EEEE, MMM dd, yyyy")
                val dateFix = newFormat.format(date)
                bind.tvYear.text = dateFix.split(" ")[3]
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            Glide.with(bind.root.context).load(BuildConfig.IMAGE_BASE_URL+item.backdrop_path).into(bind.imgPoster)
        }

    }

    companion object{
        private val COMPARATOR = object: DiffUtil.ItemCallback<TVShow>(){
            override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
                return oldItem == newItem
            }
        }
    }

}