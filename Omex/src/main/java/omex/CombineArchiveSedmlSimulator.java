package omex;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jlibsedml.Libsedml;
import org.jlibsedml.Model;
import org.jlibsedml.Output;
import org.jlibsedml.SEDMLDocument;
import org.jlibsedml.SedML;
import org.jlibsedml.Task;
import org.jlibsedml.XMLException;
import org.jlibsedml.execution.IModelResolver;
import org.jlibsedml.execution.IRawSedmlSimulationResults;
import org.jlibsedml.execution.ModelResolver;
import org.jlibsedml.modelsupport.BioModelsModelsRetriever;
import org.jlibsedml.modelsupport.URLResourceRetriever;
import org.simulator.math.odes.MultiTable;
import org.simulator.sedml.SedMLSBMLSimulatorExecutor;

import de.unirostock.sems.cbarchive.ArchiveEntry;
import de.unirostock.sems.cbarchive.CombineArchive;

/**
 * Given a CombineArchive, will run any simulation tasks using SimulationCore
 * library
 * 
 * @author richard
 *
 */
public class CombineArchiveSedmlSimulator {

	public void simulateArchive(CombineArchive archiveIn, URI sedmlFormat)
			throws IOException, XMLException {
		if (archiveIn.hasEntriesWithFormat(sedmlFormat)) {
			for (ArchiveEntry entry : archiveIn
					.getEntriesWithFormat(sedmlFormat)) {
				File sedmlFile = entry.extractFile(File.createTempFile(
						"sedmlFromOmex", ".xml"));
				SEDMLDocument sedmlIn = Libsedml.readDocument(sedmlFile);
				SedML sedml = sedmlIn.getSedMLModel();

				ModelResolver resolver = new ModelResolver(sedml);
				IModelResolver archiveResolver = new CombineEntryResolver(
						archiveIn);
				resolver.add(archiveResolver);
				// some models might be URLs or BioModels
				resolver.add(new URLResourceRetriever());
				resolver.add(new BioModelsModelsRetriever());
				// this just gets the models
				for (Model model : sedml.getModels()) {
					System.err.println(resolver.getModelString(model));
				}
				List<Output> outputs = new ArrayList<Output>(sedml.getOutputs());
				for (Output output : outputs) {
					System.err.println("Running output: " + output.getName());
					// or we can simulate them:
					SedMLSBMLSimulatorExecutor executor = new SedmlSBMLExecutorExt(
							sedml, output, archiveIn);
					Map results = executor.runSimulations();
					try {
						MultiTable table = executor.processSimulationResults(
								output, results);
						System.err.println("there are "
								+ table.getColumnCount() + "columns");
					} catch (Exception e) {
						System.err
								.println("Exception calculating simulation result - "
										+ e.getMessage());
					}

				}
			}
		}
	}

}
