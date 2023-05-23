package capstone.fullstack.service.file;

import capstone.fullstack.exception.FileException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    //저장된 파일 경로 반환
    String save(MultipartFile multipartFile) throws FileException;

    void delete(String filePath);
}
