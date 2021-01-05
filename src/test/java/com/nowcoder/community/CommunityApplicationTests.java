package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
//add junit4 to classpath
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

//@SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootTest//将SpringBoot主类中导入的bean全都包含进来 @SpringBootApplication
@ContextConfiguration(classes = CommunityApplication.class)
/*当一个类添加了注解@Component,那么他就自动变成了一个bean，就不需要再Spring配置文件中显示的配置了。
		把这些bean收集起来通常有两种方式，Java的方式和XML的方式。当这些bean收集起来之后，
		当我们想要在某个测试类使用@Autowired注解来引入这些收集起来的bean时，
		只需要给这个测试类添加@ContextConfiguration注解来标注我们想要导入这个测试类的某些bean。*/
class CommunityApplicationTests implements ApplicationContextAware {

	/*@Test
	void contextLoads() {
	}*/

	private ApplicationContext applicationContext;

	//ApplicationContext spring容器
	//ApplicationContext -> extends HierarchicalBeanFactory -> extends BeanFactory
	//BeanFactory spring容器，spring容器的顶层接口
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext() {
		System.out.println(applicationContext);

		//按照类获取（名字也有）
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());

		alphaDao = applicationContext.getBean("alphaHibernate", AlphaDao.class);
		System.out.println(alphaDao.select());
	}

	@Test
	public void testBeanManagement() {
		//按类 现在没写接口
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);

		alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
	}

	@Test
	public void testBeanConfig() {
		SimpleDateFormat simpleDateFormat =
				applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	@Autowired
	@Qualifier("alphaHibernate")
	private AlphaDao alphaDao;

	@Autowired
	private AlphaService alphaService;

	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Test
	public void testDI() {
		System.out.println(alphaDao);
		System.out.println(alphaService);
		System.out.println(simpleDateFormat);
	}

}
