package com.felix.gradle.plugin.task

import com.felix.gradle.plugin.AppConstant
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import java.io.*

class UploadVersionTask extends DefaultTask {

    @TaskAction
    void addVersion() {
        def config = project.extensions.getByName(AppConstant.USER_CONFIG)
        String versionPath = config.versionPropertiesPath
        def versionFile = new File(versionPath)
        def writeContent = ""
        boolean has = false
        boolean added = false
        versionFile.eachLine("UTF-8") {
            def content = it.trim()
            if (content.contains("=")) {
                def lines = content.split('=')
                if (lines[0].trim() == project.name) {
                    has = true
                    if (lines[1] == null || lines[1].trim().empty || !lines[1].contains(".")) {
                        lines[1] = "1.0.0"  //给一个默认值
                    }
                    def versions = lines[1].trim().split('\\.')
                    def length = versions.length
                    def tmpVersion = ""
                    for (int i = length - 1; i >= 0; i = i - 1) {
                        int tmp = versions[i].toInteger()
                        if (!added && tmp < 99) {
                            tmp = tmp + 1
                            added = true
                        } else if (tmp == 99) {
                            tmp = 0
                        }
                        tmpVersion = tmp + "." + tmpVersion
                    }
                    added = false
                    content = lines[0] + "=" + tmpVersion.substring(0, (tmpVersion.length() - 1))
                    writeContent = writeContent + content + "\n"
                } else {
                    writeContent = writeContent + content + "\n"
                }
            }
        }
        if (!has) {
            writeContent = writeContent + project.name + "=" + "1.0.0" + "\n"
        }
        versionFile.deleteOnExit()
        def printWriter = versionFile.newPrintWriter()
        printWriter.write(writeContent)
        printWriter.flush()
        printWriter.close()
        has = false
        writeContent = ""
    }

}