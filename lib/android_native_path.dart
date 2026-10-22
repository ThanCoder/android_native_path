import 'dart:io';

import 'package:flutter/services.dart';

class AndroidNativePath {
  static final AndroidNativePath instance = AndroidNativePath._();
  AndroidNativePath._();
  factory AndroidNativePath() => instance;

  final _channel = const MethodChannel('android_native_path');

  /// get native lib path
  Future<String?> getNativeLib([String? name]) async {
    final path = await nativeLibraryDir;
    if (path == null) return null;

    if (name != null) {
      return '$path${Platform.pathSeparator}$name';
    }
    return path;
  }

  /// get native lib folder
  Future<String?> get nativeLibraryDir async {
    final res = await _channel.invokeMethod<String>('getNativeLibraryDir');
    return res;
  }

  /// all native lib files
  Future<List<String>> get nativeLibraries async {
    try {
      final List<dynamic> result = await _channel.invokeMethod(
        'getNativeLibrariesList',
      );
      return result.cast<String>();
    } on PlatformException catch (e) {
      // print("Failed to get libraries: '${e.message}'.");
      return [];
    }
  }
}
