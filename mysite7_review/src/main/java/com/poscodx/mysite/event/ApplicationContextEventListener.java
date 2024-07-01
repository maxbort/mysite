package com.poscodx.mysite.event;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import com.poscodx.mysite.service.SiteService;
import com.poscodx.mysite.vo.SiteVo;

public class ApplicationContextEventListener { //스프링 애플리케이션에서 애플리케이션 컨텍스트 이벤트를 처리하는 이벤트 리스너를 정의
	
	/**
	 *  
	어플리케이션 시작 시 호출되는 순서
	1. ApplicationStartingEvent
	2. ApplicationEnvironmentPreparedEvent
	3. ApplicationContextInitializedEvent
	4. ApplicationPreparedEvent
	5. ContextRefreshedEvent
	6. ApplicationStartedEvent
	7. ApplicationReadyEvent
	
	여기서, 1-3 번 까지는 ApplicationContext 초기화 전에 시작된다. 때문에, 이 이벤트들은 @EventListener 를 통한 이벤트 등록으로 처리할 수 없다. 왜냐하면 @EventListener 는 해당 리스너 메서드를 가진 클래스가 스프링 빈으로 먼저 스캔되어야 작동하는데, 1-3번 이벤트들은 ApplicationContext 시작 전이기 때문에 이벤트 등록보다 실제 이벤트가 더 먼저 발생한다.
	
	때문에 1-3 번 이벤트들이 작동하기 위해선 스프링 어플리케이션 시작 전에 리스너를 선등록해야 한다.
	 */
	
	@Autowired
	private ApplicationContext applicationContext;
   
	@EventListener({ContextRefreshedEvent.class})
	public void handleContextRefreshEvent() {
		System.out.println("--- Context Refreshed Event Received ---");
		   
		SiteService siteService = applicationContext.getBean(SiteService.class);
		SiteVo vo = siteService.getSite();
		  
		//applicationContext.
		
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		
		propertyValues.add("title", vo.getTitle());
		propertyValues.add("profile", vo.getProfile());
		propertyValues.add("welcome", vo.getWelcome());
		propertyValues.add("description", vo.getDescription());

		
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(SiteVo.class);
		beanDefinition.setPropertyValues(propertyValues);

		applicationContext.getAutowireCapableBeanFactory();
		
		// 빈 동적으로 등록
		BeanDefinitionRegistry registry = (BeanDefinitionRegistry)applicationContext.getAutowireCapableBeanFactory();
		registry.registerBeanDefinition("site", beanDefinition);
	}
	
}
