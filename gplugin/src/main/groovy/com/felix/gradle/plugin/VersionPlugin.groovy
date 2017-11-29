package com.felix.gradle.plugin

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import com.felix.gradle.plugin.task.AppTask
import com.felix.gradle.plugin.task.UploadVersionTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class VersionPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println "${AppConstant.TAG} Welcome to version auto! "

        project.extensions.create(AppConstant.USER_CONFIG, ConfigExtension)

        if (project.plugins.hasPlugin(AppPlugin)) {
            //app打包
            project.task("app", type: AppTask)
        } else (project.plugins.hasPlugin(LibraryPlugin)) {
            //maven打包
            project.task("uploadVersion", type: UploadVersionTask)
        }

    }
}