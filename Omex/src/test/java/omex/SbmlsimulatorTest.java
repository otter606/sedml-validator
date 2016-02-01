package omex;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.math.ode.DerivativeException;
import org.junit.Test;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.validator.ModelOverdeterminedException;
import org.simulator.math.odes.AbstractDESSolver;
import org.simulator.math.odes.MultiTable;
import org.simulator.math.odes.RosenbrockSolver;
import org.simulator.sbml.SBMLinterpreter;

public class SbmlsimulatorTest {
    File sbml = new File ("src/test/resources/Elowitz2000_Repressilator.sbml");
	@Test
	public void basicHappyTestCase() throws XMLStreamException, IOException, DerivativeException, SBMLException, ModelOverdeterminedException {
		Model model = new SBMLReader().readSBML(sbml).getModel();
		SBMLinterpreter interpreter = new SBMLinterpreter(model); 
		AbstractDESSolver solver = new RosenbrockSolver();
		double[] timePoints = {0.0, 0.1, 0.2, 0.3, 0.4, 0.5};
		MultiTable solution = solver.solve(interpreter, interpreter.getInitialValues(), timePoints); 
		assertEquals("Unexpected number of columns", 36, solution.getColumnCount());
	}

}
