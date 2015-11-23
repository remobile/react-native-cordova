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
#import "RCTBridgeModule.h"
#import "CDVCommandDelegateImpl.h"
#import "CDVInvokedUrlCommand.h"


#define RCT_EXPORT_CORDOVA_METHOD(name) \
RCT_EXPORT_METHOD(name:(NSArray *)args success:(RCTResponseSenderBlock)success error:(RCTResponseSenderBlock)error) { \
CDVInvokedUrlCommand *command = [[CDVInvokedUrlCommand alloc]initWithArguments:args success:success error:error]; \
[self name:command]; \
}

#define RCT_EXPORT_CORDOVA_METHOD1(name) \
RCT_EXPORT_METHOD(name:(NSArray *)args success:(RCTResponseSenderBlock)success) { \
CDVInvokedUrlCommand *command = [[CDVInvokedUrlCommand alloc]initWithArguments:args success:success error:nil]; \
[self name:command]; \
}

#define RCT_EXPORT_CORDOVA_METHOD2(name, _name) \
RCT_EXPORT_METHOD(name:(NSArray *)args) { \
CDVInvokedUrlCommand *command = [[CDVInvokedUrlCommand alloc]initWithArguments:args success:nil error:nil]; \
[self _name:command]; \
}



@interface CDVPlugin : NSObject <RCTBridgeModule> {
    CDVCommandDelegateImpl* _commandDelegate;
}
@property (nonatomic, readonly) CDVCommandDelegateImpl* commandDelegate;

+ (UIViewController *)presentViewController;
@end
