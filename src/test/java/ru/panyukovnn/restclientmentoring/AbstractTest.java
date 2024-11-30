package ru.panyukovnn.restclientmentoring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import ru.panyukovnn.restclientmentoring.client.LinkShortenerRestClientClient;
import ru.panyukovnn.restclientmentoring.client.LinkShortenerRestTemplateClient;
import ru.panyukovnn.restclientmentoring.dto.CreateShortLinkRequest;
import ru.panyukovnn.restclientmentoring.service.FeignClientService;

@AutoConfigureWireMock(port = 0)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
            "mentoring.integration.link-shortener.host=http://localhost:${wiremock.server.port}"
        }
)
@ActiveProfiles("test")
public class AbstractTest {

    protected static CreateShortLinkRequest CREATE_SHORT_LINK_REQUEST = CreateShortLinkRequest.builder()
        .link("https://google.com")
        .endTime(null)
        .description("google")
        .active(true)
        .build();

    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected FeignClientService feignClientService;
    @Autowired
    protected LinkShortenerRestClientClient restClientClient;
    @Autowired
    protected LinkShortenerRestTemplateClient restTemplateClient;
}
