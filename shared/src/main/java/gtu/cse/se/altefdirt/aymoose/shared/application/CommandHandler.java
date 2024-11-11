package gtu.cse.se.altefdirt.aymoose.shared.application;

public interface CommandHandler<C extends Command, R> {

    R handle(C command);

}