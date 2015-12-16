# React Native Cordova (remobile)
A cordova plugin defines for react native on ios and android

## Installation
```sh
npm install @remobile/react-native-cordova --save
```
### Installation (iOS)
* Drag RCTCordova.xcodeproj to your project on Xcode.
* Click on your main project file (the one that represents the .xcodeproj) select Build Phases and drag libRCTCordova.a from the Products folder inside the RCTCordova.xcodeproj.
* Look for Header Search Paths and make sure it contains $(SRCROOT)/../../../react-native/React as recursive.

* In your project, Look for Header Search Paths and make sure it contains $(SRCROOT)/../../react-native-cordova/ios/RCTCordova.
* then you can #import "CDVPlugin.h"

### Installation (Android)
* In Main project `build.gradle`
```gradle
...
include ':react-native-cordova'
project(':react-native-cordova').projectDir = new File(rootProject.projectDir, '../node_modules/@remobile/react-native-cordova/android/RCTCordova')
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


## Usage
### IOS
```java
#import "CDVPlugin.h"
...
@interface CustomClass : CDVPlugin
@end
...

@implementation CustomClass
RCT_EXPORT_MODULE(RCTCustomClass)
RCT_EXPORT_CORDOVA_METHOD(test);
...
- (void) test:(CDVInvokedUrlCommand *)command {
...
}
....
@end
```
### Android
```java
import com.remobile.cordova.*;
...
public class CustomClass extends CordovaPlugin {
...
    public CustomClass(ReactApplicationContext reactContext, Activity activity) {
            super(reactContext);
            this.cordova.setActivity(activity);
        }
...
    @Override
    public String getName() {
        return "Sqlite";
    }
    @ReactMethod
    public void test(ReadableArray args, Callback success, Callback error) {
        String action = "test";
        try {
            this.execute(action, JsonConvert.reactToJSON(args), new CallbackContext(success, error));
        } catch (Exception ex) {
            FLog.e(LOG_TAG, "Unexpected error:" + ex.getMessage());
        }
    }
    ...
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if (action.equals("test")) {
            ....
            return true;
        }
        ....
        return false;
    }
}
```


# Project List
* [react-native-camera](https://github.com/remobile/react-native-camera)
* [react-native-contacts](https://github.com/remobile/react-native-contacts)
* [react-native-dialogs](https://github.com/remobile/react-native-dialogs)
* [react-native-file-transfer](https://github.com/remobile/react-native-file-transfer)
* [react-native-image-picker](https://github.com/remobile/react-native-image-picker)
* [react-native-sqlite](https://github.com/remobile/react-native-sqlite)
* [react-native-file](https://github.com/remobile/react-native-file)
* [react-native-zip](https://github.com/remobile/react-native-zip)
