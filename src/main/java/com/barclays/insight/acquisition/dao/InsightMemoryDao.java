package com.barclays.insight.acquisition.dao;

import com.barclays.insight.acquisition.data.Insight;
import org.springframework.stereotype.Component;

@Component
public class InsightMemoryDao implements InsightDao {

    public Insight getInsight(final int id) {
        String name;
        String image;
        switch (id) {
            case 1:
                name = "Bruces";
                image = "male";
                break;
            case 2:
                name = "Sheilas";
                image = "female";
                break;
            case 3:
                name = "Top Drop";
                image = "wine";
                break;
            case 4:
                name = "Single Bruces";
                image = "wine-male";
                break;
            case 5:
                name = "Single Sheilas";
                image = "wine-female";
                break;
            case 6:
                name = "Fancy a Tipple";
                image = "wine";
                break;
            case 7:
                name = "Clean Freaks";
                image = "clean";
                break;
            default:
                name = "insight " + id;
                image = "demo";
        }
        final Insight insight = new Insight();
        insight.setId(String.valueOf(id));
        insight.setImage(image);
        insight.setName(name);
        insight.setDetails("content " + id);
        return insight;
    }
}
