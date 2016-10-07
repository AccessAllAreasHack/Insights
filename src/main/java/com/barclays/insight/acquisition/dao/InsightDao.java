package com.barclays.insight.acquisition.dao;

import com.barclays.insight.acquisition.data.Insight;

/**
 * Created by Rob on 07/10/2016.
 */
public interface InsightDao {

    Insight getInsight(final int id);
}
