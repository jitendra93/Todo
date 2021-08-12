package com.jitendraalekar.todo

import android.app.Application
import com.jitendraalekar.todo.repo.TodoDatabase
import com.jitendraalekar.todo.ui.SingleModelMotor
import com.jitendraalekar.todo.ui.roster.RosterMotor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module


class ToDoApp : Application() {

    private val koinModule = module {
        single(named("appScope")) { CoroutineScope(SupervisorJob()) }
        single { TodoDatabase.newInstance(androidContext()) }
        single {
            ToDoRepository(
                get<TodoDatabase>().todoStore(),
                get(named("appScope"))
            )
        }
        viewModel { RosterMotor(get()) }

        viewModel { (modelId: String?) -> SingleModelMotor(get(), modelId) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ToDoApp)
            modules(koinModule)
        }
    }
}