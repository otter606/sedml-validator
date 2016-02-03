package omex;

import org.jlibsedml.Output;
import org.jlibsedml.SedML;
import org.simulator.sedml.SedMLSBMLSimulatorExecutor;

import de.unirostock.sems.cbarchive.CombineArchive;
/**
 * This extends the simulation core algorithm's SedMLExecutor, so we can
 *  add a CombineArchiveEntry model resolver.
 * @author richard
 *
 */
public class SedmlSBMLExecutorExt extends SedMLSBMLSimulatorExecutor {

	public SedmlSBMLExecutorExt(SedML sedml, Output output) {
		super(sedml, output);
	}
	
	public SedmlSBMLExecutorExt(SedML sedml, Output output, CombineArchive archive) {
		this(sedml, output);
		addModelResolver(new CombineEntryResolver(archive));
	}


}
