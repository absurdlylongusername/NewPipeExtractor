package org.schabi.newpipe.extractor.utils;

public final class ExtractorLogger {

    private ExtractorLogger() { }

    private static Logger logger = new ConsoleLogger();

    public static void setLogger(final Logger customLogger) {
        logger = customLogger;
    }

    public static void d(final String tag, final String msg) {
        logger.debug(tag, msg);
    }

    public static void w(final String tag, final String msg) {
        logger.warn(tag, msg);
    }

    public static void e(final String tag, final String msg) {
        logger.error(tag, msg);
    }

    public static void e(final String tag, final String msg, final Throwable t) {
        logger.error(tag, msg, t);
    }

    // default logger that prints to stdout
    private static final class ConsoleLogger implements Logger {
        public void debug(final String tag, final String msg) {
            System.out.println("[DEBUG][" + tag + "] " + msg);
        }

        public void warn(final String tag, final String msg) {
            System.out.println("[WARN ][" + tag + "] " + msg);
        }

        public void error(final String tag, final String msg) {
            System.err.println("[ERROR][" + tag + "] " + msg);
        }

        public void error(final String tag, final String msg, final Throwable t) {
            System.err.println("[ERROR][" + tag + "] " + msg);
            t.printStackTrace(System.err);
        }
    }
}
