package com.github.emsp.simulator.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.github.emsp.simulator.entity.Request;
import com.github.emsp.simulator.repository.RequestRepository;

@Service
public class JitterSimulatorService {

    @Autowired
    private RequestRepository repository;

    public Integer simulateJitter(String module, String version, String uid, Integer retry, Integer status, Integer timeout) {
        if (status != null && uid != null && retry != null) {

            Integer currentRetry = 1;
            List<Request> requests = repository.findByUidOrderByDateAsc(uid);
            Request request = new Request();
            if (!requests.isEmpty()) {
                currentRetry = requests.size() + 1;
                if (currentRetry >= retry) {
                    status = 200;
                }
            }
            timeout = timeout == null ? 0 : timeout;
            if (timeout > 0 && currentRetry < retry) {
                try {
                    Thread.sleep(timeout * 1_000);
                } catch (Exception ex) {
                    // intentionally left blank
                }
            } else {
                timeout = 0;
            }
            request.setModule(module);
            request.setDate(new Date());
            request.setParty("eMSP");
            request.setVersion(version);
            request.setData(
                    "max retry:" + retry + ", retry: " + currentRetry + ", status:" + status + ", timeout=" + timeout);
            request.setUid(uid);
            repository.save(request);
            return status;
        }
        return HttpStatus.OK.value();
    }

    public Integer simulateJitter(String uid, Integer retry, Integer status, Integer timeout, Request request) {
        if (status != null && uid != null && retry != null) {
            Integer currentRetry = 1;
            List<Request> requests = repository.findByUidOrderByDateAsc(uid);
            if (!requests.isEmpty()) {
                currentRetry = requests.size() + 1;
                if (currentRetry >= retry) {
                    status = 200;
                }
            }
            timeout = timeout == null ? 0 : timeout;
            if (timeout > 0 && currentRetry < retry) {
                try {
                    Thread.sleep(timeout * 1_000);
                } catch (Exception ex) {
                    // intentionally left blank
                }
            } else {
                timeout = 0;
            }
            request.setData("max retry:" + retry + ", retry: " + currentRetry + ", status:" + status + ", timeout="
                    + timeout + ", " + request.getData());
            repository.save(request);
            return status;
        }
        return HttpStatus.OK.value();
    }
}
