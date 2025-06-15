package com.hse.hseproject.util

import android.content.Context
import android.util.Log
import com.getkeepsafe.relinker.ReLinker
import com.hse.hseproject.data.datasource.local.guest.LocalDataSourceGuest
import com.hse.hseproject.data.datasource.local.student.LocalDataSourceStudent
import com.hse.hseproject.data.datasource.remote.event.RemoteDataSourceEvent
import com.hse.hseproject.data.datasource.remote.guest.RemoteDataSourceGuest
import com.hse.hseproject.data.datasource.remote.student.RemoteDataSourceStudent
import com.hse.hseproject.data.datasource.remote.ticket.RemoteDataSourceTicket
import com.hse.hseproject.data.network.apiService.ApiServiceEvent
import com.hse.hseproject.data.network.apiService.ApiServiceGuest
import com.hse.hseproject.data.network.apiService.ApiServiceProvider
import com.hse.hseproject.data.network.apiService.ApiServiceStudent
import com.hse.hseproject.data.network.apiService.ApiServiceTicket
import com.hse.hseproject.data.repository.EventRepositoryImpl
import com.hse.hseproject.domain.entity.MyObjectBox
import dagger.Module
import dagger.Provides
import com.hse.hseproject.data.repository.StudentRepositoryImpl
import com.hse.hseproject.data.repository.GuestRepositoryImpl
import com.hse.hseproject.data.repository.TicketRepositoryImpl
import com.hse.hseproject.domain.repository.EventRepository
import com.hse.hseproject.domain.repository.GuestRepository
import com.hse.hseproject.domain.repository.StudentRepository
import com.hse.hseproject.domain.repository.TicketRepository
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.objectbox.BoxStore
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }


    @Provides
    @Singleton
    fun provideApiServiceStudent(): ApiServiceStudent {
        return ApiServiceProvider.apiServiceStudent
    }

    @Provides
    @Singleton
    fun provideApiServiceGuest(): ApiServiceGuest {
        return ApiServiceProvider.apiServiceGuest
    }

    @Provides
    @Singleton
    fun provideApiServiceTicket(): ApiServiceTicket {
        return ApiServiceProvider.apiServiceTicket
    }

    @Provides
    @Singleton
    fun provideApiServiceEvent(): ApiServiceEvent {
        return ApiServiceProvider.apiServiceEvent
    }


    @Provides
    @Singleton
    fun provideRemoteDataSourceStudent(apiServiceStudent: ApiServiceStudent): RemoteDataSourceStudent{
        return RemoteDataSourceStudent(apiServiceStudent)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSourceTicket(apiServiceTicket: ApiServiceTicket): RemoteDataSourceTicket{
        return RemoteDataSourceTicket(apiServiceTicket)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSourceEvent(apiServiceEvent: ApiServiceEvent): RemoteDataSourceEvent{
        return RemoteDataSourceEvent(apiServiceEvent)
    }



    @Provides
    @Singleton
    fun provideLocalDataSourceGuest(boxStore: BoxStore): LocalDataSourceGuest {
        return LocalDataSourceGuest(boxStore)
    }

    @Provides
    @Singleton
    fun provideLocalDataSourceStudent(boxStore: BoxStore): LocalDataSourceStudent {
        return LocalDataSourceStudent(boxStore)
    }


    @Provides
    @Singleton
    fun provideTicketRepository(
        remoteDataSourceTicket: RemoteDataSourceTicket,
    ): TicketRepository {
        return TicketRepositoryImpl(
            remoteDataSourceTicket
        )
    }

    @Provides
    @Singleton
    fun provideEventRepository(
        remoteDataSourceEvent: RemoteDataSourceEvent,
    ): EventRepository {
        return EventRepositoryImpl(
            remoteDataSourceEvent
        )
    }


    @Provides
    @Singleton
    fun provideStudentRepository(
        remoteDataSourceStudent: RemoteDataSourceStudent,
        localDataSourceStudent: LocalDataSourceStudent
    ): StudentRepository {
        return StudentRepositoryImpl(
            remoteDataSourceStudent,
            localDataSourceStudent
        )
    }

    @Provides
    @Singleton
    fun provideGuestRepository(
        remoteDataSourceGuest: RemoteDataSourceGuest,
        localDataSourceGuest: LocalDataSourceGuest
    ): GuestRepository {
        return GuestRepositoryImpl(
            remoteDataSourceGuest,
            localDataSourceGuest
        )
    }


    @Provides
    @Singleton
    fun provideBoxStore(@ApplicationContext context: Context): BoxStore {
        val boxStore = MyObjectBox.builder()
            .androidContext(context)
            .androidReLinker(ReLinker.log(object : ReLinker.Logger {
                override fun log(message: String) {
                    Log.d("ObjectBoxLog", message)
                }
            }))
            .build()
//        boxStore.close()
//        boxStore.deleteAllFiles()
         // Закрываем существующее подключение

        return boxStore;


    }

}