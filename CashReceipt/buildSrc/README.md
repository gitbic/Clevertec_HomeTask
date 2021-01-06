This plugin was written to download a pdf file by URL to the final source. 

However, this plugin can be used to download any file by URL.

The repository contains two versions of the plugin written in java and groovy

**Usage**

```gradle
plugins {
    id 'ru.clevertec.file-downloader'
}
```

**Example**
```gradle
source.pdfTemplate = 'https://source.com/file.pdf'
source.target = 'resources/file.pdf'
```

**Default values**
```gradle
source.pdfTemplate = 'https://github.com/stebadmitriy/files/raw/main/Clevertec_Template.pdf'
source.target = 'resources/templates.pdf'
```

You can run this plugin by calling the task:
 * gradle downloadPdf
 
Also, you can make the "gradle build" command dependent on the "downloadpdf" task:
* build.dependsOn downloadPdf
 