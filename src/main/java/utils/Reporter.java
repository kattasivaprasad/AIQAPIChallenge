package utils;

import io.qameta.allure.Allure;

import java.io.IOException;

public class Reporter {

    public void addAllureAttachment(String name, Object file) throws IOException {
            Allure.getLifecycle().addAttachment(name, "application/json", "json", ((String) file).getBytes("UTF-8"));
    }

    public void addAttachment(String url, Integer responseCode, String responseMessage) {
        try {
            addAllureAttachment("URL", url);
            addAllureAttachment("ResponseCode", responseCode.toString());
            addAllureAttachment("ResponseMessage", responseMessage);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
