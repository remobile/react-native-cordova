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

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class CordovaPlugin extends ReactContextBaseJavaModule {
    public CordovaSimulation cordova;

    public CordovaPlugin(ReactApplicationContext reactContext) {
        super(reactContext);
        cordova = new CordovaSimulation();
    }
    public class CordovaSimulation {
        private ExecutorService threadPool;
        private Activity activity;

        public CordovaSimulation() {
            threadPool = Executors.newCachedThreadPool();
        }

        public ExecutorService getThreadPool() {
            return threadPool;
        }

        public Activity getActivity() {
            return activity;
        }

        public void setActivity(Activity activity) {
            this.activity = activity;
        }
    }
}



