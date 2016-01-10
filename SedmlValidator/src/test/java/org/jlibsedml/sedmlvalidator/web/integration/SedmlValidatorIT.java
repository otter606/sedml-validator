package org.jlibsedml.sedmlvalidator.web.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.jlibsedml.sedmlvalidator.web.SedmlValidatorApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Basic integration tests for demo application.
 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SedmlValidatorApp.class)
@WebAppConfiguration()
@DirtiesContext
public class SedmlValidatorIT {

	@Autowired
    private WebApplicationContext wac;
	
	private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
	@Test
	public void testHello() throws Exception {
		mockMvc.perform(get("/hello")).andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void postValidFile() throws Exception {
		File oksedml = new File("src/test/resources/sedMLBIOM21.xml");
		mockMvc.perform(fileUpload("/sedml/validate")
				.file(new MockMultipartFile("xfile",oksedml.getName(), 
						    "UTF-8", FileUtils.readFileToByteArray(oksedml))
				))
				.andExpect(status().is2xxSuccessful());
	}

}
