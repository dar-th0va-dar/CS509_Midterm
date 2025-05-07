package src;

import com.google.inject.Guice;
import com.google.inject.Injector;
import src.interfaces.*;

public class Main {
    public static void main(final String[] args) {
        final Injector injector = Guice.createInjector(new ATMModule());

        final IRun run = injector.getInstance(IRun.class);
        run.runATM();
    }
}
