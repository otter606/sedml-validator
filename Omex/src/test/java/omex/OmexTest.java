package omex;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

import javax.xml.transform.TransformerException;

import org.jdom2.JDOMException;
import org.jlibsedml.Libsedml;
import org.jlibsedml.Model;
import org.jlibsedml.SEDMLDocument;
import org.jlibsedml.XMLException;
import org.junit.Test;

import de.unirostock.sems.cbarchive.ArchiveEntry;
import de.unirostock.sems.cbarchive.CombineArchive;
import de.unirostock.sems.cbarchive.CombineArchiveException;
import de.unirostock.sems.cbarchive.meta.OmexMetaDataObject;
import de.unirostock.sems.cbarchive.meta.omex.OmexDescription;
import de.unirostock.sems.cbext.Formatizer;

public class OmexTest {
	
	static final File SEDML_FILE = new File ("src/test/resources/sedMLBIOM12.sed-ml");
	static final File REPRESSILATOR = new File ("src/test/resources/Elowitz2000_Repressilator.sbml");

	@Test
	public void createSEDMLArchive () throws XMLException, IOException, URISyntaxException, JDOMException, ParseException, CombineArchiveException, TransformerException{
	
		// First we read in the SED-ML file (or we could have generated the SED-ML
		// from scratch)
		// This SED-ML references a model file via a URN.
		// In this example we want to include the model in the archive.
		SEDMLDocument doc = Libsedml.readDocument(SEDML_FILE);
		
		// we set the source to the relative path of the model in the archive and save it
		// Here, only the first model refers to an actual source; other model definitions
		// are derived from this first one.
		doc.getSedMLModel().getModels().get(0).setSource(getModelPathInArchive());
		doc.writeDocument(SEDML_FILE);
		
		//create a temp file to store the archive
		File sedxOutfile = File.createTempFile("sedxExample",".sedx");
		System.out.println("sedx file is at : " + sedxOutfile.getAbsolutePath());
		
		// try using Formatizer .. this depends on java.nio.File
		// ability to find a mime-type if file ends with .xml
		CombineArchive ca = new CombineArchive (sedxOutfile);
		URI sedmlformat = Formatizer.guessFormat(SEDML_FILE);
		URI modelformat = Formatizer.guessFormat(REPRESSILATOR);
		
		// add model file
		ca.addEntry(
				   REPRESSILATOR,
				   getModelPathInArchive(),
				   sedmlformat);
		// add SED-ML file
		ArchiveEntry sedFile = ca.addEntry(
				   SEDML_FILE,
				   SEDML_FILE.getName(),
				   modelformat);
		// add a description
		OmexDescription desc = new OmexDescription("test description");
		OmexMetaDataObject dmo = new OmexMetaDataObject(desc);
		ca.addDescription(dmo);
		ca.setMainEntry(sedFile);
		ca.pack();
		ca.close();	

		CombineArchive archiveIn = new CombineArchive(sedxOutfile);
		// now let's read it in and check there's a SEDML file
		assertEquals(2, archiveIn.getNumEntries());
		assertEquals(1, archiveIn.getEntriesWithFormat(sedmlformat).size());
		// and an SBML file.
		assertEquals(1, archiveIn.getEntriesWithFormat(modelformat).size());
		
		// now let's discover the models
		if (ca.hasEntriesWithFormat(sedmlformat)){
			for (ArchiveEntry entry : archiveIn.getEntriesWithFormat(sedmlformat)) {
				File sedmlFile = entry.extractFile(File.createTempFile("sedmlFromOmex", ".xml"));
				SEDMLDocument sedmlIn = Libsedml.readDocument(sedmlFile);
				List<Model> models = sedmlIn.getSedMLModel().getModels();
				// now we need to look up the source
			}
		}
		archiveIn.close();
	}

	// puts model files in  a specific location
	private String getModelPathInArchive() {
		return "models/"+REPRESSILATOR.getName();
	}

}
