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
        versionFile.eachLine("UTF-8") {
            def content = it.trim()
            def lines = content.split('=')
            if (lines[0].trim() == project.name) {
                def versions = lines[1].trim().split('\\.')

                def versionLast = versions[2].toInteger()
                def versionMiddle = versions[1].toInteger()
                def versionFirst = versions[0].toInteger()

                if (versionLast < 99) {
                    versionLast = versionLast + 1
                } else if (versionLast < 99) {
                    versionMiddle = versionMiddle + 1
                } else if (versionLast < 99) {
                    versionFirst = versionFirst + 1
                }
                content = lines[0] + "=" + versionFirst + "." + versionMiddle + "." + versionLast
                writeContent = writeContent + content + "\n"
            } else {
                writeContent = writeContent + content + "\n"
            }
        }
        versionFile.deleteOnExit()
        def printWriter = versionFile.newPrintWriter()
        printWriter.write(writeContent)
        printWriter.flush()
        printWriter.close()
    }

}