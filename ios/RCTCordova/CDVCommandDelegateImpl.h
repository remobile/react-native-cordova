//
//  CDVCommandDelegateImpl.h
//  CDVCommandDelegateImpl
//
//  Created by fangyunjiang on 15/11/17.
//  Copyright (c) 2015年 remobile. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "RCTBridge.h"
#import "RCTEventDispatcher.h"
#import "RCTBridgeModule.h"
#import "CDVPluginResult.h"
#import "CDVInvokedUrlCommand.h"

@interface CDVCommandDelegateImpl : NSObject

#define IsAtLeastiOSVersion(X) ([[[UIDevice currentDevice] systemVersion] compare:X options:NSNumericSearch] != NSOrderedAscending)

- (void)sendPluginResult:(CDVPluginResult*)result callbackId:(CDVInvokedUrlCommand*)callbackId;
- (void)runInBackground:(void (^)())block;

@end
