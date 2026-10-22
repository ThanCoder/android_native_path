# android_native_path

## Example

```dart
final pkg = AndroidNativePath.instance;

final path = await pkg.nativeLibraryDir;
final files = await pkg.nativeLibraries;
final file = await pkg.getNativeLib('lib name.so');
print(path);
```