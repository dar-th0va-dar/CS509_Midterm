package src.interfaces;

import com.google.inject.AbstractModule;
import src.ui.Login;
import src.ui.Run;

public class ATMModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IRun.class).to(Run.class);
        bind(ILogin.class).to(Login.class);
    }
}
