package com.felix.gradle.plugin


import com.felix.gradle.plugin.task.UploadVersionTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class VersionPlugin implements Plugin<Project> {

//    @Override
//    void apply(Project project) {
//
////        println "${AppConstant.TAG} welcome to sf version manager"
////
////        project.extensions.create(AppConstant.USER_CONFIG, ConfigExtension)
//
//        if (project.plugins.hasPlugin(AppPlugin)) {
//            //app打包
//            project.task("app", type: AppTask)
//        } else (project.plugins.hasPlugin(LibraryPlugin)) {
//            //maven打包
//            project.task("uploadVersion", type: UploadVersionTask)
//        }
//
//    }
    @Override
    void apply(Project project) {
        println "${AppConstant.TAG} welcome to sf version manager"

        project.extensions.create(AppConstant.USER_CONFIG, ConfigExtension)

        project.task("uploadVersion", type: UploadVersionTask)
    }
}