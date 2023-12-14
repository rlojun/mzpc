package com.fivemin.mzpc.service;

import com.fivemin.mzpc.data.entity.Times;
import com.fivemin.mzpc.data.repository.TimesRepository;
import com.fivemin.mzpc.data.vo.KakaoPayApprovalVO;
import com.fivemin.mzpc.data.vo.KakaoPayReadyVO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;


@Service
@Log
public class KakaoPayService {

    private static final String HOST = "https://kapi.kakao.com";

    public KakaoPayReadyVO kakaoPayReadyVO;

    public KakaoPayApprovalVO kakaoPayApprovalVO;

    @Autowired
    private TimesRepository timesRepository;

    private String totalAmount;

    public String kakaoPayReady(String timeCode, int usedMileage) {

        RestTemplate restTemplate = new RestTemplate();

        Times times = timesRepository.findByCode(timeCode);
        String storeName = times.getStore().getName();
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "db04693d86102c79655fac62be801a25");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        // 내 시간 상품 들고와야함
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");
        params.add("item_name", times.getName());
        params.add("quantity", "1");
        params.add("total_amount", String.valueOf(times.getPrice()-usedMileage));
        totalAmount = String.valueOf(times.getPrice() - usedMileage);
        params.add("tax_free_amount", "0");
//        params.add("approval_url",String.format("http://localhost:9010/members/%s/purchaseTime/%s/kakaoPaySuccess",storeName, timeCode));
//        params.add("cancel_url", "http://localhost:9010/kakao/kakaoPayCancel");
//        params.add("fail_url", "http://localhost:9010/kakao/kakaoPaySuccessFail");

        params.add("approval_url",String.format("http://mzpc.net/members/%s/purchaseTime/%s/kakaoPaySuccess",storeName, timeCode));
        params.add("cancel_url", "http://mzpc.net/kakao/kakaoPayCancel");
        params.add("fail_url", "http://mzpc.net/kakao/kakaoPaySuccessFail");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);

            log.info("" + kakaoPayReadyVO);

            return kakaoPayReadyVO.getNext_redirect_pc_url();

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            System.out.println("RestClientException Err ==> "+e.getMessage());
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block

            System.out.println("URISyntaxException Err ==> "+e.getMessage());
        }
        return "/pay";
    }

    public KakaoPayApprovalVO kakaoPayInfo(String pg_token, String timeCode, int usedMileage) {

        log.info("KakaoPayInfoVO............................................");

        RestTemplate restTemplate = new RestTemplate();
        Times times = timesRepository.findByCode(timeCode);
        log.info("times : {} ");

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "db04693d86102c79655fac62be801a25");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");
        params.add("pg_token", pg_token);
        params.add("total_amount", totalAmount);
       // params.add("total_amount", String.valueOf(times.getPrice()-usedMileage));

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
            log.info("" + kakaoPayApprovalVO);

            return kakaoPayApprovalVO;

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}