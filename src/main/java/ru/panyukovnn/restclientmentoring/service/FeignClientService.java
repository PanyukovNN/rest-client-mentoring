package ru.panyukovnn.restclientmentoring.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.panyukovnn.restclientmentoring.client.LinkShortenerFeignClient;
import ru.panyukovnn.restclientmentoring.dto.CreateShortLinkRequest;
import ru.panyukovnn.restclientmentoring.dto.LinkInfoResponse;
import ru.panyukovnn.restclientmentoring.dto.common.CommonRequest;
import ru.panyukovnn.restclientmentoring.dto.common.CommonResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeignClientService {

    public static final LinkInfoResponse CREATE_SHORT_LINK_FALLBACK_RESPONSE = new LinkInfoResponse();

    private final LinkShortenerFeignClient linkShortenerFeignClient;

    public LinkInfoResponse createShortLink(CreateShortLinkRequest createShortLinkRequest) {
        try {
            CommonResponse<LinkInfoResponse> commonResponse = linkShortenerFeignClient.postCreateLinkInfo(CommonRequest.<CreateShortLinkRequest>builder()
                .body(createShortLinkRequest)
                .build());

            if (StringUtils.hasText(commonResponse.getErrorMessage())) {
                log.warn("Получено сообщение об ошибке от сервиса link-shortener при попытке cоздания короткой ссылки: " + commonResponse.getErrorMessage());

                return CREATE_SHORT_LINK_FALLBACK_RESPONSE;
            }

            return commonResponse.getBody();
        } catch (FeignException feignException) {
            log.warn("Ошибка при обращении к сервису link-shortener. Статус ответа: {}. Тело ответа: {}. Сообщение об ошибке: {}",
                feignException.status(), feignException.responseBody(), feignException.getMessage(), feignException);
        } catch (Exception e) {
            log.error("Непредвиденная ошибка при обращении к сервису content: {}", e.getMessage(), e);
        }

        return CREATE_SHORT_LINK_FALLBACK_RESPONSE;
    }
}
