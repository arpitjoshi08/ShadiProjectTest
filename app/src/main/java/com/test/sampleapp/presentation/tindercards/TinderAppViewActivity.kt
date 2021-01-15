package com.test.sampleapp.presentation.tindercards

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.apilib.response.model.cardlist.CardList
import com.apilib.response.model.cardlist.CardListItemResponse
import com.test.sampleapp.AppClass
import com.test.sampleapp.R
import com.test.sampleapp.core.plateform.BaseViewModelFactory
import com.test.sampleapp.core.plateform.NonNullObserver
import com.test.sampleapp.databinding.ActivityTinderCardViewBinding
import com.test.sampleapp.utils.Utils
import com.yuyakaido.android.cardstackview.*
import com.yuyakaido.android.cardstackview.sample.CardStackAdapter
import kotlinx.android.synthetic.main.activity_tinder_card_view.*

class TinderAppViewActivity : AppCompatActivity(), CardStackListener {

    //region - public properties
    lateinit var viewModel: TinderCardViewModel
    lateinit var binding: ActivityTinderCardViewBinding
    private val cardStackView by lazy { findViewById<CardStackView>(R.id.card_stack_view) }
    private val manager by lazy { CardStackLayoutManager(this, this) }
    private val adapter by lazy { CardStackAdapter() }
    private var itemCount: Int = 0
    //endregion

    //region - lifecycle functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tinder_card_view)
        initViewModel()
        initObserver()
        initialize()
        viewModel.fetchList()
        setOnClick()
    }
    //endregion

    //region - private functions
    private fun setOnClick() {
        declineBtn?.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            cardStackView.swipe()
        }
        acceptBtn?.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            cardStackView.swipe()
        }
        refreshBtn?.setOnClickListener {
            viewModel.fetchList()
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders
            .of(this, BaseViewModelFactory {
                TinderCardViewModel(TinderCardModel(AppClass.instance.apiContract))
            }).get(TinderCardViewModel::class.java)
        binding.viewModel = viewModel
    }

    private fun initialize() {
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())
    }

    private fun setAdapter(results: List<CardList>) {
        itemCount = 0
        refreshBtn.visibility = View.GONE
        if (!results.isNullOrEmpty()) {
            adapter.setCardList(results)
            adapter.notifyDataSetChanged()
            cardStackView.layoutManager = manager
            cardStackView.adapter = adapter
            cardStackView.itemAnimator.apply {
                if (this is DefaultItemAnimator) {
                    supportsChangeAnimations = false
                }
            }
            buttonLayout.visibility=View.VISIBLE
            noDataFoundText.visibility = View.GONE
        } else {
            noDataFoundText.visibility = View.VISIBLE
        }
    }

    private fun initObserver() {
        viewModel.showErrorMessage().observe(this, object : NonNullObserver<String>() {
            override fun onNonNullChanged(value: String) {
                Utils.showToast(this@TinderAppViewActivity, value)
                progressBar.visibility = View.GONE
            }
        })

        viewModel.showHideProgress().observe(this, object : NonNullObserver<Boolean>() {
            override fun onNonNullChanged(value: Boolean) {
                if (value) {
                    progressBar.visibility = View.VISIBLE
                    refreshBtn.visibility = View.GONE
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        })
        viewModel.fieldErrorLiveData.observe(this, object : NonNullObserver<String>() {
            override fun onNonNullChanged(value: String) {
                Utils.showToast(this@TinderAppViewActivity, value)
                progressBar.visibility = View.GONE
            }
        })

        viewModel.showSuccess().observe(this, object : NonNullObserver<CardListItemResponse>() {
            override fun onNonNullChanged(value: CardListItemResponse) {
                progressBar.visibility = View.GONE
                if (value != null) {
                    setAdapter(value.results)
                } else {
                    noDataFoundText.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onCardDisappeared(view: View?, position: Int) {
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {
        itemCount = itemCount + 1
        if (itemCount == adapter.getCardList().size) {
            Log.d("card size", "size equals")
            refreshBtn.visibility = View.VISIBLE
        } else {
            Log.d("card size", "" + adapter.getCardList().size)
        }
    }

    override fun onCardCanceled() {
    }

    override fun onCardAppeared(view: View?, position: Int) {
    }

    override fun onCardRewound() {
    }
    //endregion
}