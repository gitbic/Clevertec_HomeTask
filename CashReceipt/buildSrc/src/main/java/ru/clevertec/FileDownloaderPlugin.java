package ru.clevertec;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class FileDownloaderPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        FileDownloaderPluginExtension extension =
                project.getExtensions().create("source", FileDownloaderPluginExtension.class);

        project.task("downloadPdf").doLast(task -> {

            try {
                URL url = new URL(extension.getPdfTemplate());
                ReadableByteChannel rbc = Channels.newChannel(url.openStream());
                FileOutputStream fos = new FileOutputStream(extension.getTarget());
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                rbc.close();
                fos.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }
}
