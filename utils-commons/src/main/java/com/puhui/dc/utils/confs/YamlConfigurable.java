package com.puhui.dc.utils.confs;

import com.google.common.io.ByteStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.PropertyUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by duming on 2015/4/15.
 */
public class YamlConfigurable {
    private static final Logger LOG = LoggerFactory.getLogger(YamlConfigurable.class);
    private static final Map<String, Object> configs = new HashMap<>(8, 0.75f);

    /**
     * 因为加载程序发生多线程调用一个配置的机率不大, 所以只是简单的用了 synchronized method
     *
     * @param yamlPropertiesUrlBasedClassPath 相对于classpath的配置文件地址
     * @param clazz                           要转换成的类对象
     * @param <T>
     * @return
     */
    public static synchronized <T> T getInstance(String yamlPropertiesUrlBasedClassPath, Class<T> clazz) {
        return getInstance(yamlPropertiesUrlBasedClassPath, clazz, null);
    }


    public static synchronized <T> T getInstance(String yamlPropertiesUrlBasedClassPath, Class<T> clazz, InputStream is) {

        if (yamlPropertiesUrlBasedClassPath == null) {
            throw new RuntimeException("propertiesUrlBasedClassPath cannot be null");
        }

        if (configs.containsKey(yamlPropertiesUrlBasedClassPath)) {
            return (T) configs.get(yamlPropertiesUrlBasedClassPath);
        }

        LOG.info("Loading settings from :" + yamlPropertiesUrlBasedClassPath + ", and the class type is :" + clazz);

        byte[] configBytes;
        try {
            if (is == null) {
                is = ClassLoader.getSystemClassLoader().getResourceAsStream(yamlPropertiesUrlBasedClassPath);
            }
            configBytes = ByteStreams.toByteArray(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        org.yaml.snakeyaml.constructor.Constructor constructor = new org.yaml.snakeyaml.constructor.Constructor(clazz);
        constructor.setPropertyUtils(new PropertyUtils() {
            {
                setSkipMissingProperties(true);
            }
        });
        Yaml yaml = new Yaml(constructor);
        T config = yaml.loadAs(new ByteArrayInputStream(configBytes), clazz);
        if (ConfigInitable.class.isAssignableFrom(clazz)) {
            ((ConfigInitable) config).init();
        }
        configs.put(yamlPropertiesUrlBasedClassPath, config);
        return config;
    }

}
