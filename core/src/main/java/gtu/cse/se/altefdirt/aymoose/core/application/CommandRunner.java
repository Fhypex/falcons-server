package gtu.cse.se.altefdirt.aymoose.core.application;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandRunner {

    private final CommandHandlerRegistry registry;
    
    @SuppressWarnings("unchecked")
    public <R, C extends Command> R run(C command) {
        CommandHandler<C, R> handler = (CommandHandler<C, R>) registry.getHandler(command.getClass());
        if (handler != null) {
            return handler.handle(command);
        } else {
            throw new IllegalArgumentException("No handler found for command: " + command.getClass().getName());
        }       
    }
}