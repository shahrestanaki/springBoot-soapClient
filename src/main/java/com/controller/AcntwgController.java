package com.controller;

import com.config.SOAPConnector;
import com.localhost.springsoap.gen.GetAcntwgRequest;
import com.localhost.springsoap.gen.GetAcntwgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class AcntwgController {
    @Autowired
    private SOAPConnector soapConnector;

    @RequestMapping(value = "/getAcntwg", method = RequestMethod.GET)
    public Double getAcntwg(@RequestParam("accno") String accno) {
        GetAcntwgRequest request = new GetAcntwgRequest();
        request.setAcntno(accno);
        GetAcntwgResponse response = (GetAcntwgResponse) soapConnector.callWebService("http://localhost:8043/ws", request);
        return response.getResult();
    }
}
