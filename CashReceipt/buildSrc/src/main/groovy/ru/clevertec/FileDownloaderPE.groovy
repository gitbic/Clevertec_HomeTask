package ru.clevertec

class FileDownloaderPE {
    private String pdfTemplate = "https://github.com/stebadmitriy/files/raw/main/Clevertec_Template.pdf"
    private String target = "resources/templates.pdf"

    String getPdfTemplate() {
        return pdfTemplate
    }

    void setPdfTemplate(String pdfTemplate) {
        this.pdfTemplate = pdfTemplate
    }

    String getTarget() {
        return target
    }

    void setTarget(String target) {
        this.target = target
    }
}