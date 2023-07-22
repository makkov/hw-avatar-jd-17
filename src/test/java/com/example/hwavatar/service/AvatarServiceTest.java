package com.example.hwavatar.service;

import com.example.hwavatar.model.Avatar;
import com.example.hwavatar.repository.AvatarRepository;
import com.example.hwavatar.repository.FacultyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {AvatarService.class})
@ExtendWith(SpringExtension.class)
class AvatarServiceTest {

    @Autowired
    private AvatarService avatarService;

    @MockBean
    private AvatarRepository avatarRepository;

    @MockBean
    private FacultyRepository facultyRepository;

    @Test
    void getAll_success() {
        //Подготовка входных данных
        int page = 0;
        int size = 5;
        PageRequest pageRequest = PageRequest.of(page, size);

        Avatar firstAvatar = new Avatar();
        firstAvatar.setId(1l);

        Avatar secondAvatar = new Avatar();
        secondAvatar.setId(2l);

        List<Avatar> avatars = List.of(firstAvatar, secondAvatar);

        PageImpl<Avatar> pageable = new PageImpl<>(avatars);

        //Подготовка ожидаемого результата
        when(avatarRepository.findAll(pageRequest)).thenReturn(pageable);

        //Начало теста
        List<Avatar> actualAvatars = avatarService.getAll(page, size);
        assertEquals(avatars, actualAvatars);
        verify(avatarRepository).findAll(pageRequest);
        verifyNoMoreInteractions(avatarRepository);
    }

    @Test
    void getAll_empty() {
        //Подготовка входных данных
        int page = 0;
        int size = 5;
        PageRequest pageRequest = PageRequest.of(page, size);

        //Подготовка ожидаемого результата
        when(avatarRepository.findAll(pageRequest)).thenReturn(Page.empty());

        //Начало теста
        List<Avatar> actualAvatars = avatarService.getAll(page, size);
        assertTrue(actualAvatars.isEmpty());
        verify(avatarRepository).findAll(pageRequest);
        verifyNoMoreInteractions(avatarRepository);
    }
}