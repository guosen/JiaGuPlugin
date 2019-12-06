# JiaGuPlugin
加固App的groovy插件 目前采用360 命令行加固

# 使用方式
apply  plugin: 'guosen-jiagu'
classpath 'com.guosen.jiagu:JiaGu360:1.0.21'
# 配置相关信息
``java
JiaGuInfo{
    userName = "180******"//360账号
    userPwd  = "*******"//360密码
    keyStorePass = "****"
    keyStoreKeyAliasPwd = "****"
    keyStoreKeyAlias = "mh"
    keyStorePath = "./app/Keystore_MH.keystore"
    outputPath = "./jiaguApks"//加固后输出文件夹
    oldApkPath = "./app/build/outputs/apk/release"//需要加固的文件
}
``
