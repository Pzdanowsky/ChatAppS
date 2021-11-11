package Objects;

import java.io.Serializable;

public class FileObject implements Serializable {

    private int id;
    private byte [] contentFile;
    private String filename;

    public FileObject (String filename,byte[] contentFile){
        this.filename = filename;
        this.contentFile = contentFile;
    }

    public byte[] getContentFile() {
        return contentFile;
    }

    public void setContentFile(byte[] contentFile) {
        this.contentFile = contentFile;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}