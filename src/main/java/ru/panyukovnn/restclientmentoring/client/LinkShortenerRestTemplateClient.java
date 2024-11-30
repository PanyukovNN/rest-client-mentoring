package ru.panyukovnn.restclientmentoring.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.panyukovnn.restclientmentoring.dto.CreateShortLinkRequest;
import ru.panyukovnn.restclientmentoring.dto.LinkInfoResponse;
import ru.panyukovnn.restclientmentoring.dto.common.CommonRequest;
import ru.panyukovnn.restclientmentoring.dto.common.CommonResponse;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class LinkShortenerRestTemplateClient {

    @Value("${mentoring.integration.link-shortener.host}")
    private String linkShortenerHost;

    private final RestTemplate restTemplate;

    public LinkInfoResponse postCreateLinkInfo(CreateShortLinkRequest createShortLinkRequest) {
        URI uri = UriComponentsBuilder
            .fromHttpUrl(linkShortenerHost)
            .path("/link-shortener/api/v1/link-infos")
            .build()
            .toUri();

        CommonRequest<CreateShortLinkRequest> commonRequest = CommonRequest.<CreateShortLinkRequest>builder()
            .body(createShortLinkRequest)
            .build();

        RequestEntity<CommonRequest<CreateShortLinkRequest>> requestEntity = new RequestEntity<>(commonRequest, HttpMethod.POST, uri);

        ResponseEntity<CommonResponse<LinkInfoResponse>> commonResponse = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<>() {
        });

        return commonResponse.getBody().getBody();
    }

}
