package kr.or.ddit.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class CompositeConfigInitializer implements ServletContextListener {


    public void contextInitialized(ServletContextEvent sce)  {  // 어플리케이션이 시작이 됐다.
    	ServletContext application =  sce.getServletContext();
    	System.out.printf("%s 초기화\n", application.getContextPath());
    	Properties properties = new Properties();
    	application.setAttribute("compositeConfig", properties);
    	try(
   			InputStream is = application.getClassLoader().getResourceAsStream("compositeConfig.xml"); // 클래스 패스 리소스를 쉽게 찾아 낼 수 있음
  		) {
			properties.loadFromXML(is);
			
		} catch (IOException e) {
			throw new RuntimeException(e); // unchecked exception
		}
    }

    public void contextDestroyed(ServletContextEvent sce)  { // 컨텍스트가 종료되었다.
    	ServletContext application =  sce.getServletContext();
    	System.out.printf("%s 소멸\n", application.getContextPath());
    }

	
}
