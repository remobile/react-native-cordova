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

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Base64;

public class PluginResult {
    public final int status;
    public final int messageType;
    public String strMessage;
    public JSONArray jsonArrayMessage;
    public JSONObject jsonObjectMessage;

    public PluginResult(Status status) {
        this(status, PluginResult.StatusMessages[status.ordinal()]);
    }

    public PluginResult(Status status, String message) {
        this.status = status.ordinal();
        this.messageType = message == null ? MESSAGE_TYPE_NULL : MESSAGE_TYPE_STRING;
        this.strMessage = message;
    }

    public PluginResult(Status status, JSONArray message) {
        this.status = status.ordinal();
        this.messageType = MESSAGE_TYPE_JSON_ARRAY;
        jsonArrayMessage = message;
    }

    public PluginResult(Status status, JSONObject message) {
        this.status = status.ordinal();
        this.messageType = MESSAGE_TYPE_JSON_OBJECT;
        jsonObjectMessage = message;
    }

    public PluginResult(Status status, int i) {
        this.status = status.ordinal();
        this.messageType = MESSAGE_TYPE_NUMBER;
        this.strMessage = "" + i;
    }

    public PluginResult(Status status, float f) {
        this.status = status.ordinal();
        this.messageType = MESSAGE_TYPE_NUMBER;
        this.strMessage = "" + f;
    }

    public PluginResult(Status status, boolean b) {
        this.status = status.ordinal();
        this.messageType = MESSAGE_TYPE_BOOLEAN;
        this.strMessage = Boolean.toString(b);
    }

    public PluginResult(Status status, byte[] data) {
        this(status, data, false);
    }

    public PluginResult(Status status, byte[] data, boolean binaryString) {
        this.status = status.ordinal();
        this.messageType = binaryString ? MESSAGE_TYPE_BINARYSTRING : MESSAGE_TYPE_ARRAYBUFFER;
        this.strMessage = Base64.encodeToString(data, Base64.NO_WRAP);
    }

    public int getStatus() {
        return status;
    }

    public static final int MESSAGE_TYPE_STRING = 0;
    public static final int MESSAGE_TYPE_JSON_ARRAY = 1;
    public static final int MESSAGE_TYPE_JSON_OBJECT = 2;
    public static final int MESSAGE_TYPE_NUMBER = 3;
    public static final int MESSAGE_TYPE_BOOLEAN = 4;
    public static final int MESSAGE_TYPE_NULL = 5;
    public static final int MESSAGE_TYPE_ARRAYBUFFER = 6;
    // Use BINARYSTRING when your string may contain null characters.
    // This is required to work around a bug in the platform :(.
    public static final int MESSAGE_TYPE_BINARYSTRING = 7;

    public static String[] StatusMessages = new String[]{
            "No result",
            "OK",
            "Class not found",
            "Illegal access",
            "Instantiation error",
            "Malformed url",
            "IO error",
            "Invalid action",
            "JSON error",
            "Error"
    };

    public enum Status {
        NO_RESULT,
        OK,
        CLASS_NOT_FOUND_EXCEPTION,
        ILLEGAL_ACCESS_EXCEPTION,
        INSTANTIATION_EXCEPTION,
        MALFORMED_URL_EXCEPTION,
        IO_EXCEPTION,
        INVALID_ACTION,
        JSON_EXCEPTION,
        ERROR
    }
}
