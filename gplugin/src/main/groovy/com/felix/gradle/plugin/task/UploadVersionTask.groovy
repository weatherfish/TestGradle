package com.felix.gradle.plugin.task

import com.felix.gradle.plugin.AppConstant
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class UploadVersionTask extends DefaultTask {
    @TaskAction
    void addVersion() {
        def config = project.extensions.getByName(AppConstant.USER_CONFIG)
        def versionPath = config.versionPropertiesPath
        def versionFile = File(versionPath)
        versionFile.eachLine("UTF-8") {
            println it
        }
    }
}