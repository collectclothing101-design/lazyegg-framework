package io.lazyegg.core.util;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;

import java.util.Objects;

/**
 * SpringUtil
 *
 * @author DifferentW  nsmeng3@163.com
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    /**
     * -- GETTER --
     *  获取applicationContext
     */
    @Getter
    private static ApplicationContext applicationContext = null;

    /**
     * -- GETTER --
     *  获取环境变量
     */
    @Getter
    private static Environment environment = null;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
            environment = applicationContext.getEnvironment();
        }
    }

    /**
     * 通过name获取 Bean
     *
     * @param name bean名称
     * @return Object
     */
    public static Object getBean(String name) {
        try {
            return getApplicationContext().getBean(name);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 通过class获取Bean
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        try {
            return getApplicationContext().getBean(clazz);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        try {
            return getApplicationContext().getBean(name, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取String类型的环境变量
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        return environment.getProperty(key);
    }

    /**
     * 获取int类型的环境变量
     *
     * @param key
     * @return
     */
    public static int getInt(String key) {
        return NumberUtils.parseNumber(Objects.requireNonNull(environment.getProperty(key)), Integer.class);
    }

    /**
     * 获取long类型的环境变量
     *
     * @param key
     * @return
     */
    public static long getLong(String key) {
        return NumberUtils.parseNumber(Objects.requireNonNull(environment.getProperty(key)), Long.class);
    }

    /**
     * 获取boolean类型的环境变量
     *
     * @param key
     * @return
     */
    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(environment.getProperty(key));
    }

}
