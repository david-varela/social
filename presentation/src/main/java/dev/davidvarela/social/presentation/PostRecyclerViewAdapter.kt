package dev.davidvarela.social.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.davidvarela.social.domain.entities.PostPreview

class PostsRecyclerViewAdapter(private val listener: OnListFragmentInteractionListener?) :
    RecyclerView.Adapter<PostsRecyclerViewAdapter.ViewHolder>() {
    val values: MutableList<PostPreview> = mutableListOf()

    private val mOnClickListener = View.OnClickListener { v ->
        val item = v.tag as PostPreview
        listener?.onListFragmentInteraction(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_title, parent, false)
        return ViewHolder(view as TextView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        with(holder.mView) {
            val item = values[position]
            text = item.title
            tag = item
            setOnClickListener(mOnClickListener)
        }

    override fun getItemCount() = values.size

    inner class ViewHolder(val mView: TextView) : RecyclerView.ViewHolder(mView)
}
