package ru.clevertec
import org.gradle.api.Plugin
import org.gradle.api.Project

class FileDownloaderP implements Plugin<Project> {
    void apply(Project project) {

        def extension = project.extensions.create('source', FileDownloaderPE)

        project.task('downloadPdf').doLast {
            try {
                ant.get(src: extension.pdfTemplate, dest: new File(extension.target))
            } catch (Exception e) {
                println e
            }
        }
    }
}
