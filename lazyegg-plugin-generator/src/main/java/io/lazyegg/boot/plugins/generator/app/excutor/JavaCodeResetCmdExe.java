package io.lazyegg.boot.plugins.generator.app.excutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaCodeResetCmdExe
 * 代码撤销命令
 *
 * @author DifferentW  nsmeng3@163.com
 */

@Component
public class JavaCodeResetCmdExe {
    private static final Logger log = LoggerFactory.getLogger(JavaCodeResetCmdExe.class);

    public void execute(String entityName) {
        reset(entityName);
    }

    private void reset(String entityName) {

        String logFile = String.join(File.separator, "log", entityName + ".rl");
        File rlFile = new File(logFile);
        if (!rlFile.exists()) {
            log.warn("记录文件不存在: {}", logFile);
            return;
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(rlFile), StandardCharsets.UTF_8))) {
            List<File> files = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    files.add(new File(line));
                }
            }

            for (File file : files) {
                if (!file.exists()) {
                    log.warn("{}文件不存在", file);
                    continue;
                }
                try {
                    boolean delete = file.delete();
                } catch (Exception e) {
                    log.warn("{}删除异常", file.getAbsoluteFile());
                }
            }

            if (rlFile.exists()) {
                rlFile.delete();
            }
        } catch (Exception e) {
            log.warn("记录读取异常，撤销已生成代码操作无法进行", e);
        }
    }

}
