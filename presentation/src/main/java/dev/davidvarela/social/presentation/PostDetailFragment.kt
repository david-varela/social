package dev.davidvarela.social.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import dev.davidvarela.social.presentation.databinding.PostDetailBinding
import dev.davidvarela.social.presentation.viewmodels.PostDetailViewModel
import dev.davidvarela.social.domain.entities.PostPreview
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A fragment representing a single Post detail screen.
 * This fragment is either contained in a [PostListActivity]
 * in two-pane mode (on tablets) or a [PostDetailActivity]
 * on handsets.
 */
class PostDetailFragment : Fragment() {
    private var _binding: PostDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var post: PostPreview

    private val viewModel: PostDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM)) {
                post = it.getSerializable(ARG_ITEM) as PostPreview
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PostDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root
        with(binding) {
            postDetailTitleTextview.text = post.title
            postDetailBodyTextview.text = post.body
        }
        observers()
        viewModel.getImageUrl(post.userId)
        viewModel.getNumComments(post.postId)
        return rootView
    }

    private fun observers() {
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.imageUrl.observe(viewLifecycleOwner) {
            Picasso.get().load(it).into(binding.postDetailUserImageView)
        }
        viewModel.numComments.observe(viewLifecycleOwner) {
            binding.postDetailCommentsTextview.text = it.toString()
        }
    }

    companion object {
        const val ARG_ITEM = "post"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
