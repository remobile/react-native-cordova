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
#import <React/RCTAssert.h>
#import <React/RCTUtils.h>
#import <React/RCTLog.h>
#import "CDVPlugin.h"

#define CDV_PLUGIN_IMPL \
@synthesize commandDelegate = _commandDelegate; \
\
+ (NSString *)moduleName { \
    return @""; \
} \
\
- (id)init { \
    self = [super init]; \
    if (self) { \
        _commandDelegate = [[CDVCommandDelegateImpl alloc]init]; \
    } \
    return self; \
} \
\
-(UIViewController*)viewController \
{ \
    UIViewController *presentingViewController = [[[[UIApplication sharedApplication] delegate] window] rootViewController]; \
    while(presentingViewController.presentedViewController != nil) { \
        presentingViewController = presentingViewController.presentedViewController; \
    } \
    return presentingViewController; \
}

@implementation CDVPlugin
CDV_PLUGIN_IMPL
@end

@implementation CDVPluginEventEmitter
{
    NSInteger _listenerCount;
}

CDV_PLUGIN_IMPL

- (void)sendEventWithName:(NSString *)eventName body:(id)body
{
    RCTAssert(_bridge != nil, @"bridge is not set. This is probably because you've "
              "explicitly synthesized the bridge in %@, even though it's inherited "
              "from CDVPluginEventEmitter.", [self class]);
    
    if (_listenerCount > 0) {
        [_bridge enqueueJSCall:@"RCTDeviceEventEmitter"
                        method:@"emit"
                          args:body ? @[eventName, body] : @[eventName]
                    completion:NULL];
    } else {
        RCTLogWarn(@"Sending `%@` with no listeners registered.", eventName);
    }
}

- (void)startObserving
{
    // Does nothing
}

- (void)stopObserving
{
    // Does nothing
}

- (void)dealloc
{
    if (_listenerCount > 0) {
        [self stopObserving];
    }
}

RCT_EXPORT_METHOD(addListener:(NSString *)eventName)
{
    if (_listenerCount == 0) {
        [self startObserving];
    }
    _listenerCount++;
}

RCT_EXPORT_METHOD(removeListeners:(NSInteger)count)
{
    if (RCT_DEBUG && count > _listenerCount) {
        RCTLogError(@"Attempted to remove more %@ listeners than added", [self class]);
    }
    if (count == _listenerCount) {
        [self stopObserving];
    }
    _listenerCount -= count;
}

@end
