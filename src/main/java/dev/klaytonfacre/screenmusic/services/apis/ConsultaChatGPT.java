package dev.klaytonfacre.screenmusic.services.apis;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConsultaChatGPT {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    @Value("${openai.api.key}")
    private String apiKey;

    public String obterBiografia(String texto) throws Exception {
        OkHttpClient client = new OkHttpClient();

        JSONObject payload = new JSONObject();
        payload.put("model", "gpt-3.5-turbo-16k");

        JSONArray messages = new JSONArray();
        messages.put(new JSONObject()
                .put("role", "system")
                .put("content", "Você é um assistente que fornece biografias sucintas de artistas em português."));
        messages.put(new JSONObject()
                .put("role", "user")
                .put("content", "Forneça a biografia de: " + texto + " em português e com no máximo 200 caracteres para armazenamento em banco de dados."));

        payload.put("messages", messages);
        payload.put("temperature", 0.7);
        payload.put("max_tokens", 1000);

        RequestBody body = RequestBody.create(
                payload.toString(),
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + apiKey)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Erro na chamada: " + response);
            }

            String jsonResponse = response.body().string();
            JSONObject json = new JSONObject(jsonResponse);

            String content = json
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");

            return content.trim();
        }
    }
}
