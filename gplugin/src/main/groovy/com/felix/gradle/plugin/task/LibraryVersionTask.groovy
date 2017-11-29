package com.felix.gradle.plugin.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class LibraryVersionTask extends DefaultTask {

    @TaskAction
    void versionAdd() {
        int version = project.version
        version = version + 1
        ant.propertyfile(file: versionFile){
            entry(key:'')
        }
    }
}