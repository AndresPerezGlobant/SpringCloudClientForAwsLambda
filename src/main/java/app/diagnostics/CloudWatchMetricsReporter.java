package app.diagnostics;

import app.domain.LogMessage;
import app.domain.event.EventMessage;
import app.domain.metrics.Metric;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.MetricDatum;
import com.amazonaws.services.cloudwatch.model.PutMetricDataRequest;
import com.amazonaws.services.cloudwatch.model.StandardUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class CloudWatchMetricsReporter {

    private static Log LOGGER = LogFactory.getLog(CloudWatchMetricsReporter.class);

    public static final String PROFILE_DIMENSION = "profile";
    public static final String DOMAIN_DIMENSION = "domain";

    public static final String SUCCESSFUL_EVENT = "SUCCESSFUL_EVENT";
    public static final String FAILED_EVENT = "FAILED_EVENT";
    public static final String RECEIVED_EVENT = "RECEIVED_EVENT";

    @Value("${metrics.domain}")
    private String domain;

    @Value("${metrics.profile}")
    private String profile;

    @Value("${metrics.nameSpace}")
    private String nameSpace;

    @Autowired
    private AmazonCloudWatch amazonCloudWatch;

    @Bean
    public AmazonCloudWatch amazonCloudWatch() {
        return AmazonCloudWatchClientBuilder.defaultClient();
    }

    public Dimension getProfileDimension() {
        return new Dimension().withName(PROFILE_DIMENSION).withValue(profile);
    }

    public Dimension getDomainDimension() {
        return new Dimension().withName(DOMAIN_DIMENSION).withValue(domain);
    }

    public void registerSuccessfulEvent(EventMessage eventMessage) {
        registerEventMetric(getMetricDatumByExecutionStatus(SUCCESSFUL_EVENT));
        LOGGER.info(String.format(LogMessage.LOG10002.getMessage(), new Metric(eventMessage.getEventType(), eventMessage.getEventId()).toString()));
    }

    public void registerFailedEvent(EventMessage eventMessage) {
        registerEventMetric(getMetricDatumByExecutionStatus(FAILED_EVENT));
        LOGGER.info(String.format(LogMessage.LOG10003.getMessage(), new Metric(eventMessage.getEventType(), eventMessage.getEventId()).toString()));
    }

    public void registerReceivedEvent(EventMessage eventMessage) {
        registerEventMetric(getMetricDatumByExecutionStatus(RECEIVED_EVENT));
        LOGGER.info(String.format(LogMessage.LOG10001.getMessage(), new Metric(eventMessage.getEventType(), eventMessage.getEventId()).toString()));
    }

    private void registerEventMetric(MetricDatum datum) {
        PutMetricDataRequest request = new PutMetricDataRequest().withNamespace(nameSpace).withMetricData(datum);
        amazonCloudWatch.putMetricData(request);
    }

    private Collection<Dimension> getDimensions() {
        List<Dimension> dimensions = new ArrayList<>();
        dimensions.add(getProfileDimension());
        dimensions.add(getDomainDimension());
        return dimensions;
    }

    private MetricDatum getMetricDatumByExecutionStatus(String status) {
        MetricDatum datum = new MetricDatum()
                .withMetricName(status)
                .withUnit(StandardUnit.Count)
                .withValue(new Double(1))
                .withDimensions(getDimensions());
        return datum;
    }
}
