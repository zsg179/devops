package com.example.devops.utils;

import com.esotericsoftware.yamlbeans.YamlConfig;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;
import lombok.Data;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringWriter;

/**
 * @author 曙光
 */
@Data
public class FactoryDeployConf {

    private String workerId;
    private String name;
    private String id;
    private String pubKey;
    private String encKey;

    public String toYAML() throws Exception {
        StringWriter buffer = new StringWriter();
        YamlWriter writer = new YamlWriter(buffer);
        writer.getConfig().writeConfig.setWriteClassname(YamlConfig.WriteClassName.NEVER);
        writer.getConfig().writeConfig.setEscapeUnicode(false);
        writer.getConfig().writeConfig.setIndentSize(2);
        writer.write(this);
        writer.close();
        return buffer.toString();
    }

    public static FactoryDeployConf fromYAML(String yaml) throws Exception{
        YamlReader reader = new YamlReader(yaml);
        return reader.read(FactoryDeployConf.class);
    }

    public static FactoryDeployConf fromYAML(File file) throws Exception {
        Reader fr = new FileReader(file);
        YamlReader reader = new YamlReader(fr);
        return reader.read(FactoryDeployConf.class);
    }
}
