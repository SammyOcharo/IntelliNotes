package com.samdev.payment_microservice.mpesa;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.samdev.payment_microservice.entity.Payment;
import com.samdev.payment_microservice.entity.PaymentOption;
import com.samdev.payment_microservice.repository.PaymentRepository;
import com.samdev.payment_microservice.request.PaymentRequest;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class MpesaTransact {


    private static final Logger log = LoggerFactory.getLogger(MpesaTransact.class);
    private static PaymentRepository paymentRepository;

    public MpesaTransact(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public static String getInstantTime(){
        Instant now = Instant.now();

        // Define the formatter
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendValue(ChronoField.YEAR, 4)
                .appendValue(ChronoField.MONTH_OF_YEAR, 2)
                .appendValue(ChronoField.DAY_OF_MONTH, 2)
                .appendValue(ChronoField.HOUR_OF_DAY, 2)
                .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
                .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
                .toFormatter()
                .withZone(ZoneId.of("UTC"));

        return formatter.format(now);
    }

    public static String generatePassword(String businessShortCode, String passKey, String timestamp){
        String combinedString = businessShortCode + passKey + timestamp;

        return Base64.getEncoder().encodeToString(combinedString.getBytes());
    }

    public static String generateToken(String ClientId, String ClientSecret, String authUrl) throws URISyntaxException, IOException, InterruptedException {
        String authHeader = ClientId + ":" + ClientSecret;
        String base64AuthHeader = Base64.getEncoder().encodeToString(authHeader.getBytes());

        // Prepare the HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(authUrl))
                .header("Authorization", "Basic " + base64AuthHeader)
                .GET()
                .build();

        // Send the HTTP request
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        Map jsonResponse = objectMapper.readValue(response.body(), Map.class);

        return (String) jsonResponse.get("access_token");

    }

    public static boolean initiateTransaction(
            String phoneNumber,
            String businessShortCode,
            String ClientId,
            String ClientSecret,
            String auth,
            String amount,
            String callBackUrl,
            String transactionType,
            String requestURL,
            String passkey,
            String accountReference) throws IOException, URISyntaxException, InterruptedException {

        String password = generatePassword(businessShortCode, passkey, getInstantTime());

        OkHttpClient client = new OkHttpClient();

        HashMap<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("BusinessShortCode", businessShortCode);
        requestBodyMap.put("Password", password);
        requestBodyMap.put("Timestamp", getInstantTime());
        requestBodyMap.put("TransactionType", transactionType);
        requestBodyMap.put("Amount", amount);
        requestBodyMap.put("PartyA", phoneNumber);
        requestBodyMap.put("PartyB", businessShortCode);
        requestBodyMap.put("PhoneNumber", phoneNumber);
        requestBodyMap.put("CallBackURL", callBackUrl);
        requestBodyMap.put("AccountReference", accountReference);
        requestBodyMap.put("TransactionDesc", "Payment of IntelliNotes");

        // Convert the HashMap to a JSON string
        Gson gson = new Gson();
        String jsonBody = gson.toJson(requestBodyMap);

        // Set the JSON body to the request
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonBody);

        Request request = new Request.Builder()
                .url(requestURL)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + generateToken(ClientId, ClientSecret, auth))
                .build();

        try (Response response = client.newCall(request).execute()) {
            log.info("This is the response: {}", response);
            String responseBody = response.body() != null ? response.body().string() : "No response body";
            if (!response.isSuccessful()) {
                System.out.println("Request failed: " + response.code() + " " + response.message());
                System.out.println("Error body: " + responseBody);

                return false;
            } else {
                System.out.println("Response: " + responseBody);

                saveResponseToDB(responseBody, amount, phoneNumber, accountReference);
                return true;
            }
        } catch (Exception e) {
            log.error("Request execution failed: ", e);
        }


        return false;
    }

    private static void saveResponseToDB(String responseBody, String amount, String phoneNumber, String accountReference) {
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        String merchantRequestID = jsonObject.get("MerchantRequestID").getAsString();
        String checkoutRequestID = jsonObject.get("CheckoutRequestID").getAsString();

        Payment payment = new Payment();
        payment.setPaymentOption(PaymentOption.MPESA);
        payment.setTransactionAmount(Double.valueOf(amount));
        payment.setMerchantRequestID(merchantRequestID);
        payment.setCheckoutRequestID(checkoutRequestID);
        payment.setTransactionReference(accountReference);
        payment.setPhoneNumber(phoneNumber);
        paymentRepository.save(payment);

    }
}
