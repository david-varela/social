package dev.davidvarela.social.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import dev.davidvarela.social.presentation.databinding.ActivityPostListBinding
import dev.davidvarela.social.presentation.viewmodels.PostsViewModel
import dev.davidvarela.social.domain.entities.PostPreview
import org.koin.android.viewmodel.ext.android.viewModel

interface OnListFragmentInteractionListener {
    fun onListFragmentInteraction(post: PostPreview)
}

class PostListActivity : AppCompatActivity(), OnListFragmentInteractionListener {
    private lateinit var binding: ActivityPostListBinding

    private var twoPane: Boolean = false

    private val viewModel: PostsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        twoPane = binding.postDetailContainer != null
        binding.postList.adapter = PostsRecyclerViewAdapter(this)

        viewModel.postsPreviews.observe(this)
        { postPreviews ->
            with(binding.postList.adapter as PostsRecyclerViewAdapter) {
                this.values.addAll(postPreviews)
                this.notifyDataSetChanged()
            }
        }

        viewModel.error.observe(this) { makeText(this, it, Toast.LENGTH_SHORT).show() }
    }

    override fun onStart() {
        super.onStart()
        viewModel.refreshPosts()
    }

    override fun onListFragmentInteraction(post: PostPreview) {
        if (twoPane) {
            val fragment = PostDetailFragment().apply {
                arguments = Bundle().apply { putSerializable(PostDetailFragment.ARG_ITEM, post) }
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.post_detail_container, fragment)
                .commit()
        } else {
            val intent = Intent(this, PostDetailActivity::class.java).apply {
                putExtra(PostDetailFragment.ARG_ITEM, post)
            }
            startActivity(intent)
        }
    }
}
