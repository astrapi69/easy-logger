/**
 * The MIT License
 *
 * Copyright (C) 2022 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapisixtynine.easy.logger;

import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.LogManager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/**
 * Test class for {@link LoggingConfiguration}, ensuring that logging configuration methods work as
 * expected
 */
public class LoggingConfigurationTest
{

	/**
	 * Set up necessary configurations before running the tests
	 */
	@BeforeAll
	public static void setUpBeforeClass()
	{
		LoggingConfiguration.setup();
	}

	/**
	 * Tests that the logging setup initializes correctly by checking that SLF4J bridge handlers are
	 * installed
	 */
	@Test
	public void testSetup()
	{
		assertDoesNotThrow(LoggingConfiguration::setup);
	}

	/**
	 * Verifies that the SLF4J bridge handler configuration completes without errors and captures
	 * JUL logs as expected
	 */
	@Test
	public void testSetupJavaUtilLoggingToSlf4jBridge()
	{
		assertDoesNotThrow(LoggingConfiguration::setupJavaUtilLoggingToSlf4jBridge);
	}

	/**
	 * Tests that the logging properties file is loaded correctly by
	 * {@link LoggingConfiguration#loadLoggingFile()} without throwing exceptions
	 */
	@Test
	public void testLoadLoggingFile()
	{
		assertDoesNotThrow(LoggingConfiguration::loadLoggingFile);
	}

	/**
	 * Parameterized test for verifying behavior of {@link LoggingConfiguration#loadLoggingFile()}
	 * with different logging property files specified in a CSV file
	 *
	 * @param propertiesFile
	 *            the name of the logging properties file to be loaded
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/logging-file-tests.csv", numLinesToSkip = 1)
	public void testLoadLoggingFileWithCsv(String propertiesFile)
	{
		assertDoesNotThrow(() -> {
			LogManager.getLogManager().readConfiguration(
				LoggingConfiguration.class.getClassLoader().getResourceAsStream(propertiesFile));
		});
	}
}
