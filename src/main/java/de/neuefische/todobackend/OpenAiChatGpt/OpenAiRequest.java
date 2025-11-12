package de.neuefische.todobackend.OpenAiChatGpt;

import java.util.List;

/***
 * {
 *     "model": "gpt-5",
 *     "messages": [
 *       {
 *         "role": "user",
 *         "content": "Hello!"
 *       }
 *     ]
 *   }
 */

public record OpenAiRequest(String model, List<OpenAiMessages> messages) {

}
