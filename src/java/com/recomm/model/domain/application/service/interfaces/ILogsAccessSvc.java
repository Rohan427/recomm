/*
 * Copyright 2018 David C Cook. All rights reserved.
 * This source file is the exclusive property of David C Cook. It is
 * for the exclusive use of David C Cook and no permission is given for
 * any other use is given without written permission.
 */
package com.recomm.model.domain.application.service.interfaces;

import com.recomm.model.service.interfaces.Ipersist;
import java.util.Collection;
import java.util.Date;
import com.recomm.model.domain.application.entity.Logs;
import com.recomm.model.domain.application.interfaces.ILogs;

/**
 *
 * @author Paul.Allen
 */
public interface ILogsAccessSvc extends Ipersist
{
    Logs readLog (String customerId);

    Collection<Logs> searchLogs (ILogs object, Date begin, Date end);

    Collection<Logs> readLogs ();

    boolean removeLog (ILogs log);

    boolean updateLog (ILogs log, boolean isUpdate);
}
