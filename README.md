# React Native Cordova (remobile)
A cordova plugin defines for react native on ios and android

## Installation
```sh
npm install react-native-cordova --save
```
### Installation (iOS)
* Drag RCTCordova.xcodeproj to your project on Xcode.
* Click on your main project file (the one that represents the .xcodeproj) select Build Phases and drag libRCTCordova.a from the Products folder inside the RCTCordova.xcodeproj.
* Look for Header Search Paths and make sure it contains $(SRCROOT)/../../../react-native/React as recursive.

* In your project, Look for Header Search Paths and make sure it contains $(SRCROOT)/../../react-native-cordova/ios/RCTCordova.
* then you can #import "CDVCommandDelegateImpl.h"

### Installation (Android)
* In Main project `build.gradle`
```gradle
...
include ':react-native-cordova'
project(':react-native-cordova').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-cordova/android/RCTCordova')
```

* In you project `build.gradle`

```gradle
...
dependencies {
    ...
    compile project(':react-native-cordova')
}
```

* then you can import com.remobile.cordova.* ;


# Project List
* [react-native-camera](https://github.com/remobile/react-native-camera)
* [react-native-contacts](https://github.com/remobile/react-native-contacts)
* [react-native-dialogs](https://github.com/remobile/react-native-dialogs)
* [react-native-file-transfer](https://github.com/remobile/react-native-file-transfer)
* [react-native-image-picker](https://github.com/remobile/react-native-image-picker)
