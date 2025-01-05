package com.baron.barsiktv2.di

import com.baron.domain.repository.BarsikRetrofitRepository
import com.baron.domain.usecase.BarsikRetrofitUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideBarsikRetrofitUseCase(barsikRetrofitRepository: BarsikRetrofitRepository): BarsikRetrofitUseCase =
        BarsikRetrofitUseCase(barsikRetrofitRepository = barsikRetrofitRepository)
}