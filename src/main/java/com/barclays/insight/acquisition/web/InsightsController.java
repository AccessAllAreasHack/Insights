package com.barclays.insight.acquisition.web;

import com.barclays.insight.acquisition.data.Insight;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InsightsController {

    @ResponseBody
    @RequestMapping(value = "/insights", method = RequestMethod.GET)
    public List<Insight> listInsights() {
        final List<Insight> insights = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            insights.add(getInsight(i));
        }
        return insights;
    }

    private Insight getInsight(final int id) {
        String name;
        switch (id) {
            case 1:
                name = "mamils";
                break;
            case 2:
                name = "lunching ladies";
                break;
            case 3:
                name = "close competitors";
                break;
            default:
                name = "insight " + id;
        }
        final Insight insight = new Insight();
        insight.setId(String.valueOf(id));
        insight.setImage("img/macbook.jpg");
        insight.setName(name);
        insight.setDetails("content " + id);
        return insight;
    }
}
