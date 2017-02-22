//
//  CDVCommandDelegateImpl.m
//  CDVCommandDelegateImpl
//
//  Created by fangyunjiang on 15/11/17.
//  Copyright (c) 2015年 remobile. All rights reserved.
//

#import <React/RCTUtils.h>
#import "CDVCommandDelegateImpl.h"

@implementation CDVCommandDelegateImpl
- (void)sendPluginResult:(CDVPluginResult*)result callbackId:(id)callbackId {
    CDVInvokedUrlCommand* command= (CDVInvokedUrlCommand*)callbackId;
    RCTResponseSenderBlock callback = [result.status intValue]==CDVCommandStatus_OK ? command.success : command.error;
    if (callback != nil) {
        callback(@[result.message?:@""]);
    }
}

- (void)runInBackground:(void (^)())block {
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), block);
}

- (void)runInUIThread:(void (^)())block {
    RCTExecuteOnMainQueue(block);
}

@end
