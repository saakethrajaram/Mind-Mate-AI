package com.example.mindmateai;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MindMateAIEngine {

    private static final String API_KEY = "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";
    private static final String API_URL = "https://api.groq.com/openai/v1/chat/completions";
    private static final String MODEL = "llama-3.3-70b-versatile";

    private final OkHttpClient client = new OkHttpClient();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public interface AIResponseCallback {
        void onResponse(String response);
        void onError(String error);
    }

    public void processPromptAsync(String input, String mode, AIResponseCallback callback) {
        if (input == null || input.trim().isEmpty()) {
            callback.onResponse("Please enter some text first.");
            return;
        }

        if (isUnsafe(input)) {
            callback.onResponse("MindMate AI cannot respond to unsafe or harmful requests. Please enter an academic or productivity-related question.");
            return;
        }

        String systemPrompt = getSystemPromptForMode(mode);
        callGroqApi(input, systemPrompt, callback);
    }

    private String getSystemPromptForMode(String mode) {
        switch (mode) {
            case "EXPLAIN":
                return "You are MindMate AI, an academic assistant. Explain the following topic in simple terms with key points and an example. Use a student-friendly tone.";
            case "SUMMARISE":
                return "You are MindMate AI. Summarise the following content into concise study points. Highlight the important takeaways.";
            case "REWRITE":
                return "You are MindMate AI. Rewrite the following text to be clearer, more professional, and academically structured while preserving the original meaning.";
            case "PLAN":
                return "You are MindMate AI. Create a structured study plan or productivity timeline based on the following goal. Break it down into clear steps.";
            default:
                return "You are MindMate AI, a helpful study assistant.";
        }
    }

    private void callGroqApi(String userInput, String systemPrompt, AIResponseCallback callback) {
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("model", MODEL);
            
            JSONArray messages = new JSONArray();
            messages.put(new JSONObject().put("role", "system").put("content", systemPrompt));
            messages.put(new JSONObject().put("role", "user").put("content", userInput));
            
            jsonBody.put("messages", messages);

            RequestBody body = RequestBody.create(
                    jsonBody.toString(),
                    MediaType.get("application/json; charset=utf-8")
            );

            Request request = new Request.Builder()
                    .url(API_URL)
                    .addHeader("Authorization", "Bearer " + API_KEY)
                    .post(body)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    mainHandler.post(() -> callback.onError("Network error: " + e.getMessage()));
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        mainHandler.post(() -> callback.onError("API error: " + response.code()));
                        return;
                    }

                    try {
                        String responseData = response.body().string();
                        JSONObject jsonResponse = new JSONObject(responseData);
                        String content = jsonResponse.getJSONArray("choices")
                                .getJSONObject(0)
                                .getJSONObject("message")
                                .getString("content");

                        mainHandler.post(() -> callback.onResponse(content));
                    } catch (Exception e) {
                        mainHandler.post(() -> callback.onError("Parsing error"));
                    }
                }
            });

        } catch (Exception e) {
            callback.onError("Initialization error: " + e.getMessage());
        }
    }

    private boolean isUnsafe(String input) {
        String lower = input.toLowerCase();
        return lower.contains("bomb") ||
                lower.contains("weapon") ||
                lower.contains("hack") ||
                lower.contains("steal password") ||
                lower.contains("kill myself") ||
                lower.contains("self harm");
    }
}