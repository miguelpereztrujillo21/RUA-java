package com.mpt.rua_java.data.local.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import com.mpt.rua_java.data.local.dao.UserDao;
import com.mpt.rua_java.data.local.entity.UserEntity;

/**
 * Base de datos Room para la aplicación
 * Gestiona la persistencia local de los 100 usuarios de RandomUser
 */
@Database(
    entities = {UserEntity.class},
    version = 1,
    exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public static final String DATABASE_NAME = "rua_java_database";
}
