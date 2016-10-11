/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
*/
package com.remobile.cordova;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import org.json.JSONArray;
import org.json.JSONException;


public abstract class CordovaPlugin extends ReactContextBaseJavaModule implements ActivityEventListener, LifecycleEventListener {
    public static final int RESULT_STEP = 1000000;
    public static int CURRENT_RESULT = 0;

    public CordovaInterface cordova;
    public int requestCodeStart = 0;
    public CordovaWebView webView;

    public CordovaPlugin(ReactApplicationContext reactContext) {
        super(reactContext);
        cordova = new CordovaInterface(this);
        webView = new CordovaWebView(reactContext);
    }

    public CordovaPlugin(ReactApplicationContext reactContext, boolean needActivityResult) {
        this(reactContext);
        CURRENT_RESULT += RESULT_STEP;
        requestCodeStart = CURRENT_RESULT;
    }

    @Override
    public void initialize() {
        super.initialize();
        getReactApplicationContext().addActivityEventListener(this);
        getReactApplicationContext().addLifecycleEventListener(this);
    }

    public Activity getActivity() {
        return this.getCurrentActivity();
    }

    public void sendJSEvent(String eventName, @Nullable WritableMap params) {
        getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
    }

    public void onRequestPermissionResult(int requestCode, String[] permissions,
                                          int[] grantResults) throws JSONException {
    }

    public void executeReactMethod(String action, ReadableArray args, Callback success, Callback error) {
        try {
            this.execute(action, JsonConvert.reactToJSON(args), new CallbackContext(success, error));
        } catch (Exception ex) {
            FLog.e(getName(), "Unexpected error:" + ex.getMessage());
        }
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {}

    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (requestCode >= requestCodeStart && requestCode < requestCodeStart+RESULT_STEP) {
            onActivityResult(requestCode-requestCodeStart, resultCode, data);
        }
    }

    public void onNewIntent(Intent intent) {
    }

    public void onResume() {
    }

    public void onHostResume() {
        onResume();
    }

    public void onPause() {
    }

    public void onHostPause() {
        onPause();
    }

    public void onDestroy() {
    }

    public void onHostDestroy() {
        onDestroy();
    }
}



