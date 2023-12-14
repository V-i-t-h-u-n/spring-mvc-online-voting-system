package voting.app.utilities;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import org.springframework.web.multipart.MultipartFile;

public class MultipartFileToBlobPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        // Implementing this method is necessary for two-way data binding but not used for file upload
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof MultipartFile) {
            MultipartFile file = (MultipartFile) value;
            try {
                Blob blob = new javax.sql.rowset.serial.SerialBlob(file.getBytes());
                super.setValue(blob);
            } catch (IOException | SQLException e) {
                // Handle exceptions
                e.printStackTrace();
            }
        } else {
            super.setValue(value);
        }
    }
}
