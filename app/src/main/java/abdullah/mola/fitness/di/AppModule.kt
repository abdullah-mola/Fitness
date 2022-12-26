package abdullah.mola.fitness.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import abdullah.mola.fitness.db.RunDatabase
import abdullah.mola.fitness.utils.Constants.FIRST_RUN
import abdullah.mola.fitness.utils.Constants.KEY_NAME
import abdullah.mola.fitness.utils.Constants.KEY_WEIGHT
import abdullah.mola.fitness.utils.Constants.RUN_DATABASE_NAME
import abdullah.mola.fitness.utils.Constants.SHARED_PREFS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRunDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        RunDatabase::class.java,
        RUN_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRunDao(runDatabase: RunDatabase) = runDatabase.getRunDao()

    @Singleton
    @Provides
    fun provideSharedPrefs(@ApplicationContext app: Context): SharedPreferences =
        app.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideName(sharedPref: SharedPreferences) = sharedPref.getString(KEY_NAME, "") ?: ""

    @Singleton
    @Provides
    fun provideWight(sharedPref: SharedPreferences) = sharedPref.getFloat(KEY_WEIGHT, 70f)

    @Singleton
    @Provides
    fun provideFirstRun(sharedPref: SharedPreferences) = sharedPref.getBoolean(FIRST_RUN, true)


}