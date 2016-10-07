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
                image = "male.png";
                break;
            case 2:
                name = "Sheilas";
                image = "female.png";
                break;
            case 3:
                name = "Top Drop";
                image = "wine.png";
                break;
            case 4:
                name = "Single Bruces";
                image = "wine-male.png";
                break;
            case 5:
                name = "Single Sheilas";
                image = "wine-female.png";
                break;
            case 6:
                name = "Fancy a Tipple";
                image = "wine.png";
                break;
            case 7:
                name = "Clean Freaks";
                image = "clean.png";
                break;
            default:
                name = "insight " + id;
                image = "demo.png";
        }
        final Insight insight = new Insight();
        insight.setId(String.valueOf(id));
        insight.setImage(image);
        insight.setName(name);
        insight.setDetails("content " + id);
        return insight;
    }
}
