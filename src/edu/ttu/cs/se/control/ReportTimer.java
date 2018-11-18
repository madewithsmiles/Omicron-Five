package edu.ttu.cs.se.control;

import edu.ttu.cs.se.applogic.ReportLogic;
import edu.ttu.cs.se.entity.TransactionLog;

import java.util.TimerTask;

public class ReportTimer extends TimerTask {

    /**
     * Outputs transaction log and inventory report to seperate files, executes every day at midnight
     */
    public void run() {
        ReportLogic.buildTransLog("transaction_log.txt");
        ReportLogic.buildInvReport("inventory_report.txt");
        TransactionLog.reset();
    }

}