package ru.panyukovnn.restclientmentoring.service;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import ru.panyukovnn.restclientmentoring.AbstractWireMockTest;
import ru.panyukovnn.restclientmentoring.dto.LinkInfoResponse;
import ru.panyukovnn.restclientmentoring.dto.common.CommonResponse;

class FeignClientServiceTest extends AbstractWireMockTest {

    @Test
    void when_callCreateShortLink_then_success() {
        LinkInfoResponse response = feignClientService.createShortLink(CREATE_SHORT_LINK_REQUEST);

        System.out.println(response);
    }

    @Test
    void when_callCreateShortLinkWithDelay_then_timeoutError() {
        stubResponseWithDelay("/link-shortener/api/v1/link-infos", HttpMethod.POST, CommonResponse.builder().build(), 3000);

        LinkInfoResponse response = feignClientService.createShortLink(CREATE_SHORT_LINK_REQUEST);

        System.out.println(response);
    }

    @Test
    void when_callCreateShortLinkWith400Status_then_badRequest() {
        stub400Response("/link-shortener/api/v1/link-infos", HttpMethod.POST);

        LinkInfoResponse response = feignClientService.createShortLink(CREATE_SHORT_LINK_REQUEST);

        System.out.println(response);
    }

}