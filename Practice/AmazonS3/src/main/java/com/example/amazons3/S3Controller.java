package com.example.amazons3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/s3")
public class S3Controller {

  @Autowired private AmazonS3 amazonS3;

  @Value("${cloud.aws.s3.bucket}")
  private String bucketName;

  @PostMapping("/upload")
  public List<String> uploadFile(@RequestParam("file") List<MultipartFile> files)
      throws IOException {

    List<String> uploadedFileNames = new ArrayList<>();

    for (MultipartFile file : files) {
      if (file.isEmpty()) {
        continue;
      }

      String originalFilename = file.getOriginalFilename();
      String uniqueFilename = UUID.randomUUID().toString() + "-" + originalFilename;

      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentLength(file.getSize());
      amazonS3.putObject(bucketName, uniqueFilename, file.getInputStream(), metadata);

      uploadedFileNames.add(uniqueFilename);
    }
    return uploadedFileNames;
  }

  @DeleteMapping("/delete")
  public String deleteFile(@RequestBody ReqS3Delete req) {
    amazonS3.deleteObject(bucketName, req.fileName());
    return "File deleted successfully";
  }
}
