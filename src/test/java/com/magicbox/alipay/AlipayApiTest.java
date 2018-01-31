package com.magicbox.alipay;

import org.junit.Test;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;

public class AlipayApiTest {
	
	private String GATEWAY_URL = "https://openapi.alipay.com/gateway.do";
	private String APP_ID = "2017122701264862";
	private String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApzd07xPWcjU0JacC9SYLbmKFg6VooGWjMoSFISK9uY94cGjSOgN2iECEzy3oCsY+X2jgEkKAnIJ44BGZtLkn+Vw+BbTm/Kl1KncpK3KD0jwiYmAE6BLKEw0hF76I1NFJfm+NHK44+FH5gwxJMgLHenBdDIQU7nAE5hppeGDJ99oS8Sie0/BXFVKGxvaGoikoWYNnQQle9XksVAZNGlauL13R4mQLpOmUNCKSCgboRjsW4qFzApig5BJ5DNih4wakYY5qXw7RY4bFma/tGI6ADNe211VxUkZa6h1CyXq3x/1QHtt9Ei+UiMViNH8GwOG6t3wXGA18zd+pMvYp3vqF9wIDAQAB";
	private String PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCnN3TvE9ZyNTQlpwL1JgtuYoWDpWigZaMyhIUhIr25j3hwaNI6A3aIQITPLegKxj5faOASQoCcgnjgEZm0uSf5XD4FtOb8qXUqdykrcoPSPCJiYAToEsoTDSEXvojU0Ul+b40crjj4UfmDDEkyAsd6cF0MhBTucATmGml4YMn32hLxKJ7T8FcVUobG9oaiKShZg2dBCV71eSxUBk0aVq4vXdHiZAuk6ZQ0IpIKBuhGOxbioXMCmKDkEnkM2KHjBqRhjmpfDtFjhsWZr+0YjoAM17bXVXFSRlrqHULJerfH/VAe230SL5SIxWI0fwbA4bq3fBcYDXzN36ky9ine+oX3AgMBAAECggEAHm3TE6aSgZn48VkhCqdvsRiwnzI8heO4/Ppsl4kQ6XGYQyxwBSqYKVdEU8dQ0JUeyJH3JJuBd/muMWhChkajZZdJmhQ/bH8R2TZyAXwHTPywbSJ4AhtZNnpPKVj84W1saFu4XB3ANEAGnbhgoX8r+bX02NA4VuN/oLfxtXw3hKiZLhv4vtyuo+XOB76BRZUYu/r2V9PHcQ06bRjSWUfV3UZx4Yhib7CTuMn09qRhuT5ifwxNfAX2O+a1XYRq95ka+vlodDdQ4/yPux60AJ0KChqFIB/hW+6p85Lcxe2paxXe9BG0P8+4bLeC6254oRqmokWkPl3y0Y8BJFdwy65CEQKBgQDV12c1Q9DOa2LdaKjIeVgdaAzxVBbjTQMGbbChBxrk/0i7JSy8MrAXMUtvvQF91crMx8mKKXE2CzUbDCvQfQx2EGLz2As8YxLMKPr1vEmc+Va45ZsuLN9nb3q2kMAGZudAnjRTGqQ4FajexU63yf4vEvuHm3PTGVXRkVDzw+NscwKBgQDILuVe2uwG3qV6SES0lXfTEPscnH25rRVvTjOusRagKhu353RyAR8ZH5GBKpquMWm5/wy0Khdw/UAVDqzkt5acTj36jK+VnibgKRaGdyorbxuExyk1SIUBbTkOzU44H/l3p8rVRhELYsGBsmOyMCHCO0V5zmFltmNnVj7Lp6ADbQKBgEYDJU5iKq46qH7XMlOYrwnJtFsa7fdzkmb7aNX/7oVFM7naHQBtFAwz3n/yANgDNEfBrbGnieJo3vVBEeNL3dWV3d/6PwuBYHkpfakRAYT8ebFFs331wRsre6DkWYp/DH2yjm0bDuMx7D0rmm9AGhGthF7MvapC5D8d9cQpwX8TAoGBAKdQSvXVFKFJRhXGgWZN5zDKaJepVIv0Vo4JVrkT2k1T5Ib5NSJ0PYHKGNtUuKQPOMb0nbGX3K/be8wdz/P6Xi+WVccr/zWWUffZX7JTtzNGUkHQFGksi759g8sTXeUbFG8mFynavjQ9/m5go2MzR4QH9jGwhUd2Ae9E7odZOyyhAoGBALlC3PEaTbv4MiCauyNhEy2pGi2KzEwcz83X2WkyiMAOb5RmSejaIGuGXIBAmouPji+Hxr3Iz+3QWmpaZOmK7X8WkQwggjzT6GEhnGfIE9TDqq/ktEU3UGOCvjrYqgrZtwFPktUx3V9w2Bzo8dscw/d1819Ogxfco1qrQdYVkLL3";
	private String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk5eQMbfelEKFzC4KadqCC+0vrn/ehzNE9WOJu4aOUxRMbSzbDD9ZbrDzbDLjSvHZ4bOytLsQDmJz7CAVQniU83HjEgIbmcfWBjtTF+tL8DFp32H0QSFOUk+6CmNT0rIOATHYEPr3bemdabLhN3unkufG4FFGJl7u3PCBy+YEQdkFrh1PFC3CZ/ovczIPIeMf+vRGq9FuEW9rmm4o9MXFPMmcWLGSfhEK2LtO2G473O3JICOwSvsoi6WqcQkvUW6z1VsppWgvpqOQSSRrHtI19g6QsJLvvLMlYOLkhc3axSnmZe5v6bvKhmmYwFJ3LxZusu3X1hzaZ2k10mrLUNZGyQIDAQAB";
	
	@Test
	public void getAccessToken() {
		AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL,APP_ID,PRIVATE_KEY,"json","utf-8",ALIPAY_PUBLIC_KEY,"RSA2");
		AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
		request.setCode("2e4248c2f50b4653bf18ecee3466UC18");
		request.setGrantType("authorization_code");
		try {
			AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(request);
			System.out.println(oauthTokenResponse.getAccessToken());
		} catch (AlipayApiException e) {
			//处理异常
			e.printStackTrace();
		}
	}
	
//	@Test
//	public void test() {
//	
//		AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL,APP_ID,PRIVATE_KEY,"json","utf-8",ALIPAY_PUBLIC_KEY,"RSA2");
//		AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
//		AlipayUserInfoShareResponse response = alipayClient.execute(request,accessToken);
//		if(response.isSuccess()){
//		System.out.println("调用成功");
//		} else {
//		System.out.println("调用失败");
//		}
//	}
}
