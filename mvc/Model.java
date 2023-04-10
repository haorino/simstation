package mvc;

public class Model extends Bean {
    boolean unsavedChanges = false;
    String fileName = null;

    public Model() {}

    public void changed() {
        unsavedChanges = true;
        firePropertyChange("model", 0,1);
    }

    public String getFileName(){
        return fileName;
    }

    public void setFileName(String fName){
        fileName = fName;
    }

    public boolean getUnsavedChanges(){
        return unsavedChanges;
    }

    public void setUnsavedChanges(boolean i){
        unsavedChanges = i;
    }
}