package com.saucefan.stuff.kierantration

import android.app.Application
import android.util.Log
import timber.log.Timber



    class MyDebugTree : Timber.DebugTree() {
        override fun createStackElementTag(element: StackTraceElement): String? {
            return String.format(
                "[C:%s] [M:%s] [L:%s]",
                super.createStackElementTag(element),
                element.methodName,
                element.lineNumber
            )
        }
    }

    class KieranTration : Application() {

        override fun onCreate() {
            super.onCreate()
            if (BuildConfig.DEBUG) {
                Timber.plant(MyDebugTree())
            }
            else {
                Timber.plant(CrashReportingTree());
            }
        }


        /** A tree which logs important information for crash reporting. */
        inner class CrashReportingTree : Timber.Tree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                    return
                }
                val fakeCrashLibrary=FakeCrashLibrary()
                fakeCrashLibrary.log(priority, tag, message);

                if (t != null) {
                    if (priority == Log.ERROR) {
                        fakeCrashLibrary.logError(t)
                    } else if (priority == Log.WARN) {
                        fakeCrashLibrary.logWarning(t)
                    }


                }
            }
        }
        inner class FakeCrashLibrary{
            fun log(priority: Int,tag: String?,message: String){
                Timber.log(priority,tag,message)
            }

            fun logError(t: Throwable?){
                Timber.e(t)
            }
            fun logWarning(t:Throwable?) {
                Timber.e(t)
            }
        }

    }




