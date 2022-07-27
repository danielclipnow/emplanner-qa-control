package Slack;

import Base.ConfProperties;
import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;

import java.io.IOException;

public class SlackIn {

    public static void sendMessage(String message) throws IOException {
        Slack slack = Slack.getInstance();
        Payload payload = Payload.builder().text(message).build();
        WebhookResponse response = slack.send(ConfProperties.getProperty("urlslack"), payload);
        System.out.println(response);
    }

}
