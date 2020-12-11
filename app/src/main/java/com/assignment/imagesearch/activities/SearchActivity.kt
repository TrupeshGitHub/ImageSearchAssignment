package com.assignment.imagesearch.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.assignment.imagesearch.R
import com.assignment.imagesearch.adapters.ImageListAdapter
import com.assignment.imagesearch.model.Data
import com.assignment.imagesearch.viewmodels.MainActivityViewModel
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit

class SearchActivity : AppCompatActivity(), ImageListAdapter.OnClickListener {

    private val imageListAdapter: ImageListAdapter by lazy {
        ImageListAdapter(this, this)
    }

    private val mainActivityViewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Image Search"

        setSearchViewListeners()
        setUpAdapterToList()
        setUpViewModelObserver()
    }

    private fun setUpViewModelObserver() {
        mainActivityViewModel.isMoreApiInProgress.observe(this, {
            if (it) {
                loadMoreProgress.visibility = View.VISIBLE
            } else {
                loadMoreProgress.visibility = View.GONE
            }
        })

        mainActivityViewModel.isSearchApiInProgress.observe(this, {
            if (it) {
                searchProgressBar.visibility = View.VISIBLE
                emptyText.visibility = View.GONE
            } else {
                searchProgressBar.visibility = View.GONE
            }
        })

        mainActivityViewModel.imageDataList.observe(this, { list ->
            if (list.isEmpty()) {
                imageListView.visibility = View.GONE
                emptyText.visibility = View.VISIBLE
            } else {
                imageListView.visibility = View.VISIBLE
                emptyText.visibility = View.GONE
            }

            imageListAdapter.setData(list)
        })
    }

    private fun setUpAdapterToList() {
        imageListView.adapter = imageListAdapter
    }

    @SuppressLint("CheckResult")
    private fun setSearchViewListeners() {

        imageSearchView.queryHint = getString(R.string.search_image)

        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            imageSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    subscriber.onNext(query!!)
                    getSearchImage(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    subscriber.onNext(newText!!)
                    return false
                }

            })
        }).debounce(250, TimeUnit.MILLISECONDS)
            .distinct()
            .filter { text -> text.isNotBlank() }
            .subscribe { text ->
                getSearchImage(text)
            }
    }

    private fun getSearchImage(text: String?) {
        CoroutineScope(Dispatchers.Main).launch {
            text?.let { mainActivityViewModel.getImages(this@SearchActivity, it, false) }
        }
    }

    override fun onClick(data: Data, position: Int) {
        val intent = Intent(this, ImageDetailActivity::class.java)
        intent.putParcelableArrayListExtra("ImageList", data.images as ArrayList<out Parcelable>)
        intent.putExtra("dataID", data.id)
        intent.putExtra("title", data.title)
        startActivity(intent)
    }
}