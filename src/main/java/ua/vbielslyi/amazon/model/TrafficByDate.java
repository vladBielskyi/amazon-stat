package ua.vbielslyi.amazon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.vbielslyi.amazon.util.ObjectUtil;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrafficByDate {
    private Integer browserPageViews;
    private Integer browserPageViewsB2B;
    private Integer mobileAppPageViews;
    private Integer mobileAppPageViewsB2B;
    private Integer pageViews;
    private Integer pageViewsB2B;
    private Integer browserSessions;
    private Integer browserSessionsB2B;
    private Integer mobileAppSessions;
    private Integer mobileAppSessionsB2B;
    private Integer sessions;
    private Integer sessionsB2B;
    private Double buyBoxPercentage;
    private Double buyBoxPercentageB2B;
    private Double orderItemSessionPercentage;
    private Double orderItemSessionPercentageB2B;
    private Double unitSessionPercentage;
    private Double unitSessionPercentageB2B;
    private Integer averageOfferCount;
    private Integer averageParentItems;
    private Integer feedbackReceived;
    private Integer negativeFeedbackReceived;
    private Double receivedNegativeFeedbackRate;

    @Override
    public String toString() {
        return ObjectUtil.toJson(this);
    }
}
