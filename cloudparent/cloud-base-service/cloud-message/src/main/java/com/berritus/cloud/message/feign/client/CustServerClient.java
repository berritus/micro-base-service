package com.berritus.cloud.message.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//@FeignClient(value = "cust-server")
public interface CustServerClient {
//    @RequestMapping(method = RequestMethod.GET, value = "/qry/bank/req?msgId={msgId}")
//    public @ResponseBody BankAccountReq qryBankAccountReqByMsgId(@PathVariable("msgId") Integer msgId);
}
