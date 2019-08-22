/*
 * Copyright 2018 David C Cook. All rights reserved.
 * This source file is the exclusive property of David C Cook. It is
 * for the exclusive use of David C Cook and no permission is given for
 * any other use is given without written permission.
 */
package com.recomm.business.logging;

import java.util.Arrays;
import java.util.Calendar;

/**
 *
 * @author Paul.Allen
 */
public class Logger
{
    private static final boolean DEBUG = false;

////    private static User user = new User();

    private static String clientIP = "0.0.0.0";

////    public static void setUser (User newUser)
////    {
////        user = newUser;
////    }

    public static void log (Class caller, int code, String message)
    {
        java.util.Date utilDate = Calendar.getInstance().getTime();

        if (DEBUG)
        {
            System.out.println ("<" + utilDate + "> " + caller.toString() +
                                ", Error Code: " + code +
                                ", Message: " + message
                               );
        }
        // else do nothing
    }

    public static void log (Class caller, String message)
    {
        java.util.Date utilDate = Calendar.getInstance().getTime();

        if (DEBUG)
        {
            System.out.println ("<" + utilDate + "> " + caller.toString() +
                                ", Message: " + message);
        }
        // else do nothing
    }

    public static void log (Class caller,
                            String transactionId,
                            String authCode,
                            String amount,
                            String customerId,
                            String errorCode,
                            String errorMsg,
                            String errorType,
                            String logMsg,
                            String clientIP,
                            boolean record
                           )
    {
        java.util.Date utilDate = Calendar.getInstance().getTime();
////        ILogsAccessSvc logSvc = new LogsAccessSvcImpl();
////        Logs log;
////        Collection<Logs> logs = new ArrayList<Logs>();

        if (clientIP != null)
        {
            Logger.clientIP = clientIP;
        }
        // else do nothing - use value already set

        if (authCode == null)
        {
            authCode = "none";
        }

        System.out.println ("<" + utilDate + "> \n\t" +
                            caller.toString() +
                            "\n\tClient IP: " + Logger.clientIP +
                            "\n\tTransaction ID: " + transactionId +
                            "\n\tAuth Code: " + authCode +
                            "\n\tAmount: " +amount +
                            "\n\tCustomer ID: " + customerId +
                            "\n\tError Code: " + errorCode +
                            "\n\tError Msg: " + errorMsg +
                            "\n\tDetails: " + logMsg
                           );

        // Create log entry in DB if flag set

        if (logMsg == null)
        {
            logMsg = "";
        }

        if (record)
        {
////            log = new Logs (0,
////                            user.getId().getIdUser(),
////                            Logger.clientIP,
////                            transactionId,
////                            authCode,
////                            amount,
////                            customerId,
////                            errorCode,
////                            errorType,
////                            errorMsg,
////                            logMsg,
////                            caller.toString(),
////                            utilDate
////                           );
////            logs.add (log);
        }
        // else do nothing - record flag not set

////        if (!logSvc.save (logs))
        {
////            logSvc.close ();

////            if (!logSvc.save (logs))
            {
                System.err.println ("<" + utilDate + "> Failed to create Logs DB entry: \n\t" + caller.toString());
            }
            //else do nothing
        }
        // else do nothing
    }

    public static void log (Class caller, Exception e)
    {
        java.util.Date utilDate = Calendar.getInstance().getTime();

        System.err.print ("<" + utilDate + "> " + caller.toString() + "Threw an exception:\n\n");
        System.err.println (e.getLocalizedMessage());
        System.err.println (Arrays.toString (e.getStackTrace()));
    }

    public static void setClientIP (String clientIP)
    {
        Logger.clientIP = clientIP;
    }

    public static void log (Class caller, Exception e, String message)
    {
        java.util.Date utilDate = Calendar.getInstance().getTime();

        System.err.println ("<" + utilDate + "> " + caller.toString() + message);
        System.err.println (e.getLocalizedMessage());
        System.err.println (Arrays.toString (e.getStackTrace()));
    }
}
