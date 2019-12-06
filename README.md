# JiaGuPlugin
加固App的groovy插件 目前采用360 命令行加固

# 使用方式
apply  plugin: 'guosen-jiagu'
classpath 'com.guosen.jiagu:JiaGu360:1.0.21'
# 配置相关信息
```groovy
JiaGuInfo{
    userName = "180******"//360账号
    userPwd  = "*******"//360密码
    keyStorePass = "****"
    keyStoreKeyAliasPwd = "****"
    keyStoreKeyAlias = "mh"
    keyStorePath = "./Keystore_MH.keystore"
    outputPath = "./jiaguApks"//加固后输出文件夹
    oldApkPath = "./build/outputs/apk/release"//需要加固的文件
}
```
# 打加固包
在AndroidStudio 右侧gradle 找到guosen/下面的assembleXXXPackage Task
运行Task 将会在 之前配置的目录下面生成对应的加固保