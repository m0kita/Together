package ru.mokita.together.di.local

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.mokita.together.data.source.local.SharedPreferencesHelper

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideSharedPreferencesHelper(sharedPreferences: SharedPreferences): SharedPreferencesHelper {
        return SharedPreferencesHelper(sharedPreferences)
    }
}