package omex;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.apache.commons.io.FileUtils;
import org.jlibsedml.execution.IModelResolver;

import de.unirostock.sems.cbarchive.ArchiveEntry;
import de.unirostock.sems.cbarchive.CombineArchive;

public class CombineEntryResolver implements IModelResolver {

	private CombineArchive archive;
	public CombineEntryResolver(CombineArchive ca) {
		this.archive = ca;
	}	
	
	@Override
	public String getModelXMLFor(URI modelURI) {
		ArchiveEntry entry =  archive.getEntry(modelURI.toString());
		File tempFile;
		try {
			tempFile = File.createTempFile("tempModel", "xml");
			entry.extractFile(tempFile);
			return FileUtils.readFileToString(tempFile);
		} catch (IOException e) {
			return null;
		}
	}
}
