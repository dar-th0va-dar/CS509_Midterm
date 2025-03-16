package src;

import com.google.inject.AbstractModule;

public class ATMModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ILogin.class).to(Login.class);
    }
}
