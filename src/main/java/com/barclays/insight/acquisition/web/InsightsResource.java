package com.barclays.insight.acquisition.web;

import com.barclays.insight.acquisition.data.GeoLocation;
import com.barclays.insight.acquisition.data.Insight;
import com.barclays.insight.acquisition.data.Location;
import com.barclays.insight.acquisition.data.Result;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InsightsResource {

    @ResponseBody
    @RequestMapping(value = "/insights", method = RequestMethod.GET)
    public List<Insight> listInsights() {
        final List<Insight> insights = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            insights.add(getInsight(i));
        }
        return insights;
    }

    private Insight getInsight(final int id) {
        final Insight insight = new Insight();
        insight.setId("id" + id);
        insight.setImage("/path/to/image" + id + ".png");
        insight.setName("product" + id);
        insight.setDetails("lorem ipsum dolor sit amet");
        return insight;
    }
}
