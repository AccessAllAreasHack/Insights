package com.barclays.insight.acquisition.web;

import com.barclays.insight.acquisition.dao.InsightDao;
import com.barclays.insight.acquisition.data.Insight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InsightsController {

    @Autowired
    private InsightDao insightDao;

    @ResponseBody
    @RequestMapping(value = "/insights", method = RequestMethod.GET)
    public List<Insight> listInsights() {
        final List<Insight> insights = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            insights.add(insightDao.getInsight(i));
        }
        return insights;
    }

}
