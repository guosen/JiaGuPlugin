package com.guosen.jiagu

import org.gradle.api.Plugin
import org.gradle.api.Project

class JiaGuAppPlugin implements Plugin<Project>{
    @Override
    void apply(Project project) {
        print("hello")
        project.extensions.create("appInfo",JiaGuAppInfoExtension.class)
        project.tasks.create('publishApp',JiaGuAppTask.class)
    }
}