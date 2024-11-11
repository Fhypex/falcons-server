package gtu.cse.se.altefdirt.aymoose.account.internal.infra.config;

import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.spi.ResteasyClientProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.ws.rs.client.Client;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class KeycloakAdminConfig {

    public KeycloakAdminConfig(@Value("${keycloak.admin.auth-server-url}") String authServerUrl,
            @Value("${keycloak.realm}") String realm,
            @Value("${keycloak.admin.client}") String clientId,
            @Value("${keycloak.admin.credentials.secret}") String clientSecret,
            @Value("${keycloak.admin.ssl}") Boolean sslProtected,
            @Value("${keycloak.admin.ssl.cert.path}") String certificatePath,
            @Value("${keycloak.admin.ssl.cert.type}") String certificateType,
            @Value("${keycloak.admin.ssl.cert.password}") String certificatePassword) {
        this.authServerUrl = authServerUrl;
        this.realm = realm;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.sslProtected = sslProtected;
        this.certificatePath = certificatePath;
        this.certificateType = certificateType;
        this.certificatePassword = certificatePassword;
    }

    private final String authServerUrl;
    private final String realm;
    private final String clientId;
    private final String clientSecret;
    private final Boolean sslProtected;
    private final String certificatePath;
    private final String certificateType;
    private final String certificatePassword;

    @Bean
    public Keycloak keycloakAdminClient() {
        try {

            if (this.sslProtected != null && this.sslProtected) {
                ResteasyClientProvider resteasyClientProvider = Keycloak.getClientProvider();
                Client client = resteasyClientProvider.newRestEasyClient(null, createSecuredSSLContext(), false);

                return KeycloakBuilder.builder()
                        .serverUrl(authServerUrl)
                        .realm(realm)
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .resteasyClient(client)
                        .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                        .build();
            }
            return KeycloakBuilder.builder()
                    .serverUrl(authServerUrl)
                    .realm(realm)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                    .build();
        } catch (Exception e) {
            log.error("An expcetion occured during initializing keycloak admin client", e);
            throw new RuntimeException("An expcetion occured during initializing keycloak admin client", e);
        }
    }

    private SSLContext createSecuredSSLContext() throws Exception {
        // Path to your .p12 client certificate file
        final String certFilePath = certificatePath;
        final String certPassword = certificatePassword;

        // Load the client keystore
        KeyStore keyStore = KeyStore.getInstance(certificateType);
        try (FileInputStream keyStoreStream = new FileInputStream(certFilePath)) {
            keyStore.load(keyStoreStream, certPassword.toCharArray());
        }

        // Set up KeyManagerFactory to use the client keystore
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, certPassword.toCharArray());

        // Set up TrustManagerFactory to trust the server's certificate
        TrustManagerFactory trustManagerFactory = TrustManagerFactory
                .getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null); // Trust all certificates (or load a custom truststore)

        // Create SSLContext with KeyManagers and TrustManagers
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(),
                new java.security.SecureRandom());

        return sslContext;
    }
}