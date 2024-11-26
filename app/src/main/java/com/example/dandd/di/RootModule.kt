package com.example.dandd.di

import androidx.datastore.core.DataStore
import com.example.dandd.data.converter.ClassDbToClassItem
import com.example.dandd.data.converter.ClassDbToClassItemImpl
import com.example.dandd.data.converter.ItemDbToItem
import com.example.dandd.data.converter.ItemDbToItemImpl
import com.example.dandd.data.converter.ProfileDbToProfileItem
import com.example.dandd.data.converter.ProfileDbToProfileItemImpl
import com.example.dandd.data.model.ProfileDb
import com.example.dandd.data.repo.DataSourceProvider
import com.example.dandd.data.repo.DatastoreRepository
import com.example.dandd.data.repo.DatastoreRepositoryImpl
import com.example.dandd.data.repo.ItemRepository
import com.example.dandd.data.repo.ItemRepositoryImpl
import com.example.dandd.data.repo.ProfileRepository
import com.example.dandd.data.repo.ProfileRepositoryImpl
import com.example.dandd.data.retrofit.converter.ClassesNetworkToClassDb
import com.example.dandd.data.retrofit.converter.ClassesNetworkToClassDbImpl
import com.example.dandd.data.retrofit.converter.ItemNetworkToItemDb
import com.example.dandd.data.retrofit.converter.ItemNetworkToItemDbImpl
import com.example.dandd.domain.converter.ClassToClassView
import com.example.dandd.domain.converter.ClassToClassViewImpl
import com.example.dandd.domain.converter.ItemToItemView
import com.example.dandd.domain.converter.ItemToItemViewImpl
import com.example.dandd.domain.converter.ProfileToProfileView
import com.example.dandd.domain.converter.ProfileToProfileViewImpl
import com.example.dandd.domain.usecase.ClassUseCase
import com.example.dandd.domain.usecase.ClassUseCaseImpl
import com.example.dandd.domain.usecase.DatastoreUseCase
import com.example.dandd.domain.usecase.DatastoreUseCaseImpl
import com.example.dandd.domain.usecase.ItemsUseCase
import com.example.dandd.domain.usecase.ItemsUseCaseImpl
import com.example.dandd.domain.usecase.ProfileUseCase
import com.example.dandd.domain.usecase.ProfileUseCaseImpl
import com.example.dandd.presentation.ui.activity.MainActivityViewModel
import com.example.dandd.presentation.ui.fragment.dataList.DataListViewModel
import com.example.dandd.presentation.ui.fragment.detail.DataDetailViewModel
import com.example.dandd.presentation.ui.fragment.favourites.FavouritesViewModel
import com.example.dandd.presentation.ui.fragment.home.HomeViewModel
import com.example.dandd.presentation.ui.fragment.profileEdit.EditProfileViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val rootModule = module {
    singleOf(::ItemRepositoryImpl) { bind<ItemRepository>() }
    singleOf(::DatastoreRepositoryImpl) { bind<DatastoreRepository>() }
    singleOf(::ProfileRepositoryImpl) { bind<ProfileRepository>() }
    factory<ItemDbToItem> { ItemDbToItemImpl() }
    factory<ItemNetworkToItemDb> { ItemNetworkToItemDbImpl() }
    factory<ClassesNetworkToClassDb> { ClassesNetworkToClassDbImpl() }
    factory<ClassDbToClassItem> { ClassDbToClassItemImpl() }
    factory<ClassToClassView> { ClassToClassViewImpl() }
    factory<ProfileToProfileView> { ProfileToProfileViewImpl() }
    factory<ProfileDbToProfileItem> { ProfileDbToProfileItemImpl() }
    single<DataListViewModel> { DataListViewModel(get(), get(), get()) }
    single<DataDetailViewModel> { DataDetailViewModel(get(), get()) }
    single<HomeViewModel> { HomeViewModel(get(), get()) }
    single<MainActivityViewModel> { MainActivityViewModel(get()) }
    single<FavouritesViewModel> { FavouritesViewModel(get(), get()) }
    single<EditProfileViewModel>{ EditProfileViewModel(get(), get()) }

    factory<DataStore<ProfileDb>>(named("profile")) { DataSourceProvider(get()).provide() }

    single<ItemsUseCase> { ItemsUseCaseImpl(get()) }
    single<ItemToItemView> { ItemToItemViewImpl() }
    single<ClassUseCase> { ClassUseCaseImpl(get()) }
    single<DatastoreUseCase> { DatastoreUseCaseImpl(get()) }
    single<ProfileUseCase> { ProfileUseCaseImpl(get()) }
}