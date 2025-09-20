package com.mpt.rua_java.di;

import android.content.Context;
import androidx.room.Room;
import com.mpt.rua_java.data.local.database.AppDatabase;
import com.mpt.rua_java.data.local.dao.UserDao;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

/**
 * Módulo de inyección de dependencias para la base de datos
 * Configura Room database y DAOs
 */
@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(
            context,
            AppDatabase.class,
            AppDatabase.DATABASE_NAME
        ).build();
    }

    @Provides
    public UserDao provideUserDao(AppDatabase database) {
        return database.userDao();
    }
}
