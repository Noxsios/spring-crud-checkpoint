package com.galvanize.springcrudcheckpoint;

public class Authenticated {
    private boolean authen;
    private User user;

    public Authenticated(boolean auth){
        this.authen = auth;
        this.user = new User();
    }

    public boolean isAuthen() {
        return authen;
    }

    public void setAuthen(boolean authen) {
        this.authen = authen;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
