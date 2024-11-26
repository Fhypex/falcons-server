/* package gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;

import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.gson.GsonFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class GDriveImageRepository {


    private final String credentialsFilePath;
    private final String folderId;
    private final String delegationEmail;

    private static final GsonFactory jsonFactory = GsonFactory.getDefaultInstance();

    public GDriveImageRepository(@Value("${google.drive.credentials}") String credentialsFilePath, @Value("${google.drive.folderId}") String folderId, @Value("${google.drive.delegationEmail}") String delegationEmail) {
        this.credentialsFilePath = credentialsFilePath;
        this.folderId = folderId;
        this.delegationEmail = delegationEmail;
    }

    private GoogleCredentials getCredentials() throws IOException {
        return GoogleCredentials.fromStream(new FileInputStream(credentialsFilePath))
                .createScoped(List.of(DriveScopes.DRIVE))
                .createDelegated(delegationEmail);
    }

    // Initialize Google Drive Service (you would authenticate using a service account)
    private Drive getInstance() throws IOException, GeneralSecurityException {
        
    return new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, new HttpCredentialsAdapter(getCredentials()))
        .setApplicationName("aymoose-server")
        .build();

    }

    public String findFolderIdByName(String folderName) throws IOException, GeneralSecurityException {

        Drive drive = getInstance();
        // Query to find folders by name
        String query = "mimeType = 'application/vnd.google-apps.folder' and name = '" + folderName + "'";
        FileList result = drive.files().list()
                .setQ(query)
                .setFields("files(id, name)")
                .execute();

        // Return the folder ID if found
        if (!result.getFiles().isEmpty()) {
            return result.getFiles().get(0).getId();
        }
        return null;  // Return null if no folder found
    }


    public String upload(Image image, MultipartFile file) throws IOException, GeneralSecurityException {

        Drive service = getInstance();

        // Set file metadata (file name and MIME type)
        File fileMetadata = new File();
        fileMetadata.setName(image.getName());
        fileMetadata.setMimeType(file.getContentType());

        // Create input stream content
        InputStream inputStream = file.getInputStream();
        InputStreamContent mediaContent = new InputStreamContent(file.getContentType(), inputStream);

        // Set the final folder as the parent of the uploaded file
        fileMetadata.setParents(List.of(folderId));

        // Upload file to Google Drive
        File uploadedFile = service.files().create(fileMetadata, mediaContent).setFields("id").execute();

        // Return the file ID which can be used to generate a public URL
        return uploadedFile.getId();
    }
} */