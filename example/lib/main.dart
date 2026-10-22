// ignore_for_file: avoid_print, unused_local_variable

import 'package:android_native_path/android_native_path.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MaterialApp(home: const MyApp()));
}

class MyApp extends StatefulWidget {
  const new({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  void fetch() async {
    final pkg = AndroidNativePath.instance;

    final path = await pkg.nativeLibraryDir;
    final files = await pkg.nativeLibraries;
    final file = await pkg.getNativeLib('lib name.so');
    print(path);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Native Lib')),
      floatingActionButton: FloatingActionButton(onPressed: fetch),
    );
  }
}
