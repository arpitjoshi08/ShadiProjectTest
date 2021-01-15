package com.test.sampleapp.core.plateform

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel()

abstract class BaseAndroidViewModel(application: Application) : AndroidViewModel(application)