# Android Native Path

`android_native_path` is a lightweight Flutter plugin that provides access to the application's native library directory and the native libraries stored inside it on Android.

It uses Flutter's `MethodChannel` to communicate with the Android platform.

## Features

- Retrieve the application's native library directory.
- Get the absolute path of a specific native library.
- Retrieve a list of available native library files.
- Singleton-based API for convenient access.

- [x] [Android Configuration](#android-configuration)

## Example

```dart
final pkg = AndroidNativePath.instance;

final path = await pkg.nativeLibraryDir;
final files = await pkg.nativeLibraries;
final file = await pkg.getNativeLib('lib name.so');
print(path);
```

## Installation

Add the dependency to your `pubspec.yaml`:

```yaml
dependencies:
  android_native_path: ^0.2.0
```

Then run:

```bash
flutter pub get
```

## Usage

Import the package:

```dart
import 'package:android_native_path/android_native_path.dart';
```

### 1. Get the Native Library Directory

Returns the absolute path to the application's native library directory.

```dart
final path = await AndroidNativePath.instance.nativeLibraryDir;

print(path);
```

### 2. Get a Specific Native Library Path

Use `getNativeLib()` to retrieve the absolute path of a native library.

```dart
final path = await AndroidNativePath.instance.getNativeLib(
  'libffmpeg.so',
);

print(path);
```

If the library name is omitted, it returns the native library directory.

```dart
final path = await AndroidNativePath.instance.getNativeLib();
```

### 3. Get All Native Libraries

Use `nativeLibraries` to retrieve the names of all available native library files.

```dart
final libraries = await AndroidNativePath.instance.nativeLibraries;

for (final library in libraries) {
  print(library);
}
```

## Android Configuration

For Android, enable legacy JNI library packaging in your `android/app/build.gradle.kts` file.

```kotlin
android {
    // Keep your existing Android configuration.

    packaging {
        jniLibs {
            useLegacyPackaging = true
        }
    }
}
```

If retrieving the library list fails, the getter returns an empty list.

## API Reference

| API                    | Return Type            | Description                                   |
| ---------------------- | ---------------------- | --------------------------------------------- |
| `instance`             | `AndroidNativePath`    | Singleton instance                            |
| `nativeLibraryDir`     | `Future<String?>`      | Gets the native library directory             |
| `getNativeLib([name])` | `Future<String?>`      | Gets the directory or a specific library path |
| `nativeLibraries`      | `Future<List<String>>` | Gets all available native library file names  |

## Platform Support

| Platform | Supported |
| -------- | --------- |
| Android  | Yes       |
| Linux    | No        |
| Windows  | No        |
| iOS      | No        |
| macOS    | No        |

## Requirements

- Flutter SDK `>= 3.50.0`
- Dart SDK `^3.14.0`
- Android platform

## Notes

- This plugin is designed specifically for Android.
- Returned paths refer to the application's native library directory.
- The plugin retrieves library paths; it does not load or execute native libraries.
- Access to native library files is subject to Android's application sandbox and platform restrictions.

## License

See the [LICENSE](LICENSE) file for details.
