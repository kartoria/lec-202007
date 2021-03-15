package kr.or.ddit.myclassroom.live.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.CredentialRefreshListener;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.youtube.YouTubeScopes;

import kr.or.ddit.commons.service.BaseService;

@Service
public class CredentialService extends BaseService{
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static final Collection<String> SCOPES = Arrays.asList(YouTubeScopes.YOUTUBE);
	private NetHttpTransport HTTP_TRANSPORT;
	private GoogleClientSecrets googleClientSecrets;
	private Details details;
	private DataStoreFactory dataStoreFactory = MemoryDataStoreFactory.getDefaultInstance();
	private GoogleAuthorizationCodeFlow.Builder authorizationCodeFlowBuilder;
	private GoogleAuthorizationCodeFlow authorizationCodeFlow;
	
	@Value("#{appInfo.clientSecretFilePath}")
	private Resource clientSecretFile;
	
	@PostConstruct
	public void init() throws GeneralSecurityException, IOException {
		LOGGER.info("client secret file : {} loading", clientSecretFile);
		HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		googleClientSecrets = GoogleClientSecrets.load(JSON_FACTORY, 
				new InputStreamReader(clientSecretFile.getInputStream()));
		details = googleClientSecrets.getDetails();
		LOGGER.info("client secret details : {}", details);
		authorizationCodeFlowBuilder = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, googleClientSecrets, SCOPES)
		        .setApprovalPrompt("auto")
	        	.setAccessType("offline")
		        .setDataStoreFactory(dataStoreFactory);      
		authorizationCodeFlow = authorizationCodeFlowBuilder.build();
	}
	
	/**
	 * Credential store 에 저장된 Credential 로드
	 * @param userId resource owner 의 Credential 을 식별하기 위해 store 에서 사용하는 key
	 * @return
	 * @throws IOException
	 */
	public Credential loadCredential(String userId) throws IOException{
		return authorizationCodeFlow.loadCredential(userId);
	}
	
	/**
	 *<pre>
	 * Resource owner 의 자원에 대한 사용 승인이 이루어졌는지 여부(oAuth 동의)를 의미하는  authorization code 발급 절차.
	 * Authorization Server를 대상으로 로그인 및 API client app이 자원을 사용하도록 승인하기 위한 페이지로 이동함.
	 * 발급된 authorization code는 Google cloud project 에 등록한 client app의 redirect_url 핸들러를 이용하여 받을 수 있음.
	 *</pre> 
	 * @return 자원 사용 승인을 위해 사용될 authorization server의 페이지 주소 (oauth 동의 화면으로 redirect 주소로 사용함).
	 */
	public String sendAuthorizationCodeRequest() {
		return authorizationCodeFlow.newAuthorizationUrl()
									 .setRedirectUri(details.getRedirectUris().get(0))
									 .setResponseTypes(Collections.singleton("code"))
									 .build();
	}
	
	/**
	 *<pre>
	 *	발급된 Authorization code를 다시 authorization server 로 보내 향후 자원 접근시에 사용할 token 정보들을 받아오기 위한 메소드.
	 *  Access toke 과  Refresh token 을 발급받아 Credential 을 생성하고, 생성된 Credential 은 DataStore 에 저장함.
	 *</pre>
	 * @param authorizationCode
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	public Credential createAndStoreCredential(String authorizationCode, String userId) throws IOException{
		GoogleTokenResponse response = authorizationCodeFlow.newTokenRequest(authorizationCode)
				.setRedirectUri(details.getRedirectUris().get(0))
				.setGrantType("authorization_code")
				.execute();
		LOGGER.info("token response : \n{}", response.toPrettyString());
		Credential credential = authorizationCodeFlow.createAndStoreCredential(response, userId);
		LOGGER.info("발급받은 access token : {}", credential.getAccessToken());
		LOGGER.info("access token 잔여시한: {}", credential.getExpiresInSeconds());
		LOGGER.info("발급받은 refresh token : {}, {}", response.getRefreshToken(), credential.getRefreshToken());
		return credential;
	}
	
	/**
	 * Credential 의 만료 여부를 판단하고, 만료시한이 1분 미만인 경우 갱신함. 
	 * @param credential
	 * @return
	 * @throws IOException
	 */
	public boolean validateCredential(Credential credential) throws IOException {
		if(credential.getExpiresInSeconds() < 60) {
			return credential.refreshToken();
		}else {
			return true;
		}
	}
	
	/**
	 * access token 갱신시에 동작할 리스너 등록
	 * @param listener
	 * @return
	 */
	public boolean addCredentialRefreshListener(CredentialRefreshListener listener) {
		LOGGER.info("Original Refresh Token Listeners : {}", authorizationCodeFlow.getRefreshListeners());
		return authorizationCodeFlow.getRefreshListeners().add(listener);
	}
	
	/**
	 * 저장된 Credential을 삭제함.
	 * @param userId
	 * @throws IOException
	 */
	public void deleteCredential(String userId) throws IOException {
		authorizationCodeFlow.getCredentialDataStore().delete(userId);
	}
	
}
