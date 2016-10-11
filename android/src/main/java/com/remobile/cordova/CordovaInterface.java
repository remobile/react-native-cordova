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

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Pair;

import org.json.JSONException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CordovaInterface {
    private ExecutorService threadPool;
    private CordovaPlugin plugin;
    protected CordovaPlugin activityResultCallback;
    protected CallbackMap permissionResultCallbacks;
    protected int activityResultRequestCode = -1;

    public CordovaInterface(CordovaPlugin plugin) {
        this.plugin = plugin;
        threadPool = Executors.newCachedThreadPool();
        this.permissionResultCallbacks = new CallbackMap();
    }

    public ExecutorService getThreadPool() {
        return threadPool;
    }

    public Activity getActivity() {
        return plugin.getActivity();
    }

    public void startActivityForResult(CordovaPlugin command, Intent intent, int requestCode) {
        setActivityResultCallback(command);
        try {
            getActivity().startActivityForResult(intent, requestCode + plugin.requestCodeStart);
        } catch (RuntimeException e) { // E.g.: ActivityNotFoundException
            activityResultCallback = null;
            throw e;
        }
    }

    public void setActivityResultCallback(CordovaPlugin plugin) {
        // Cancel any previously pending activity.
        if (activityResultCallback != null) {
            activityResultCallback.onActivityResult(activityResultRequestCode, Activity.RESULT_CANCELED, null);
        }
        activityResultCallback = plugin;
    }

    public void setActivityResultRequestCode(int requestCode) {
        activityResultRequestCode = requestCode;
    }


    public void onRequestPermissionResult(int requestCode, String[] permissions,
                                          int[] grantResults) throws JSONException {
        Pair<CordovaPlugin, Integer> callback = permissionResultCallbacks.getAndRemoveCallback(requestCode);
        if(callback != null) {
            callback.first.onRequestPermissionResult(callback.second, permissions, grantResults);
        }
    }

    public void requestPermission(CordovaPlugin plugin, int requestCode, String permission) {
        String[] permissions = new String [1];
        permissions[0] = permission;
        requestPermissions(plugin, requestCode, permissions);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissions(CordovaPlugin plugin, int requestCode, String [] permissions) {
        int mappedRequestCode = permissionResultCallbacks.registerCallback(plugin, requestCode);
        getActivity().requestPermissions(permissions, mappedRequestCode);
    }

    public boolean hasPermission(String permission)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            int result = getActivity().checkSelfPermission(permission);
            return PackageManager.PERMISSION_GRANTED == result;
        }
        else
        {
            return true;
        }
    }

}



