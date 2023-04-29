package com.mt.face_sdk_3divi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

const val FLUTTER_ENGINE_ID = "flutter_engine"

class MainActivity : AppCompatActivity() {

    private val CHANNEL = "com.flutterKotlin/result"
    lateinit var methodChannel: MethodChannel
    private lateinit var flutterEngine: FlutterEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dataDir: String = applicationInfo.dataDir
        val libDir: String = applicationInfo.nativeLibraryDir

        flutterEngine = FlutterEngine(this)
        flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        FlutterEngineCache.getInstance().put(FLUTTER_ENGINE_ID, flutterEngine)
        val accessKey = "<yourAccessKey>"
        methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
        methodChannel.invokeMethod("initiateSdk", mapOf("accessKey" to accessKey, "libDir" to libDir, "dataDir" to dataDir))//pass the "accessKey" argument
    }
}