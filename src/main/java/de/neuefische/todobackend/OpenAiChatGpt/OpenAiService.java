package de.neuefische.todobackend.OpenAiChatGpt;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service

public class OpenAiService {


    private final RestClient restClient;

    public OpenAiService(RestClient.Builder restClientbuilder, @Value("${KEYFORCHATGPT}") String key){

        this.restClient = restClientbuilder
                .defaultHeader("Authorization", "Bearer "+ key)
                .baseUrl("https://api.openai.com/v1/chat/completions").build();
    }

    public String requestToChatGpt(String text){
        String model = "gpt-5";
        String role = "developer";
        OpenAiMessages openAiMessages = new OpenAiMessages(role,text);
        OpenAiRequest openAiRequest = new OpenAiRequest(model, List.of(openAiMessages));
        OpenAiResponse openAiResponse;
        openAiResponse = restClient.post().contentType(MediaType.APPLICATION_JSON).body(openAiRequest).retrieve().body(OpenAiResponse.class);
        return openAiResponse.choices().get(0).message().content();
    }

}
