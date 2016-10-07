package com.barclays.insight.acquisition.web;

import com.barclays.insight.acquisition.data.EposDataExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExtractController {

    @Autowired
    EposDataExtractor eposDataExtractor;

    @ResponseBody
    @RequestMapping(value = "/extract", method = RequestMethod.GET)
    public String extract() {

        eposDataExtractor.extract();
        return "success";
    }
}
