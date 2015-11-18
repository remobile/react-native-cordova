//
//  CDVCommandDelegateImpl.m
//  CRTFileTransfer
//
//  Created by fangyunjiang on 15/11/17.
//  Copyright (c) 2015年 remobile. All rights reserved.
//

#import "CDVCommandDelegateImpl.h"

@interface CDVCommandDelegateImpl() {
    RCTResponseSenderBlock success;
    RCTResponseSenderBlock error;
}
@end

@implementation CDVCommandDelegateImpl


- (id)initWithCallback:(RCTResponseSenderBlock)successFunc error:(RCTResponseSenderBlock)errorFunc {
    self = [super init];
    if (self) {
        success = successFunc;
    		error = errorFunc;
    }
    return self;
}

- (void)sendPluginResult:(CDVPluginResult*)result {
    RCTResponseSenderBlock callback = result.status==CDVCommandStatus_OK ? success : error;
    if (callback != nil) {
        callback(@[result.message]);
    }
}

- (void)runInBackground:(void (^)())block {
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), block);
}

+ (UIViewController *)getTopPresentedViewController {
    UIViewController *presentingViewController = [[[[UIApplication sharedApplication] delegate] window] rootViewController];
    while(presentingViewController.presentedViewController != nil) {
        presentingViewController = presentingViewController.presentedViewController;
    }
    return presentingViewController;
}

@end
