package ru.panyukovnn.restclientmentoring.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import ru.panyukovnn.restclientmentoring.dto.CreateShortLinkRequest;
import ru.panyukovnn.restclientmentoring.dto.LinkInfoResponse;
import ru.panyukovnn.restclientmentoring.dto.common.CommonRequest;
import ru.panyukovnn.restclientmentoring.dto.common.CommonResponse;

@Service
@RequiredArgsConstructor
public class LinkShortenerRestClientClient {

    @Value("${mentoring.integration.link-shortener.host}")
    private String linkShortenerHost;

    private final RestClient restClient;

    public LinkInfoResponse postCreateLinkInfo(CreateShortLinkRequest createShortLinkRequest) {
        String uri = UriComponentsBuilder
            .fromHttpUrl(linkShortenerHost)
            .path("/link-shortener/api/v1/link-infos")
            .toUriString();

        CommonRequest<CreateShortLinkRequest> commonRequest = CommonRequest.<CreateShortLinkRequest>builder()
            .body(createShortLinkRequest)
            .build();

        CommonResponse<LinkInfoResponse> commonResponse = restClient.post()
            .uri(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .body(commonRequest)
            .retrieve()
            .body(new ParameterizedTypeReference<>() {
            });

        return commonResponse.getBody();
    }

}
