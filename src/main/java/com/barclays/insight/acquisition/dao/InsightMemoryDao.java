package com.barclays.insight.acquisition.dao;

import com.barclays.insight.acquisition.data.Insight;
import org.springframework.stereotype.Component;

@Component
public class InsightMemoryDao implements InsightDao {

    public Insight getInsight(final int id) {
        String name;
        switch (id) {
            case 1:
                name = "gender=male";
                break;
            case 2:
                name = "gender=female";
                break;
            case 3:
                name = "product=merlot";
                break;
            case 4:
                name = "product=merlot, gender=male";
                break;
            case 5:
                name = "product=merlot, gender=female";
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
