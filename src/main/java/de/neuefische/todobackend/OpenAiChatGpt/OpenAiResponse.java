package de.neuefische.todobackend.OpenAiChatGpt;

import java.util.List;

/***
 *
 *
 {
 "choices": [
 {
 "message": {
 "role": "assistant",
 "content": "Hello! How can I assist you today?",
    }
 }
 ]
 }

 *
 *
 *
 *
 */

public record OpenAiResponse(List<OpenAiChoice> choices) {
}
