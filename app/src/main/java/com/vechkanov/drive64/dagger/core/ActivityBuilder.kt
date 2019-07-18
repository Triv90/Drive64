package com.vechkanov.drive64.dagger.core

import com.vechkanov.drive64.ui.login.LoginActivity
import com.vechkanov.drive64.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun bindLoginActivity(): LoginActivity
}