package com.example.android_native_path

import android.content.Context
import java.io.File

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

class AndroidNativePathPlugin: FlutterPlugin, MethodCallHandler {
    private lateinit var channel : MethodChannel
    private lateinit var context: Context

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        context = flutterPluginBinding.applicationContext
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "android_native_path")
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        if (call.method == "getNativeLibraryDir") {
            // Android applicationInfo.nativeLibraryDir ကို တိုက်ရိုက်ယူခြင်း
            val libDir = context.applicationInfo.nativeLibraryDir
            result.success(libDir)
        } else if (call.method == "getNativeLibrariesList") {
            val nativeDir = File(context.applicationInfo.nativeLibraryDir)
            val libraries = nativeDir.listFiles { file ->
            file.isFile && file.name.endsWith(".so")
            }?.map { it.name } ?: emptyList()
            
            result.success(libraries)   
        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}