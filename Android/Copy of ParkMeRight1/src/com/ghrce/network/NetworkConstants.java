package com.ghrce.network;

public class NetworkConstants {
 
    public static final int NETWORK_NOT_AVAILABLE           = 100;
    
    public static final int INVALID_REQUEST                 = 101;
    
    public static final int NETWORK_TIME_OUT                = 102;
    
    public static final int REQUEST_FAILED                  = 103;
    
    public static final class SignUpRequestConstants
    {
        public static final int EMAIL_ALREADY_TAKEN         = 200; 
    }
    
    public static final class SignInRequestConstans
    {
        public static final int SIGN_IN_FAILED              = 300;
    }
    
    public static final String EMAIL_ALREADY_TAKEN_ERROR = "Email Already Exists!";
    
    public static final String ERROR_CODE = "error";
}
