package capstone.fullstack.service.file;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileServiceTest {

    @Autowired FileService fileService;

    private MockMultipartFile getMockUploadFile() throws IOException{
        return new MockMultipartFile("file", "file.jpg", "image/jpg", new FileInputStream("C:/temp/sample.jpg"));
    }

    @Test
    public void file_save() throws IOException {
        String filePath = fileService.save(getMockUploadFile());
        System.out.println("filePath = " + filePath);

        File file = new File(filePath);
        Assertions.assertThat(file.exists()).isTrue();

        file.delete();
    }
}