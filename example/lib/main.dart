// ignore_for_file: unused_import, avoid_print, unused_local_variable

import 'dart:io';

import 'package:android_native_path/android_native_path.dart';
import 'package:material_ui/material_ui.dart';

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

    // final path = await pkg.nativeLibraryDir;
    final files = await pkg.nativeLibraries;
    setState(() {
      resText = files.toString();
    });
  }

  String resText = '';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Native Lib')),
      floatingActionButton: FloatingActionButton(onPressed: fetch),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Text(resText),
      ),
    );
  }
}
