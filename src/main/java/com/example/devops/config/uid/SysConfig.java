package com.example.devops.config.uid;

import com.example.devops.utils.FactoryDeployConf;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PublicKey;

/**
 * 系统配置信息
 * @author Holi
 * 2020年1月15日 下午2:56:09
 */
@Component
@Data
public class SysConfig {

	/**
	 * 工厂id
	 */
	private String workerId;


	/**
	 * 工厂配置文件路径
	 */
	@Value("${sys.factory_conf_path}")
	private String factoryConfPath;

	/**
	 * 工厂ID
	 */
	private String factoryId;

	/**
	 * 工厂名
	 */
	private String factoryName;

	/**
	 * 解压缩密码
	 */
	private String uncompressPassword;

	/**
	 *
	 * @throws Exception
	 */
	@PostConstruct
	private void init() throws Exception {
		FactoryDeployConf conf = FactoryDeployConf.fromYAML(new File(factoryConfPath));
		workerId = conf.getWorkerId();
		factoryId = conf.getId();
		uncompressPassword = conf.getEncKey();
		factoryName = conf.getName();
	}

}
