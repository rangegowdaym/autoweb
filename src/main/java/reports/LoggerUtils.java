package reports;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtils {
    private static Logger logger;

    static {
        // Log4j 2 automatically loads configuration from the classpath
        try {
            logger = LogManager.getLogger(LoggerUtils.class);
        } catch (Exception e) {
            System.err.println("Failed to configure Log4j 2: " + e.getMessage());
        }
    }

    /**
     * Get the logger instance for a specific class.
     * @param clazz Class to associate the logger with
     * @return Logger instance
     */
    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }

    /**
     * Log an info message.
     * @param message Info message to log
     */
    public static void info(String message) {
        logger.info(message);
    }

    /**
     * Log an info message.
     * @param message Info message to log
     */
    public static void info(String message, Object... args) {
        logger.info(message, args);
    }

    /**
     * Log a debug message.
     * @param message Debug message to log
     */
    public static void debug(String message) {
        logger.debug(message);
    }

    /**
     * Log a warning message.
     * @param message Warning message to log
     */
    public static void warn(String message) {
        logger.warn(message);
    }

    /**
     * Log an error message.
     * @param message Error message to log
     */
    public static void error(String message) {
        logger.error(message);
    }

    /**
     * Log an exception with error level.
     * @param message Error message to log
     * @param throwable Exception to log
     */
    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}
