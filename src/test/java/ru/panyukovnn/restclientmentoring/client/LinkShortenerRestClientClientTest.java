package ru.panyukovnn.restclientmentoring.client;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import ru.panyukovnn.restclientmentoring.AbstractWireMockTest;
import ru.panyukovnn.restclientmentoring.dto.LinkInfoResponse;
import ru.panyukovnn.restclientmentoring.dto.common.CommonResponse;

import java.util.UUID;

class LinkShortenerRestClientClientTest extends AbstractWireMockTest {

    @Test
    void when_callCreateShortLink_then_success() {
        stubResponse("/link-shortener/api/v1/link-infos", HttpMethod.POST, CommonResponse.builder()
            .id(UUID.randomUUID())
            .body(LinkInfoResponse.builder()
                .id(UUID.randomUUID())
                .link(CREATE_SHORT_LINK_REQUEST.getLink())
                .shortLink("testshortlink")
                .description(CREATE_SHORT_LINK_REQUEST.getDescription())
                .active(true)
                .openingCount(0L)
                .build())
            .build());

        LinkInfoResponse response = restClientClient.postCreateLinkInfo(CREATE_SHORT_LINK_REQUEST);

        System.out.println(response);
    }
}