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

#import "CDVInvokedUrlCommand.h"

@implementation CDVInvokedUrlCommand
@synthesize arguments = _arguments;
@synthesize callbackId = _callbackId;
@synthesize success = _success;
@synthesize error = _error;

- (id)initWithArguments:(NSArray*)arguments success:(RCTResponseSenderBlock)successFunc error:(RCTResponseSenderBlock)errorFunc {
    self = [super init];
    if (self != nil) {
        _arguments = arguments;
        _success = successFunc;
        _error = errorFunc;
        _callbackId = self;
    }
    return self;
}

- (id)initWithArguments: (NSArray*)arguments command:(CDVInvokedUrlCommand *)command {
    self = [super init];
    if (self != nil) {
        _arguments = arguments;
        _success = command.success;
        _error = command.error;
        _callbackId = self;
    }
    return self;
}

- (id)argumentAtIndex:(NSUInteger)index
{
    return [self argumentAtIndex:index withDefault:nil];
}

- (id)argumentAtIndex:(NSUInteger)index withDefault:(id)defaultValue
{
    return [self argumentAtIndex:index withDefault:defaultValue andClass:nil];
}

- (id)argumentAtIndex:(NSUInteger)index withDefault:(id)defaultValue andClass:(Class)aClass
{
    if (index >= [_arguments count]) {
        return defaultValue;
    }
    id ret = [_arguments objectAtIndex:index];
    if (ret == [NSNull null]) {
        ret = defaultValue;
    }
    if ((aClass != nil) && ![ret isKindOfClass:aClass]) {
        ret = defaultValue;
    }
    return ret;
}

@end
