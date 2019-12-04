package com.guosen.jiagu

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class JiaGuAppTask extends DefaultTask {
    JiaGuAppTask(){
        group = "guosen"
        dependsOn 'app:build'
    }
    @TaskAction
    doAction(){
        //打包完成
        print('加固开始-------------------------')
        //获取参数
        def qihuPath = "${project.getProjectDir()}/JiaGu360/jiagu/jiagu.jar"
        def keyStorePath = project.extensions.appInfo.keyStorePath
        def keyStorePass = project.extensions.appInfo.keyStorePass
        def keyStoreKeyAlias = project.extensions.appInfo.keyStoreKeyAlias
        def keyStoreKeyAliasPass = project.extensions.appInfo.keyStoreKeyAliasPwd
        def apkOutputDir = project.extensions.appInfo.outputPath
        def apkOldDir = project.extensions.appInfo.oldApkPath

        def oldApkPath = project.fileTree("${apkOldDir}").find {
            return it.name.endsWith(".apk")
        }
        def userName = project.extensions.appInfo.userName
        def userPwd = project.extensions.appInfo.userPwd
        print("jiagu:: "+project.getProjectDir())
        //360加固-登录
        execCmd("java -jar ${qihuPath} -login ${userName} ${userPwd}")
        print("userName:${userName}")
        //360加固-签名信息配置
        execCmd("java -jar ${qihuPath}  -importsign ${keyStorePath} ${keyStorePass} ${keyStoreKeyAlias} ${keyStoreKeyAliasPass}")
        //360加固-渠道信息配置
        // execCmd("java -jar ${qihuPath} -importmulpkg ${project.extensions.publishAppInfo.channelPath}")
        //360加固-开始加固
        execCmd("java -jar ${qihuPath} -jiagu ${oldApkPath} ${apkOutputDir} -autosign  -automulpkg")

        print("加固完成-------------------------${project.buildDir}")
    }

    void execCmd(cmd){
        project.exec {
            executable 'bash'
            args '-c', cmd
        }
    }
}