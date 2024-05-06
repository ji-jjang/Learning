package com.juny.core.ch05.service;

import com.juny.core.ch05.dto.RecaptchaDto;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GoogleRecaptchaService {

  private static final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";


  private final RestTemplate restTemplate;

  public GoogleRecaptchaService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Value("${captcha.secret.key}")
  private String secretKey;

  public RecaptchaDto verify(String ip, String recaptchaResponse) {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
    map.add("secret", secretKey);
    map.add("response", recaptchaResponse);
    map.add("remoteip", ip);

    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

    ResponseEntity<Map> response = restTemplate.postForEntity(VERIFY_URL, request, Map.class);

    Map<String, Object> body = response.getBody();
    boolean success = (Boolean) body.get("success");
    RecaptchaDto recaptchaDto = new RecaptchaDto();
    recaptchaDto.setSuccess(success);


    if (!success) {
      recaptchaDto.setErrors((List) body.get("error-codes"));
    }

    return recaptchaDto;
  }
}
