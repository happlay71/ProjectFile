package happlay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 引导类。SpringBoot项目的入口
 */

// 本身就是Spring的一个组件
@SpringBootApplication  // 标注这是一个SpringBoot的应用
public class DemoApplication {
	// 将SpringBoot应用启动
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
