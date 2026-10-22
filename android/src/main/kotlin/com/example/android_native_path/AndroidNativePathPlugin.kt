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
    when (call.method) {
        "getNativeLibraryDir" -> {
            result.success(context.applicationInfo.nativeLibraryDir)
        }

        "getNativeLibraryPath" -> {
            val name = call.argument<String>("name")

            if (name == null) {
                result.error(
                    "INVALID_ARGUMENT",
                    "Library name is required",
                    null
                )
                return
            }

            val fileName = if (name.endsWith(".so")) {
                name
            } else {
                "lib$name.so"
            }

            val file = File(
                context.applicationInfo.nativeLibraryDir,
                fileName
            )

            if (file.isFile) {
                result.success(file.absolutePath)
            } else {
                result.error(
                    "NOT_FOUND",
                    "Native library not found: $fileName",
                    null
                )
            }
        }

        "getNativeLibrariesList" -> {
            val nativeDir = File(context.applicationInfo.nativeLibraryDir)

            val libraries = nativeDir
                .listFiles { file ->
                    file.isFile && file.name.endsWith(".so")
                }
                ?.map { it.absolutePath }
                ?: emptyList()

            result.success(libraries)
        }

        else -> result.notImplemented()
    }
}

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}


