//
//  CDVCommandDelegateImpl.h
//  CRTFileTransfer
//
//  Created by fangyunjiang on 15/11/17.
//  Copyright (c) 2015年 remobile. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "RCTBridgeModule.h"
#import "CDVPluginResult.h"

@interface CDVCommandDelegateImpl : NSObject

#define IsAtLeastiOSVersion(X) ([[[UIDevice currentDevice] systemVersion] compare:X options:NSNumericSearch] != NSOrderedAscending)

- (id)initWithCallback:(RCTResponseSenderBlock)successFunc error:(RCTResponseSenderBlock)errorFunc;
- (void)sendPluginResult:(CDVPluginResult*)result;
- (void)runInBackground:(void (^)())block;
+ (UIViewController *)getTopPresentedViewController;

@end
