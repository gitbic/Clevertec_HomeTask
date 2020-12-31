import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile

class FileDownloader extends DefaultTask{
    @Input
    String sourceUrl

    @OutputFile
    File target

    @TaskAction
    void download() {
        try{
            ant.get(src: sourceUrl, dest: target)
        } catch (Exception e) {
            println e
        }
    }
}