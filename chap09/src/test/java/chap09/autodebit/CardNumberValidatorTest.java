package chap09.autodebit;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.WireMockServer;

public class CardNumberValidatorTest {

	private WireMockServer wireMockServer;

	@BeforeEach
	void setUp() {
		wireMockServer = new WireMockServer(options().port(8089));
		wireMockServer.start();
	}

	@AfterEach
	void tearDown() {
		wireMockServer.stop();
	}

	@Test
	void valid() {
		wireMockServer.stubFor(post(urlEqualTo("/card"))
			.withRequestBody(equalTo("1234567890"))
			.willReturn(aResponse()
				.withHeader("Content-Type", "text/plain")
				.withBody("ok"))
		);

		CardNumberValidator validator = new CardNumberValidator("http://localhost:8089");
		CardValidity validity = validator.validity("1234567890");
		Assertions.assertEquals(CardValidity.VALID, validity);
	}

	@Test
	void timeout() {
		wireMockServer.stubFor(post(urlEqualTo("/card"))
			.willReturn(aResponse()
				.withFixedDelay(5000))
		);

		CardNumberValidator validator = new CardNumberValidator("http://localhost:8089");
		CardValidity validity = validator.validity("1234567890");
		Assertions.assertEquals(CardValidity.TIMEOUT, validity);
	}

}
