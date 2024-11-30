package ru.panyukovnn.restclientmentoring.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import ru.panyukovnn.restclientmentoring.dto.CreateShortLinkRequest;
import ru.panyukovnn.restclientmentoring.dto.LinkInfoResponse;
import ru.panyukovnn.restclientmentoring.dto.common.CommonRequest;
import ru.panyukovnn.restclientmentoring.dto.common.CommonResponse;

@FeignClient(url = "${mentoring.integration.link-shortener.host}/link-shortener/api/v1/link-infos", name = "link-shortener-client")
public interface LinkShortenerFeignClient {

    @PostMapping
    CommonResponse<LinkInfoResponse> postCreateLinkInfo(CommonRequest<CreateShortLinkRequest> request);

}
