package ru.clevertec;

public class FileDownloaderPluginExtension {
    private String pdfTemplate;
    private String target;

    {
        pdfTemplate = "https://github.com/stebadmitriy/files/raw/main/Clevertec_Template.pdf";
        target = "resources/templates.pdf";
    }

    public String getPdfTemplate() {
        return pdfTemplate;
    }

    public void setPdfTemplate(String pdfTemplate) {
        this.pdfTemplate = pdfTemplate;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
