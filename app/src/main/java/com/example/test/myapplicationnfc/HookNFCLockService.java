package com.example.test.myapplicationnfc;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by zhouxi on 29/8/2017.
 */

public class HookNFCLockService implements IXposedHookLoadPackage{
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        //只hook测试app
        if (loadPackageParam.packageName.equals("com.sonymobile.nfclock")) {


//            Class clasz = loadPackageParam.classLoader.loadClass("com.sonymobile.nfclock.NfcLockService");
//
//
//            XposedHelpers.findAndHookMethod(clasz, "judgeClfPassMatching", String.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    XposedBridge.log("judgeClfPassMatching:"+param.args[0]);
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//                    XposedBridge.log("judgeClfPassMatching:retrun:"+param.getResult());
//                    param.setResult(0);
//                    XposedBridge.log("judgeClfPassMatching:hook to 0");
//                }
//            });


//            XposedHelpers.findAndHookMethod(clasz, "isUrgentNfcLockStatus", new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    param.setResult(false);
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//                    param.setResult(false);
//                    XposedBridge.log("isUrgentNfcLockStatus?:"+param.getResult());
//
//
//
//                }
//            });

//
//            XposedHelpers.findAndHookMethod(clasz, "checkPassphrase", Object.class, String.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    XposedBridge.log("checkPassphrase:"+param.args[0]+","+param.args[1]);
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//                    param.setResult(true);
//                }
//            });




//            XposedHelpers.findAndHookMethod(clasz, "setNfcUnlock", String.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    String p1 = (String) param.args[0];
//
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//
//
//                    String p1 = (String) param.args[0];
////                    param.setResult(0);
//                    XposedBridge.log("setNfcUnlock,ori:"+p1+",result:"+param.getResult());
//                }
//            });


            Class statusClass = loadPackageParam.classLoader.loadClass("com.sonymobile.nfclock.NfcLockState");
            XposedHelpers.findAndHookMethod(statusClass, "isTaDataMatch", String.class, int.class, int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    param.setResult(true);
                }
            });
//




//            XposedHelpers.findAndHookMethod(statusClass, "isClfLock", new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    XposedBridge.log("打log::isClfLock");
//
////                    Class c = param.thisObject.getClass();
////                    Field[] fs = c.getDeclaredFields();
////
////                    for(int i = 0 ; i < fs.length ; i ++){
////                        Field f = fs[i];
////                        f.setAccessible(true);
////                        Object val = f.get(param.thisObject);
////
////                        if(f.getName().equals("mClfLock")){
////                            f.set(param.thisObject,false);//黑掉他
////                        }
////
////
//////                        XposedBridge.log("val:"+f.getName() +","+val);
////                    }
////                    Log.e("changed","changed before"+param.thisObject);
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
////                    param.setResult(false);
////                    Log.e("changed","changed"+param.thisObject);
////                    XposedBridge.log("changed"+param.thisObject);
//                }
//            });
//
        }
    }
}
