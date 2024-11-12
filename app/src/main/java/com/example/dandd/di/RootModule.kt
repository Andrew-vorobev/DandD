package com.example.dandd.di

import com.example.dandd.data.converter.ClassDbToClassItem
import com.example.dandd.data.converter.ClassDbToClassItemImpl
import com.example.dandd.data.converter.ItemDbToItem
import com.example.dandd.data.converter.ItemDbToItemImpl
import com.example.dandd.data.repo.DatastoreRepository
import com.example.dandd.data.repo.DatastoreRepositoryImpl
import com.example.dandd.data.repo.ItemRepository
import com.example.dandd.data.repo.ItemRepositoryImpl
import com.example.dandd.data.retrofit.converter.ClassesNetworkToClassDb
import com.example.dandd.data.retrofit.converter.ClassesNetworkToClassDbImpl
import com.example.dandd.data.retrofit.converter.ItemNetworkToItemDb
import com.example.dandd.data.retrofit.converter.ItemNetworkToItemDbImpl
import com.example.dandd.domain.converter.ClassToClassView
import com.example.dandd.domain.converter.ClassToClassViewImpl
import com.example.dandd.domain.converter.ItemToItemView
import com.example.dandd.domain.converter.ItemToItemViewImpl
import com.example.dandd.domain.usecase.ClassUseCase
import com.example.dandd.domain.usecase.ClassUseCaseImpl
import com.example.dandd.domain.usecase.DatastoreUseCase
import com.example.dandd.domain.usecase.DatastoreUseCaseImpl
import com.example.dandd.domain.usecase.ItemsUseCase
import com.example.dandd.domain.usecase.ItemsUseCaseImpl
import com.example.dandd.presentation.ui.activity.MainActivityViewModel
import com.example.dandd.presentation.ui.fragment.dataList.DataListViewModel
import com.example.dandd.presentation.ui.fragment.detail.DataDetailViewModel
import com.example.dandd.presentation.ui.fragment.home.HomeViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val rootModule = module{
//    single<ItemRepository> { ItemRepositoryImpl(get(), get(), get(), get(), get(), get()) }
    singleOf(::ItemRepositoryImpl) { bind<ItemRepository>() }
    singleOf(::DatastoreRepositoryImpl) { bind<DatastoreRepository>() }
    factory<ItemDbToItem> { ItemDbToItemImpl() }
    factory<ItemNetworkToItemDb> { ItemNetworkToItemDbImpl() }
    factory<ClassesNetworkToClassDb> {ClassesNetworkToClassDbImpl()}
    factory<ClassDbToClassItem> { ClassDbToClassItemImpl() }
    factory<ClassToClassView> {ClassToClassViewImpl()}

    single<DataListViewModel> {DataListViewModel(get(), get(), get())}
    single<DataDetailViewModel> { DataDetailViewModel(get(),get()) }
    single<HomeViewModel> { HomeViewModel() }
    single<MainActivityViewModel> { MainActivityViewModel(get()) }
//    single<FavouritesViewModel> {FavouritesViewModel(get(), get())}

    single<ItemsUseCase> { ItemsUseCaseImpl(get()) }
    single<ItemToItemView>{ ItemToItemViewImpl()}
    single<ClassUseCase>{ClassUseCaseImpl(get())}
    single<DatastoreUseCase>{ DatastoreUseCaseImpl(get()) }
}