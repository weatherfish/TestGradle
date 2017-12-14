package com.felix.gradle.plugin


import com.felix.gradle.plugin.task.UploadVersionTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class VersionPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println "${AppConstant.TAG} welcome to sf version manager"

        project.extensions.create(AppConstant.USER_CONFIG, ConfigExtension)

        project.task("uploadVersion", type: UploadVersionTask)

    }
}