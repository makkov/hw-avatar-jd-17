package com.example.hwavatar.service;

import com.example.hwavatar.model.Avatar;
import com.example.hwavatar.model.Faculty;
import com.example.hwavatar.repository.AvatarRepository;
import com.example.hwavatar.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarService {

    private final String avatarsDir;
    private final FacultyRepository facultyRepository;
    private final AvatarRepository avatarRepository;

    public AvatarService(
            @Value("${path.to.avatars.folder}") String avatarsDir,
            FacultyRepository facultyRepository,
            AvatarRepository avatarRepository
    ) {
        this.avatarsDir = avatarsDir;
        this.facultyRepository = facultyRepository;
        this.avatarRepository = avatarRepository;
    }

    public void uploadAvatar(Long facultyId, MultipartFile avatarFile) throws IOException {
        Faculty faculty = facultyRepository.findById(facultyId).get();
        // строчка ниже работает для MacOs. заменить для Windows: Path filePath = Path.of(avatarsDir, student + "." + getExtensions(avatarFile.getOriginalFilename()));
        Path filePath = Path.of(new File("").getAbsolutePath()+ avatarsDir, faculty + "." + getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = new Avatar();
        avatar.setFaculty(faculty);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }

    public Avatar findAvatar(long id) {
        return avatarRepository.findById(id).get();
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public List<Avatar> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return avatarRepository.findAll(pageRequest).getContent();
    }
}
