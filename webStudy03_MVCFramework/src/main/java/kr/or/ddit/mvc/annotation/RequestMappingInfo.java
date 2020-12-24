package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

/**
 * HandlerMapping 이 핸들러에 대한 정보를 수집하고, 
 * Map을 만들 때, value로 설정.
 */
public class RequestMappingInfo {
	private RequestMappingCondition mappingCondition;
	private Object commandHandler;
	private Method handlerMethod;
	
	public RequestMappingInfo(RequestMappingCondition mappingCondition, Object commandHandler, Method handlerMethod) {
		this.mappingCondition = mappingCondition;
		this.commandHandler = commandHandler;
		this.handlerMethod = handlerMethod;
	}

	public RequestMappingCondition getMappingCondition() {
		return mappingCondition;
	}

	public Object getCommandHandler() {
		return commandHandler;
	}

	public Method getHandlerMethod() {
		return handlerMethod;
	}

	@Override
	public String toString() {
		return "RequestMappingInfo [mappingCondition=" + mappingCondition + ", commandHandler=" + commandHandler
				+ ", handlerMethod=" + handlerMethod + "]";
	}
	
}
