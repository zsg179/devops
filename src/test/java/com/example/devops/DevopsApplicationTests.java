package com.example.devops;

import com.baidu.fsg.uid.UidGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.IdGenerator;

import java.util.ArrayList;

@SpringBootTest
class DevopsApplicationTests {

	@Autowired
	private UidGenerator uidGenerator;

	@Test
	void contextLoads() {
		ArrayList<Long> list = new ArrayList<>();
		for (int i = 0; i < 65540; i++) {
			long uid = uidGenerator.getUID();
			list.add(uid);
		}
	}

}
