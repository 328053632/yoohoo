package com.yoohoo.en.bean.talk;




public class UploadFileResult {

    private int result;
    private String swfpath;
    private String pdfpath;
    private int pagenum;
    private long fileid;
    private String downloadpath;
    private int size;
    private int status;
    private String filename;
    private int dynamicppt;
    private int fileprop;

    public void setResult(int result) {
        this.result = result;
    }
    public int getResult() {
        return result;
    }

    public void setSwfpath(String swfpath) {
        this.swfpath = swfpath;
    }
    public String getSwfpath() {
        return swfpath;
    }

    public void setPdfpath(String pdfpath) {
        this.pdfpath = pdfpath;
    }
    public String getPdfpath() {
        return pdfpath;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }
    public int getPagenum() {
        return pagenum;
    }

    public void setFileid(long fileid) {
        this.fileid = fileid;
    }
    public long getFileid() {
        return fileid;
    }

    public void setDownloadpath(String downloadpath) {
        this.downloadpath = downloadpath;
    }
    public String getDownloadpath() {
        return downloadpath;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getFilename() {
        return filename;
    }

    public void setDynamicppt(int dynamicppt) {
        this.dynamicppt = dynamicppt;
    }
    public int getDynamicppt() {
        return dynamicppt;
    }

    public void setFileprop(int fileprop) {
        this.fileprop = fileprop;
    }
    public int getFileprop() {
        return fileprop;
    }

    @Override
    public String toString() {
        return "UploadFileResult{" +
                "result=" + result +
                ", swfpath='" + swfpath + '\'' +
                ", pdfpath='" + pdfpath + '\'' +
                ", pagenum=" + pagenum +
                ", fileid=" + fileid +
                ", downloadpath='" + downloadpath + '\'' +
                ", size=" + size +
                ", status=" + status +
                ", filename='" + filename + '\'' +
                ", dynamicppt=" + dynamicppt +
                ", fileprop=" + fileprop +
                '}';
    }
}