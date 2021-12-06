package co.edu.uniquindio.software.safepet.gui.beans.util;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class FileDownloadView {
    private StreamedContent file;

    public FileDownloadView() {
        file = DefaultStreamedContent.builder()
                .name("contrato.pdf")
                .contentType("application/pdf")
                .stream(() -> FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/files/contrato.pdf"))
                .build();
    }

    public StreamedContent getFile() {
        return file;
    }
}
