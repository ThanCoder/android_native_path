# android_native_path

## Example

```dart
final pkg = AndroidNativePath.instance;

final path = await pkg.nativeLibraryDir;
final files = await pkg.nativeLibraries;
final file = await pkg.getNativeLib('lib name.so');
print(path);
```

### Android build.gradle.kts

```kts

// android/app/build.gradle.kts

android {
    // ရှိပြီးသား configuration တွေကို မဖျက်ပါနဲ့။

    packaging {
        jniLibs {
            useLegacyPackaging = true
        }
    }
}
```
