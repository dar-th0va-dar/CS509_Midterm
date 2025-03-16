package src;

import com.google.inject.AbstractModule;
import src.interfaces.ILogin;
import src.interfaces.IRun;

public class ATMModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IRun.class).to(Run.class);
        bind(ILogin.class).to(Login.class);
    }
}
