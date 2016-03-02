package ru.megazlo.flea.utils;

import java.lang.management.ManagementFactory;

/**
 * @author paradoxfm - 21.01.2016
 */
public class GlobalUtil {

    private static boolean debug;

    static {
        debug = ManagementFactory.getRuntimeMXBean().getInputArguments().toString().contains("jdwp");
    }

    /**
     * Проверна на то что кто-то подключен в режиме отладки
     *
     * @return если в режиме отладки
     */
    public static boolean isDebug() {
        return debug;
    }
}
