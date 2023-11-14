package com.springboot3crud.service;

import com.springboot3crud.entity.ActionLog;
import com.springboot3crud.model.Action;
import com.springboot3crud.repository.ActionLogRepository;
import com.springboot3crud.security.ActiveUserContext;
import com.springboot3crud.utils.WebUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ActionLogService {

    private final ActionLogRepository actionLogRepository;

    private final ActiveUserContext context;

    @Transactional
    public void publishActivity(Action action, String documentId, String comments) {
        ActionLog actionLog = new ActionLog();
        actionLog.setAction(action);
        actionLog.setDocumentId(documentId);
        actionLog.setComments(comments);
        actionLog.setIpAddress(WebUtils.getCurrentRequest().getRemoteAddr());
        actionLog.setCreated(new Date());
        actionLog.setUserName(context.getLoggedInUserName());
        actionLogRepository.save(actionLog);
    }
}
