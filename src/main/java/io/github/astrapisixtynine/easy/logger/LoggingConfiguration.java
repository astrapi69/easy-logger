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

import java.io.IOException;
import java.util.logging.LogManager;

import org.slf4j.bridge.SLF4JBridgeHandler;

/**
 * The class {@link LoggingConfiguration} sets up the logging configuration for the application
 */
public final class LoggingConfiguration
{

	/**
	 * Private constructor to prevent instantiation
	 */
	private LoggingConfiguration()
	{
	}

	/**
	 * Configures the logging setup for the application by resetting existing handlers, loading the
	 * logging properties file, and establishing a bridge to SLF4J for JUL log capture
	 */
	public static void setup()
	{
		LogManager.getLogManager().reset();
		loadLoggingFile();
		setupJavaUtilLoggingToSlf4jBridge();
	}

	/**
	 * Configures SLF4J bridge handler to capture JUL logs by removing the default JUL loggers and
	 * installing the SLF4J bridge handler
	 */
	public static void setupJavaUtilLoggingToSlf4jBridge()
	{
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
	}

	/**
	 * Loads the logging properties file into the {@link LogManager}
	 */
	public static void loadLoggingFile()
	{
		try
		{
			LogManager.getLogManager().readConfiguration(LoggingConfiguration.class.getClassLoader()
				.getResourceAsStream("logging.properties"));
		}
		catch (IOException e)
		{
			System.err.println("Could not load logging properties file");
		}
	}
}
