import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;

import java.io.IOException;

public class SlackIn {

    public static void sendMessage() throws IOException {
        Slack slack = Slack.getInstance();
        Payload payload = Payload.builder().text("test completed successfully").build();
        WebhookResponse response = slack.send("https://hooks.slack.com/services/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", payload);
        System.out.println(response);
    }

}
