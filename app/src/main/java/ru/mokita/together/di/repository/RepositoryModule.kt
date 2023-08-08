package ru.mokita.together.di.repository

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.mokita.together.data.repository.AuthentificationRepositoryImpl
import ru.mokita.together.data.repository.CommunityNoteRepositoryImpl
import ru.mokita.together.data.repository.CourseRepositoryImpl
import ru.mokita.together.data.repository.NoteRepositoryImpl
import ru.mokita.together.domain.repository.AuthentificationRepository
import ru.mokita.together.domain.repository.CommunityNoteRepository
import ru.mokita.together.domain.repository.CourseRepository
import ru.mokita.together.domain.repository.NoteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideNoteRepository(noteRepositoryImpl: NoteRepositoryImpl): NoteRepository

    @Binds
    abstract fun provideAuthentificationRepository(authentificationRepositoryImpl: AuthentificationRepositoryImpl): AuthentificationRepository

    @Binds
    abstract fun provideCourseRepository(courseRepositoryImpl: CourseRepositoryImpl): CourseRepository

    @Binds
    abstract fun provideCommunityNoteRepository(communityNoteRepositoryImpl: CommunityNoteRepositoryImpl): CommunityNoteRepository
}