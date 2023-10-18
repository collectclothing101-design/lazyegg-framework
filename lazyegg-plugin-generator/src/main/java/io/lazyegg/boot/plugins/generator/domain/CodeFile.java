package io.lazyegg.boot.plugins.generator.domain;

import io.lazyegg.boot.plugins.generator.infrastructure.util.LeggJavaCodeUtil;
import lombok.Data;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.Serializable;
import java.io.StringWriter;

/**
 * OutputCodeFile
 *
 * @author DifferentW  nsmeng3@163.com
 */

@Data
public class CodeFile implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(CodeFile.class);

    private static final String userDir = System.getProperty("user.dir");

    /**
     * 代码内容字符串流
     */
    @NonNull
    private StringWriter sw;

    @NonNull
    private String modulePrefix;

    @NonNull
    private String moduleName;

    private LayerType layerType;
    private String layerModuleName;

    public CodeFile(@NonNull StringWriter sw, @NonNull String modulePrefix, @NonNull String moduleName) {
        this.sw = sw;
        this.modulePrefix = modulePrefix;
        this.moduleName = moduleName;
        this.layerType = LayerType.of(this);
        this.layerModuleName = String.join("-", modulePrefix, moduleName, layerType.getLowerName());


    }

    /**
     * 包名
     *
     * @return 包名
     */
    public String packageName() {
        return LeggJavaCodeUtil.parsePackageName(sw.toString());
    }

    /**
     * 包路径
     *
     * @return 包路径
     */
    public String packagePath() {
        return packageName().replaceAll("\\.", File.separator);
    }


    /**
     * 类名
     *
     * @return 类名
     */
    public String className() {
        return LeggJavaCodeUtil.parseClassName(sw.toString());
    }

    /**
     * 文件名  Aaa.java
     *
     * @return 文件名
     */
    public String fileName() {
        return String.join(".", className(), "java");
    }

    /**
     * 文件全名  xxx/yyy/Aaa.java
     *
     * @return 文件全名
     */
    public String fileFullName() {
        return String.join(File.separator, packagePath(), fileName());
    }

    /**
     * 获取绝对路径
     *
     * @return 绝对路径
     */
    public String absolutePath() {
        return String.join(File.separator, absolute(), fileFullName());
    }

    private String absolute() {
        if (className().endsWith("Test")) {
            return String.join(File.separator, userDir, "..", layerModuleName,
                    "src", "test", "java");
        }
        return String.join(File.separator, userDir, "..", layerModuleName,
                "src", "main", "java");
    }


    /**
     * 获取绝对包路径
     *
     * @return 绝对包路径
     */
    public String absolutePackagePath() {
        return String.join(File.separator, absolute(),
                packagePath());
    }
}
