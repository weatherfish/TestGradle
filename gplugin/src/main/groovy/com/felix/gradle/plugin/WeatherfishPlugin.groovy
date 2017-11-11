package com.felix.gradle.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import com.felix.gradle.plugin.task.WeatherfishTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.GroovyPlugin
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.WarPlugin

class WeatherfishPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

//        Instantiator instantiator = ((DefaultGradle) project.gradle)
//                .services.get(Instantiator.class)
//
//        Object[] obj = { instantiator }
//        project.extensions.create("config", WeatherfishExtension.class, obj)

        project.extensions.create('config', WeatherfishExtension)
        project.config.extensions.create("inConfig", WeatherfishInnerExtension)
        project.config.extensions.create("inConfig2", WeatherfishExtension2)

        project.gradle.addListener(new TimeListener())

        if (project.plugins.hasPlugin(AppPlugin)) {
            def android = project.extensions.getByType(AppExtension)
            android.applicationVariants.all { variant ->
                def appID = variant.generateBuildConfig.appPackageName
                println "appID is " + appID
                def variantData = variant.variantData
                println "variantData is " + variantData
                def scope = variantData.scope
                println "scope is " + scope
                variant.outputs.each { output ->
                    println "output name is " + output.name
                }
            }

            def manifestFile = android.sourceSets.main.manifest.srcFile
            def packageName = new XmlParser().parse(manifestFile).attribute('package')
            println "packageName->" + packageName

            project.task("weatherfishTask", type: WeatherfishTask)
        }
        if (project.plugins.hasPlugin(LibraryPlugin)) {

        }
        if (project.plugins.hasPlugin(WarPlugin)) {

        }
        if (project.plugins.hasPlugin(JavaPlugin)) {

        }
        if (project.plugins.hasPlugin(GroovyPlugin)) {

        }
    }
}

class WeatherfishExtension {
    def message = "this is message"
    def sender = " weatherfish"
}

class WeatherfishExtension2 {
    Closure myClosure = { v ->
        println "extension" + v
        return v
    }
}

class WeatherfishInnerExtension {
    def inMessage = "this is message inner"
    def inSender = " weatherfish inner"
}