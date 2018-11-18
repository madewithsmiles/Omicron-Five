package edu.ttu.cs.se.control;

import edu.ttu.cs.se.applogic.ReportLogic;
import edu.ttu.cs.se.entity.TransactionLogEntity;

import java.util.TimerTask;

/**
 * Encapsulates execution of timer functions
 *
 * @author Justin Aguilar
 * created on 11/17/2018
 */
public class ReportTimer extends TimerTask {

    /**
     * Outputs transaction log and inventory report to separate files,
     * executes every day at midnight
     */
    public void run() {
        ReportLogic.buildTransLog("transaction_log.txt");
        ReportLogic.buildInvReport("inventory_report.txt");
        TransactionLogEntity.reset();
    }

}