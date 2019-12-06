package com.guosen.jiagu

import com.android.build.gradle.AppExtension

import org.codehaus.groovy.runtime.DefaultGroovyMethods
import org.gradle.api.Plugin
import org.gradle.api.Project


class JiaGuAppPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        print("hello")
        project.extensions.create("JiaGuInfo",JiaGuAppInfoExtension.class)
        //project.tasks.create('JiaGuApp',JiaGuAppTask.class)

        project.android.applicationVariants.all{
            def variantName = it.name.capitalize()
            JiaGuAppTask task = project.tasks.create("assemble${variantName}Package", JiaGuAppTask.class)
            //task.targetProject = project
            task.variant = it
            task.doFirst {
                println '>>>默认包全部生成，开始打加固签名渠道包。。。'
            }

            //依赖assemble，需要先编译出所有的该variant的包
            task.dependsOn it.assemble

            it.outputs.all{
                println "appName>>>$it.outputFileName"
            }
        }

    }
}