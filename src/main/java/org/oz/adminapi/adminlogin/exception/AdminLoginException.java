package org.oz.adminapi.adminlogin.exception;

public enum AdminLoginException {
    BAD_AUTH(400,"ID/PW incorrect"),
    TOKEN_NOT_ENOUGH(401,"More Tokens required"),
    ACCESSTOKEN_TOO_SHORT(402,"Acceses Token too short"),
    REQUIRE_SIGN_IN(403,"Require sign in");

    private AdminLoginTaskException exception;

    AdminLoginException(int status, String msg){
        exception = new AdminLoginTaskException(status, msg);
    }

    public AdminLoginTaskException get() {
        return exception;
    }

}
