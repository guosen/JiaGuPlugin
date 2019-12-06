package com.guosen.jiagu

import com.android.build.gradle.api.ApplicationVariant
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class JiaGuAppTask extends DefaultTask {


    @Input
    ApplicationVariant variant;

    @Input
    Project targetProject
    JiaGuAppTask(){
        group = "guosen"
       // dependsOn 'app:build'
    }
    @TaskAction
    doAction(){
        //打包完成
        print('加固开始-------------------------')
        //获取参数
        //Dir 目录依赖于apply plugin: 'guosen-jiagu' 这句话应用在哪里，一般是app build
        def qihuPath ="${project.getRootDir()}/JiaGu360/jiagu/jiagu.jar"
        def keyStorePath = project.extensions.JiaGuInfo.keyStorePath
        def keyStorePass = project.extensions.JiaGuInfo.keyStorePass
        def keyStoreKeyAlias = project.extensions.JiaGuInfo.keyStoreKeyAlias
        def keyStoreKeyAliasPass = project.extensions.JiaGuInfo.keyStoreKeyAliasPwd
        def apkOutputDir = project.extensions.JiaGuInfo.outputPath





        def userName = project.extensions.JiaGuInfo.userName
        def userPwd = project.extensions.JiaGuInfo.userPwd
//        def oldApkPath = project.fileTree("${apkOldDir}").find {
//            return it.name.endsWith(".apk")
//        }
        variant.outputs.each {
            outputs->
                def outFile = outputs.outputFile
                if (outFile != null && outFile.name.endsWith('.apk')){
                    //360加固-登录
                    execCmd("java -jar ${qihuPath} -login ${userName} ${userPwd}")
                    //360加固-签名信息配置
                    execCmd("java -jar ${qihuPath}  -importsign ${keyStorePath} ${keyStorePass} ${keyStoreKeyAlias} ${keyStoreKeyAliasPass}")
                    //360加固-渠道信息配置
                    // execCmd("java -jar ${qihuPath} -importmulpkg ${project.extensions.publishAppInfo.channelPath}")
                    //360加固-开始加固
                    execCmd("java -jar ${qihuPath} -jiagu ${outFile} ${apkOutputDir} -autosign  -automulpkg")
                    print("加固完成-------------------------${outFile}")
                }
        }
    }

    void execCmd(cmd){
        project.exec {
            executable 'bash'
            args '-c', cmd
        }
    }
}