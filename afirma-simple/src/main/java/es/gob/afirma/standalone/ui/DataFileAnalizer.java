package es.gob.afirma.standalone.ui;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import es.gob.afirma.core.misc.MimeHelper;

/**
 * Clase para obtener informaci&oacute;n sobre un documento.
 */
public class DataFileAnalizer {

    private static final Logger LOGGER = Logger.getLogger("es.gob.afirma"); //$NON-NLS-1$

    private static final Set<String> EXECUTABLE_EXTENSIONS;
    static {
    	EXECUTABLE_EXTENSIONS = new HashSet<>();
    	EXECUTABLE_EXTENSIONS.add("ACTION"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("APK"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("APP"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("BAT"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("BIN"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("CMD"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("COM"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("COMMAND"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("CPL"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("CSH"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("EXE"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("GADGET"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("INF1"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("INS"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("INX"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("IPA"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("ISU"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("JOB"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("JSE"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("KSH"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("LNK"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("MSC"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("MSI"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("MSP"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("MST"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("OSX"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("OUT"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("PAF"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("PIF"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("PRG"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("PS1"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("REG"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("RGS"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("RUN"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("SCR"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("SCT"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("SHB"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("SHS"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("U3P"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("VB"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("VBE"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("VBS"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("VBSCRIPT"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("WORKFLOW"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("WS"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("WSF"); //$NON-NLS-1$
    	EXECUTABLE_EXTENSIONS.add("WSH"); //$NON-NLS-1$
    }

	private final byte[] data;

	/**
	 * Construye el analizador para la obtenci&oacute;n de datos de un fichero.
	 * @param data
	 */
	public DataFileAnalizer(byte[] data) {
		this.data = data;
	}

	DataFileInfo analize() {

		final MimeHelper mimeHelper = new MimeHelper(this.data);

		final DataFileInfo info = new DataFileInfo();
		info.setExtension(mimeHelper.getExtension());
		info.setDescription(mimeHelper.getDescription());
		info.setSize(this.data.length);
		try {
			info.setIcon(FileIconProvider.getIcon(info.getExtension()));
		} catch (final IOException e) {
			LOGGER.warning("No se ha podido cargar el icono de la extension " + info.getExtension() + ": " + e); //$NON-NLS-1$ //$NON-NLS-2$
		}
		info.setData(this.data);

		return info;
	}

    /**
     * Identifica si la extensi&oacute;n de fichero indicada se corresponde con la de un ejecutable
     * nativo.
     * @param ext Extensi&oacute;n de fichero.
     * @return {@code true} si la extensi&oacute;n es la de un ejecutable, {@code false} en caso
     * contrario.
     */
    static boolean isExecutable(String ext) {
    	return ext != null && EXECUTABLE_EXTENSIONS.contains(ext.toUpperCase());
    }

}
