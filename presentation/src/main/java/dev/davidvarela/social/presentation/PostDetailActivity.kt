package dev.davidvarela.social.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PostDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        if (savedInstanceState == null) {
            val fragment = PostDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(
                        PostDetailFragment.ARG_ITEM,
                        intent.getSerializableExtra(PostDetailFragment.ARG_ITEM)
                    )
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.post_detail_container, fragment)
                .commit()
        }
    }
}
