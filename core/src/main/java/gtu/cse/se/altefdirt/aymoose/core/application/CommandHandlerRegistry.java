package gtu.cse.se.altefdirt.aymoose.core.application;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import jakarta.annotation.PostConstruct;

import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CommandHandlerRegistry {

    private final ApplicationContext applicationContext;
    private final Map<Class<? extends Command>, CommandHandler<?, ?>> handlers = new ConcurrentHashMap<>();

    public CommandHandlerRegistry(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        // Discover all beans annotated with @RegisterHandler and register them
        Map<String, Object> annotatedBeans = applicationContext.getBeansWithAnnotation(RegisterHandler.class);
        for (Object bean : annotatedBeans.values()) {
            if (bean instanceof CommandHandler) {
                CommandHandler<?, ?> handler = (CommandHandler<?, ?>) bean;
                Class<? extends Command> commandType = getCommandType(handler);
                handlers.put(commandType, handler);
            }
        }
    }

    /**
     * Resolves the command type by checking if the handler is a proxy and
     * unwrapping it.
     * 
     * @param handler the CommandHandler to extract the command type from
     * @return the Class representing the command type
     */
    @SuppressWarnings("unchecked")
    private Class<? extends Command> getCommandType(CommandHandler<?, ?> handler) {
        // Unwrap the proxy if necessary
        Class<?> handlerClass = AopProxyUtils.ultimateTargetClass(handler);

        // Now extract the generic type from the real class
        return (Class<? extends Command>) ((ParameterizedType) handlerClass
                .getGenericInterfaces()[0])
                .getActualTypeArguments()[0];
    }

    /*
     * @SuppressWarnings("unchecked")
     * private Class<? extends Command> getCommandType(CommandHandler<?, ?> handler)
     * {
     * return (Class<? extends Command>) ((ParameterizedType) handler.getClass()
     * .getGenericInterfaces()[0])
     * .getActualTypeArguments()[0];
     * }
     */

    @SuppressWarnings("unchecked")
    public <C extends Command, R> CommandHandler<C, R> getHandler(Class<C> commandClass) {
        CommandHandler<C, R> handler = (CommandHandler<C, R>) handlers.get(commandClass);
        if (handler != null) {
            return handler;
        } else {
            throw new IllegalStateException("No handler found for command: " + commandClass.getName());
        }
    }
}