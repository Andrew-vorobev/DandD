package com.example.dandd.di

import com.example.dandd.data.converter.ClassDbToClassItem
import com.example.dandd.data.converter.ClassDbToClassItemImpl
import com.example.dandd.data.converter.ItemDbToItem
import com.example.dandd.data.converter.ItemDbToItemImpl
import com.example.dandd.data.dao.ItemDao
import com.example.dandd.data.dao.ItemDaoImpl
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
import com.example.dandd.domain.usecase.ItemsUseCase
import com.example.dandd.domain.usecase.ItemsUseCaseImpl
import com.example.dandd.presentation.ui.fragment.dataList.DataListViewModel
import com.example.dandd.presentation.ui.fragment.notifications.NotificationsViewModel
import com.example.dungeonanddragonsapp.presentation.ui.fragment.detail.DataDetailViewModel
import com.example.dungeonanddragonsapp.presentation.ui.fragment.home.HomeViewModel
import org.koin.dsl.module

val rootModule = module{
    single<ItemRepository> { ItemRepositoryImpl(get(), get(), get(), get(), get(), get()) }
    single<ItemDao> { ItemDaoImpl() }
    factory<ItemDbToItem> { ItemDbToItemImpl() }
    factory<ItemNetworkToItemDb> { ItemNetworkToItemDbImpl() }
    factory<ClassesNetworkToClassDb> {ClassesNetworkToClassDbImpl()}
    factory<ClassDbToClassItem> { ClassDbToClassItemImpl() }
    factory<ClassToClassView> {ClassToClassViewImpl()}

    single<DataListViewModel> {DataListViewModel(get(), get(),get(),get())}
    single<DataDetailViewModel> {DataDetailViewModel()}
    single<HomeViewModel> {HomeViewModel()}
    single<NotificationsViewModel> {NotificationsViewModel()}

    single<ItemsUseCase> { ItemsUseCaseImpl(get()) }
    single<ItemToItemView>{ ItemToItemViewImpl()}
    single<ClassUseCase>{ClassUseCaseImpl(get())}
}