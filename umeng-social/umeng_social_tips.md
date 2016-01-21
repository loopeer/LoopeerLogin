[返回](/README.md)

# 友盟分享以及第三方登录使用说明 #
## 一、AndroidManifest文件修改 ##
1. 修改 UMENG_APPKEY
2. 修改腾讯 scheme(格式:tencent**)
## 二、UmengSocialTool文件修改 ##
1. 替换setupKey()方法内各平台key
## 三、使用方法 ##
1. 分享：构建ShareContent对象，调取UmengSocialTool.openShare()方法，参考sample实现
2. 第三方登录：调取UmengSocialTool.doLogin()方法，参考sample实现

## 参考 ##
1. 官方文档：
[http://dev.umeng.com/social/android/android-update](http://dev.umeng.com/social/android/android-update "参考官方文档")