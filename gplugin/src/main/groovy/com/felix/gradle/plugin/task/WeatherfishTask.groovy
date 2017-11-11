package com.felix.gradle.plugin.task

import com.felix.gradle.plugin.AppConstant
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class WeatherfishTask extends DefaultTask {

    @TaskAction
    void output() {
        def config = project.extensions.getByName(AppConstant.USER_CONFIG)
        def inConfig = project.config.extensions.getByName("inConfig")
        def inConfig2 = project.config.extensions.getByName("inConfig2")
        def myClosure = inConfig2.myClosure
        println "inConfig2->"
        myClosure('-------->>>>>>>>inConfig2 message toApp')

        println "WeatherfishTask    " + config.message + "\t" + config.sender
        println "WeatherfishTask Inner  " + inConfig.inMessage + "\t" + inConfig.inSender
    }
}