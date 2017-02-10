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
#import <React/RCTBridgeModule.h>
#import "CDVCommandDelegateImpl.h"
#import "CDVInvokedUrlCommand.h"


#define RCT_EXPORT_CORDOVA_METHOD(name) \
RCT_EXPORT_METHOD(name:(NSArray *)args success:(RCTResponseSenderBlock)success error:(RCTResponseSenderBlock)error) { \
CDVInvokedUrlCommand *command = [[CDVInvokedUrlCommand alloc]initWithArguments:args success:success error:error]; \
[self name:command]; \
}

#define CDV_PLUGIN_DEFINE { \
    CDVCommandDelegateImpl* _commandDelegate; \
} \
@property (nonatomic, readonly) CDVCommandDelegateImpl* commandDelegate; \
@property (nonatomic, readonly, strong) UIViewController *viewController;


@interface CDVPlugin : NSObject <RCTBridgeModule>
CDV_PLUGIN_DEFINE
@end

@interface CDVPluginEventEmitter : NSObject <RCTBridgeModule>
CDV_PLUGIN_DEFINE

@property (nonatomic, weak) RCTBridge *bridge;
/**
 * Send an event that does not relate to a specific view, e.g. a navigation
 * or data update notification.
 */
- (void)sendEventWithName:(NSString *)name body:(id)body;

/**
 * These methods will be called when the first observer is added and when the
 * last observer is removed (or when dealloc is called), respectively. These
 * should be overridden in your subclass in order to start/stop sending events.
 */
- (void)startObserving;
- (void)stopObserving;
@end

