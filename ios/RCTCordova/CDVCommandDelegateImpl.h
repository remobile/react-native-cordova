//
//  CDVCommandDelegateImpl.h
//  CDVCommandDelegateImpl
//
//  Created by fangyunjiang on 15/11/17.
//  Copyright (c) 2015年 remobile. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTBridge.h>
#import <React/RCTEventDispatcher.h>
#import <React/RCTBridgeModule.h>
#import "CDVPluginResult.h"
#import "CDVInvokedUrlCommand.h"

@interface CDVCommandDelegateImpl : NSObject

#define IsAtLeastiOSVersion(X) ([[[UIDevice currentDevice] systemVersion] compare:X options:NSNumericSearch] != NSOrderedAscending)

- (void)sendPluginResult:(CDVPluginResult*)result callbackId:(id)callbackId;
- (void)runInBackground:(void (^)())block;
- (void)runInUIThread:(void (^)())block;
@end
