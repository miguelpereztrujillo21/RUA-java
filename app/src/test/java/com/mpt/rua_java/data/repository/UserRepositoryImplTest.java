package com.mpt.rua_java.data.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.mpt.rua_java.data.local.dao.UserDao;
import com.mpt.rua_java.data.local.entity.UserEntity;
import com.mpt.rua_java.data.remote.api.RandomUserApiService;
import com.mpt.rua_java.data.remote.dto.*;
import com.mpt.rua_java.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Tests de integración para UserRepositoryImpl
 * Verifica la integración entre API, mappers y base de datos local
 */
@ExtendWith(MockitoExtension.class)
public class UserRepositoryImplTest {

    @Mock
    private RandomUserApiService apiService;

    @Mock
    private UserDao userDao;

    @Mock
    private Call<RandomUserResponseDto> mockCall;

    private UserRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new UserRepositoryImpl(apiService, userDao);
    }

    @Test
    void getRandomUsers_shouldReturnMappedUsers_whenApiCallSuccessful() {
        // Given
        RandomUserResponseDto mockResponse = createMockApiResponse();
        when(apiService.getUsers(100, null, null, null)).thenReturn(mockCall);

        // Simular respuesta exitosa de Retrofit
        doAnswer(invocation -> {
            Callback<RandomUserResponseDto> callback = invocation.getArgument(0);
            callback.onResponse(mockCall, Response.success(mockResponse));
            return null;
        }).when(mockCall).enqueue(any(Callback.class));

        // When
        CompletableFuture<List<User>> result = repository.getRandomUsers(100, null, null, null);

        // Then
        assertNotNull(result);
        List<User> users = result.join();
        assertEquals(1, users.size());
        assertEquals("test-uuid", users.get(0).getId());
        assertEquals("John", users.get(0).getName().getFirst());
    }

    @Test
    void saveUsers_shouldCallDaoInsert_whenUsersProvided() {
        // Given
        List<User> users = createMockDomainUsers();

        // When
        CompletableFuture<Void> result = repository.saveUsers(users);

        // Then
        assertDoesNotThrow(() -> result.join());
        verify(userDao).insertUsers(any(List.class));
    }

    @Test
    void getAllUsers_shouldReturnMappedUsers_whenDaoReturnsData() {
        // Given
        List<UserEntity> entities = createMockUserEntities();
        when(userDao.getAllUsers()).thenReturn(entities);

        // When
        CompletableFuture<List<User>> result = repository.getAllUsers();

        // Then
        assertNotNull(result);
        List<User> users = result.join();
        assertEquals(1, users.size());
        assertEquals("test-id", users.get(0).getId());
        verify(userDao).getAllUsers();
    }

    @Test
    void updateUserContactStatus_shouldCallDao_withCorrectParameters() {
        // Given
        String userId = "test-user-id";
        boolean contactStatus = true;

        // When
        CompletableFuture<Void> result = repository.updateUserContactStatus(userId, contactStatus);

        // Then
        assertDoesNotThrow(() -> result.join());
        verify(userDao).updateUserContactStatus(userId, contactStatus);
    }

    private RandomUserResponseDto createMockApiResponse() {
        RandomUserResponseDto response = mock(RandomUserResponseDto.class);
        UserDto userDto = createMockUserDto();
        when(response.getResults()).thenReturn(Arrays.asList(userDto));
        return response;
    }

    private UserDto createMockUserDto() {
        UserDto userDto = mock(UserDto.class);
        NameDto nameDto = mock(NameDto.class);
        LocationDto locationDto = mock(LocationDto.class);
        StreetDto streetDto = mock(StreetDto.class);
        CoordinatesDto coordDto = mock(CoordinatesDto.class);
        TimezoneDto timezoneDto = mock(TimezoneDto.class);
        LoginDto loginDto = mock(LoginDto.class);
        DateOfBirthDto dobDto = mock(DateOfBirthDto.class);
        RegisteredDto regDto = mock(RegisteredDto.class);
        PictureDto pictureDto = mock(PictureDto.class);

        // Setup mock data
        when(userDto.getGender()).thenReturn("male");
        when(userDto.getName()).thenReturn(nameDto);
        when(userDto.getLocation()).thenReturn(locationDto);
        when(userDto.getEmail()).thenReturn("john@example.com");
        when(userDto.getLogin()).thenReturn(loginDto);
        when(userDto.getDob()).thenReturn(dobDto);
        when(userDto.getRegistered()).thenReturn(regDto);
        when(userDto.getPhone()).thenReturn("123456789");
        when(userDto.getCell()).thenReturn("987654321");
        when(userDto.getPicture()).thenReturn(pictureDto);
        when(userDto.getNat()).thenReturn("US");

        when(nameDto.getTitle()).thenReturn("Mr");
        when(nameDto.getFirst()).thenReturn("John");
        when(nameDto.getLast()).thenReturn("Doe");

        when(locationDto.getStreet()).thenReturn(streetDto);
        when(locationDto.getCity()).thenReturn("City");
        when(locationDto.getState()).thenReturn("State");
        when(locationDto.getCountry()).thenReturn("Country");
        when(locationDto.getPostcode()).thenReturn("12345");
        when(locationDto.getCoordinates()).thenReturn(coordDto);
        when(locationDto.getTimezone()).thenReturn(timezoneDto);

        when(streetDto.getNumber()).thenReturn(123);
        when(streetDto.getName()).thenReturn("Main St");

        when(coordDto.getLatitude()).thenReturn("40.7128");
        when(coordDto.getLongitude()).thenReturn("-74.0060");

        when(timezoneDto.getOffset()).thenReturn("-5:00");
        when(timezoneDto.getDescription()).thenReturn("Eastern Time");

        when(loginDto.getUuid()).thenReturn("test-uuid");
        when(loginDto.getUsername()).thenReturn("johndoe");
        when(loginDto.getPassword()).thenReturn("password");
        when(loginDto.getSalt()).thenReturn("salt");
        when(loginDto.getMd5()).thenReturn("md5hash");
        when(loginDto.getSha1()).thenReturn("sha1hash");
        when(loginDto.getSha256()).thenReturn("sha256hash");

        when(dobDto.getDate()).thenReturn("1990-01-01T00:00:00.000Z");
        when(dobDto.getAge()).thenReturn(33);

        when(regDto.getDate()).thenReturn("2020-01-01T00:00:00.000Z");
        when(regDto.getAge()).thenReturn(3);

        when(pictureDto.getLarge()).thenReturn("https://example.com/large.jpg");
        when(pictureDto.getMedium()).thenReturn("https://example.com/medium.jpg");
        when(pictureDto.getThumbnail()).thenReturn("https://example.com/thumb.jpg");

        return userDto;
    }

    private List<User> createMockDomainUsers() {
        User user = mock(User.class);
        when(user.getId()).thenReturn("test-id");
        return Arrays.asList(user);
    }

    private List<UserEntity> createMockUserEntities() {
        UserEntity entity = new UserEntity();
        entity.setId("test-id");
        entity.setGender("male");
        entity.setNameTitle("Mr");
        entity.setNameFirst("John");
        entity.setNameLast("Doe");
        entity.setEmail("john@example.com");
        entity.setPhone("123456789");
        entity.setCell("987654321");
        entity.setStreetNumber(123);
        entity.setStreetName("Main St");
        entity.setCity("City");
        entity.setState("State");
        entity.setCountry("Country");
        entity.setPostcode("12345");
        entity.setCoordinatesLatitude("40.7128");
        entity.setCoordinatesLongitude("-74.0060");
        entity.setTimezoneOffset("-5:00");
        entity.setTimezoneDescription("Eastern Time");
        entity.setLoginUuid("test-uuid");
        entity.setLoginUsername("johndoe");
        entity.setLoginPassword("password");
        entity.setLoginSalt("salt");
        entity.setLoginMd5("md5hash");
        entity.setLoginSha1("sha1hash");
        entity.setLoginSha256("sha256hash");
        entity.setDobDate("1990-01-01T00:00:00.000Z");
        entity.setDobAge(33);
        entity.setRegisteredDate("2020-01-01T00:00:00.000Z");
        entity.setRegisteredAge(3);
        entity.setPictureLarge("https://example.com/large.jpg");
        entity.setPictureMedium("https://example.com/medium.jpg");
        entity.setPictureThumbnail("https://example.com/thumb.jpg");
        entity.setNat("US");
        entity.setAddedToContacts(false);

        return Arrays.asList(entity);
    }
}
